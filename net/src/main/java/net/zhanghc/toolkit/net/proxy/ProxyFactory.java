package net.zhanghc.toolkit.net.proxy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TreeSet;

public class ProxyFactory {
	
	private TreeSet<Proxy> proxies;

	public void load() throws Exception {
		load(ProxyFactory.class.getResourceAsStream("proxy.list"));
	}

	public void load(String path) throws Exception {
		load(new FileInputStream(path));
	}

	public void load(InputStream is) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		proxies = new TreeSet<Proxy>();
		while((line = br.readLine()) != null) {
			proxies.add(parse(line));
		}
		br.close();
	}
	
	public void addProxy(Proxy proxy) {
		proxies.add(proxy);
	}
	
	public Proxy proxy() {
		return proxies.first();
	}
	
	public Proxy pop() {
		return proxies.pollFirst();
	}
	
	static int timeout = 10000;
	public Proxy available() throws UnknownHostException, IOException {
		for(Proxy p : proxies) {
			if(InetAddress.getByName(p.getHost()).isReachable(timeout))
				return p;
		}
		return null;
	}
	
	static Proxy parse(String line) {
		switch(line.charAt(0)) {
		case 'S':
		default:
			String segs[] = line.split("\t");
			SimpleProxy sp = new SimpleProxy();
			sp.host = segs[1];
			sp.port = Integer.parseInt(segs[2]);
			sp.priority = Integer.parseInt(segs[3]);
			return sp;
		}
	}

}
