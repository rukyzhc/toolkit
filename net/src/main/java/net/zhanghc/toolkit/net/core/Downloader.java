package net.zhanghc.toolkit.net.core;

import java.io.IOException;

import net.zhanghc.toolkit.net.data.Page;
import net.zhanghc.toolkit.net.data.Url;
import net.zhanghc.toolkit.net.proxy.Proxy;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HttpContext;

public class Downloader {

	DefaultHttpClient httpClient;

	public Downloader() {
		httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
	}
	
	public void setUserAgent(String ua) {
		httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2");
	}
	
	public void setProxy(Proxy p) {
		HttpHost proxy = new HttpHost(p.getHost(), p.getPort());
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
	
	public Page fetch(Url url) throws IllegalStateException, IOException {
		return fetch(url, null);
	}

	public Page fetch(Url url, Header[] headers) throws IllegalStateException, IOException {
		HttpGet get = new HttpGet(url.getUrl());
		if(headers != null) {
			get.setHeaders(headers);
		}
		UrlResolver resolver = new UrlResolver(url.getUrl());
		httpClient.setRedirectStrategy(resolver);

		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();

		Page ret = new Page();
		ret.setUrl(url);
		ret.setFinalUrl(resolver.getFinalUrl());
		ret.load(response.getAllHeaders());
		ret.load(entity.getContent());

		return ret;
	}

	static class UrlResolver extends DefaultRedirectStrategy {
		String url = null;

		public UrlResolver(String url) {
			this.url = url;
		}

		public String getFinalUrl() {
			return url;
		}

		@Override
		public HttpUriRequest getRedirect(HttpRequest request,
				HttpResponse response, HttpContext context)
						throws ProtocolException {
			HttpUriRequest req = super.getRedirect(request, response, context);
			url = req.getURI().toString();
			return req;
		}

	}
}
