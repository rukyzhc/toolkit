package net.zhanghc.toolkit.net.proxy;


public class SimpleProxy implements Proxy {
	String host;
	int port;
	int priority;
	
	@Override
	public String getHost() {
		return host;
	}
	@Override
	public int getPort() {
		return port;
	}
	@Override
	public int getPriority() {
		return priority;
	}
	@Override
	public int compareTo(Proxy p) {
		int d = p.getPriority() - priority;
		return d == 0 ? p.getHost().compareTo(host) : d;
	}
}
