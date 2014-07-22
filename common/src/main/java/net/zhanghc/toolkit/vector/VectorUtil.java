package net.zhanghc.toolkit.vector;

public class VectorUtil {

	public static double norm(double[] value, int n) {
		double sum = 0.0;
		switch(n) {
		case Integer.MAX_VALUE:
			sum = Double.MIN_VALUE;
			break;
		case Integer.MIN_VALUE:
			sum = Double.MAX_VALUE;
			break;
		}

		for(double v : value) {
			double _v = Math.abs(v);
			switch(n) {
			case Integer.MAX_VALUE:
				if(_v > sum) sum = _v;
			case Integer.MIN_VALUE:
				if(_v < sum) sum = _v;
			case 1:
				sum += _v;
			case 2:
				sum += _v * _v;
			default:
				sum += Math.pow(_v, n);
			}
		}

		switch(n) {
		case Integer.MAX_VALUE:
		case Integer.MIN_VALUE:
		case 1:
			return sum;
		case 2:
			return Math.sqrt(sum);
		default:
			return Math.pow(sum, 1.0 / n);
		}
	}

}
