package net.zhanghc.toolkit.util;

public class Progress {
	int count = 0;
	int total = 0;
	int lap = 0;
	long start = 0l;
	
	public Progress(int total, int lap) {
		this.total = total;
		this.lap = lap;
		
		start();
	}
	
	public void process() {
		count++;
	}
	
	public boolean lap() {
		return count % lap == 0;
	}
	
	public void start() {
		count = 0;
		start = System.currentTimeMillis();
	}
	
	public static final int SECOND = 0;
	public static final int MINITE = 1;
	public static final int HOUR = 2;
	
	public double eta(int timeunit) {
		double interval = 1000.0;
		switch(timeunit) {
		case MINITE:
			interval = 60 * 1000.0;
			break;
		case HOUR:
			interval = 3600 * 1000.0;
			break;
		case SECOND:
		default:
			interval = 1000.0;
		}
		return ((System.currentTimeMillis() - start) * (total - count)) / (interval * count);
	}

}
