package es.ull.datamining.core;

public class NominalValue extends Value {
	private String value;

	public NominalValue() {
		this("");
	}

	public NominalValue(String value) {
		this.value = value;
	}

	public void setValue(Object value) {
		this.value = (String) value;
	}

	public Object getValue() {
		return (String) this.value;
	}

	public String toString() {
		return value;
	}

	public boolean isNumeric() {
		return false;
	}

	@Override
	public double toDouble() {
		return Double.NaN;
	}

}
