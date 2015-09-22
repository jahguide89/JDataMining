package es.ull.datamining.core;

public class NumericValue extends Value {
	private double value;

	public NumericValue(double value) {
		this.value = value;
	}
	
	public NumericValue(String value) {
		this.value = Double.parseDouble(value);
	}

	public NumericValue() {
		this(0d);
	}

	public void setValue(Object value) {
		this.value = Double.parseDouble(value.toString());
	}

	public Object getValue() {
		return (double) this.value;
	}

	public String toString() {
		return this.value+"";
	}

	public boolean isNumeric() {
		return true;
	}

	@Override
	public double toDouble() {
		return value;
	}

}
