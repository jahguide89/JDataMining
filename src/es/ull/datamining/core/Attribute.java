package es.ull.datamining.core;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Attribute implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1577425579438525658L;
	public static final String NOMINAL = "Nominal";
	public static final String NUMERIC = "Numeric";
	
	private String name;
	private double weight;
	private boolean isClass;
	
	private ArrayList<Value> values;
	
	public Attribute(String name) {
		this.name = name;
		this.weight = 1d;
		this.values = new ArrayList<Value>();
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	public void setValues(ArrayList<Value> values) {
		this.values = values;
	}

	public ArrayList<Value> getValues() {
		return this.values;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isClass() {
		return isClass;
	}

	public void setIsClass(boolean isClass) {
		this.isClass = isClass;
	}

	public int size() {
		return getValues().size();
	}

	public void addValue(Value value) {
		getValues().add(value);
	}

	public void delValue(int index) {
		getValues().remove(index);
	}

	public Value getValue(int index) {
		return getValues().get(index);
	}
	
	public Value setValue(int index, Value value) {
		return getValues().set(index,value);
	}
	
	public abstract void updateData();
	
	public abstract String toString();

	public abstract boolean isNumeric();

	public abstract String getType();

}
