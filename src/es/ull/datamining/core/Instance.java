package es.ull.datamining.core;

import java.util.Arrays;

import es.ull.datamining.util.Util;

public class Instance {
	
	private Value[] values;
	private boolean[] testTrainingSet;
	private int[] classIndexes;
	
	public Instance(int size) {
		values = new Value[size];
	}
	
	public Instance(int size, boolean[] testTrainingSet) {
		values = new Value[size];
		this.setTestTrainingSet(testTrainingSet);
	}
	
	public Instance() {
		values = new Value[0];
	}
	
	public Instance(Value[] values, boolean[] testTrainingSet) {
		this.setValues(values);
		this.setTestTrainingSet(testTrainingSet);
	}

	public Value[] getValues() {
		return values;
	}
	
	public Value getValue(int index) {
		return values[index];
	}

	public void setValues(Value[] values) {
		this.values = values;
	}
	
	public void setValue(int index, Value element) {
		this.values[index] = element;
	}
	
	public boolean[] getTestTrainingSet() {
		return testTrainingSet;
	}

	public void setTestTrainingSet(boolean[] testTrainingSet) {
		this.testTrainingSet = testTrainingSet;
	}

	public int size(){
		return values.length;
	}

	@Override
	public String toString() {
		String tmp = "";
		tmp+= Arrays.toString(values);
		return tmp.substring(1, tmp.length()-1); 
	}
	
	public boolean isTrain() {
		return isTrain(0);
	}
	
	public boolean isTrain(int index) {
		return testTrainingSet[index];
	}
	
	public static Instance parseValues(String[] values) {
		Instance parse = new Instance(values.length);
		for (int i = 0; i < values.length; i++) {
			if(Util.isNumeric(values[i]))
				parse.setValue(i, new NumericValue(values[i]));
			else
				parse.setValue(i, new NominalValue(values[i]));
		}
		return parse;
	}

	public String getInstanceClass() {
		String[] classes = new String[classIndexes.length];
		for (int i = 0; i < classes.length; i++) {
			classes[i]=values[classIndexes[i]].getValue().toString();
		}
		return Arrays.toString(classes);
	}

	public int[] getClassIndexes() {
		return classIndexes;
	}

	public void setClassIndexes(int[] indexClass) {
		this.classIndexes = indexClass;
	}

}
