package net.zhanghc.toolkit.net.data;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;

public class Page {
	Url url;
	String finalUrl;
	byte[] content;
	String header;

	public void setUrl(Url url) {
		this.url = url;
	}

	public void load(InputStream is) throws IOException {
		content = IOUtils.toByteArray(is);
	}

	public void load(Header[] header) {
		StringBuilder sb = new StringBuilder();
		for(Header h : header) {
			sb.append(h.getName());
			sb.append(':');
			sb.append(h.getValue());
			sb.append('\n');
		}
		this.header = sb.toString();
	}

	public void setFinalUrl(String fUrl) {
		this.finalUrl = fUrl;
	}

	public String getFinalUrl() {
		return finalUrl;
	}

	public byte[] getContent() {
		return content;
	}

	public Url getUrl() {
		return url;
	}

	public String getHeader() {
		return header;
	}

}
