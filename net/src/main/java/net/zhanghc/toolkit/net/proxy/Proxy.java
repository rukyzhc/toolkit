package net.zhanghc.toolkit.net.proxy;

public interface Proxy extends Comparable<Proxy> {

	public String getHost();

	public int getPort();
	
	public int getPriority();

}
