package net.zhanghc.toolkit.net.data;

import java.net.URI;
import java.net.URISyntaxException;

public class Url {

	String url;

	public Url(String url) {
		this.url = url;
	}

	public Url(Url parent, String url) throws URISyntaxException {
		URI uri = new URI(parent.getUrl()).resolve(url);

		this.url = uri.toASCIIString();
	}

	public String getUrl() {
		return url;
	}

}
