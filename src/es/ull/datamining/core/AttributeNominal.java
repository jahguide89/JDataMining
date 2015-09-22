package es.ull.datamining.core;

import java.util.ArrayList;

/**
 * @author Ivan Ramirez *
 */
public class AttributeNominal extends Attribute {

	private boolean unique;
	private ArrayList<AttributeClass> classes;

	public AttributeNominal() {
		super("");
		setIsClass(true);
		this.unique = false;
		this.classes = new ArrayList<AttributeClass>();
	}

	public AttributeNominal(String name) {
		super(name);
		setIsClass(true);
		this.unique = false;
		this.classes = new ArrayList<AttributeClass>();
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public int getDistinctValues() {
		return classes.size();
	}

	public void setClasses(ArrayList<AttributeClass> classes) {
		this.classes = classes;
	}

	public ArrayList<AttributeClass> getClasses() {
		return this.classes;
	}
	
	public void addClass(AttributeClass attributeClass) {
		classes.add(attributeClass);
	}
	
	public AttributeClass getClass(int index) {
		return classes.get(index);
	}

	public void updateData() {
		updateClass();
		updateUnique();
	}

	private void updateUnique() {
		if (this.getValues().size() == classes.size())
			unique = true;
	}

	private void updateClass() {
		classes = new ArrayList<AttributeClass>();
		for (int i = 0; i < this.getValues().size(); i++) {
			int index = indexOf(getValue(i));
			if (index == -1)
				addClass(new AttributeClass(getValue(i).toString(),1));
			else
				classes.get(index).setFrequency(
						classes.get(index).getFrequency() + 1);
		}
	}

	private int indexOf(Value value) {
		for (int i = 0; i < classes.size(); i++) {
			if (classes.get(i).getValue().equals(value.toString()))
				return i;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		builder.append(getName());
		builder.append(',');
		builder.append(getWeight()+"");
		builder.append(',');
		builder.append(isClass()+"");
		builder.append(',');
		builder.append(isUnique()+"");
		builder.append(',');
		builder.append(getClasses().toString());
		builder.append(']');
		return builder.toString();
	}

	@Override
	public boolean isNumeric() {
		return false;
	}

	@Override
	public String getType() {
		return Attribute.NOMINAL;
	}

}
