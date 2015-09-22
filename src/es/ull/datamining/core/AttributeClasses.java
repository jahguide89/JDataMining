package es.ull.datamining.core;

import java.util.ArrayList;

public class AttributeClasses {
	private ArrayList<AttributeClass> classes;
	
	public AttributeClasses() {
		classes = new ArrayList<AttributeClass>();
	}
	
	public void addInstanceClass(String instanceClass){
		int index = indexOf(instanceClass);
		if (index == -1){
			classes.add(new AttributeClass(instanceClass, 1));
		}
		else
			classes.get(index).setFrequency(
					classes.get(index).getFrequency() + 1);
	}

	private int indexOf(String instanceClass) {
		for (int i = 0; i < classes.size(); i++) {
			String a = classes.get(i).toString();
			if (instanceClass.equals(a))
				return i;
		}
		return -1;
	}

	public AttributeClass[] getClasses() {
		return classes.toArray(new AttributeClass[classes.size()]);
	}
	
	public AttributeClass getClass(int index) {
		return classes.get(index);
	}
	
	public int size(){
		return classes.size();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
