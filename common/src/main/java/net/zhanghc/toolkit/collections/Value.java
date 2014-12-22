package net.zhanghc.toolkit.collections;

public class Value<K> extends ComputableMap<K, Double> {
	private static final long serialVersionUID = -6256800069865162268L;
	
	public void addAll(Value<K> target) {
		merge(target, (d1, d2) -> {
			return d1 + d2;
		});
	}
	
	public void multipleAll(Value<K> target) {
		merge(target, (d1, d2) -> {
			return d1 * d2;
		});
	}

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
