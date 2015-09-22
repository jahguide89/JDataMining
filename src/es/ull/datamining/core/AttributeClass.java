package es.ull.datamining.core;

import java.io.Serializable;

/**
 * @author Ivan Ramirez
 *
 */
public class AttributeClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5293592834688562479L;
	private String[] value;
	private int frequency;
	
	public AttributeClass(String value) {
		this.value = new String[1];
		this.value[0] = value;
		this.frequency = 0;
	}

	public AttributeClass(String value, int frenquecy) {
		this.value = new String[1];
		this.value[0] = value;
		this.frequency = frenquecy;
	}

	public AttributeClass(String[] value) {
		this.value = value;
		this.frequency = 0;
	}

	public AttributeClass(String[] value, int frenquecy) {
		this.value = value;
		this.frequency = frenquecy;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public String[] getValues() {
		return value;
	}
	
	public String getValue() {
		return value[0];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append('[');
		builder.append(value[0]);
		for (int i = 1; i < value.length; i++) {
			builder.append(", ");
			builder.append(value[i]);
		}
//		builder.append(']');
		return builder.toString();
	}

}
