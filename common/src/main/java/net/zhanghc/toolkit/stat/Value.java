package net.zhanghc.toolkit.stat;

public class Value<K> extends ComputableMap<K, Double> {

	@Override
	protected Double add(Double a, Double b) {
		return a + b;
	}

	@Override
	protected Double subtract(Double a, Double b) {
		return a - b;
	}

	@Override
	protected Double zero() {
		return 0.0;
	}

}
