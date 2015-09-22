package es.ull.datamining.core;

import java.io.Serializable;

import es.ull.datamining.util.Util;

public abstract class Value implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2552650590448369503L;

	public abstract void setValue(Object value);

	public abstract Object getValue();
	
	@Override
	public abstract String toString();
	
	public abstract double toDouble();
	
	/**
	 * Convert String to nominal or numeric Value 
	 * 
	 * @param string to parse
	 * @return Value Numeric or Nominal
	 */
	public static Value parseValue(String string) {
		if(Util.isNumeric(string))
			return new NumericValue(string);
		else
			return new NominalValue(string);
	}
	
	public abstract boolean isNumeric();

}
