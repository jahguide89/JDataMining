package es.ull.datamining.core;

import es.ull.datamining.util.Util;

public class AttributeFactory {
	static public Attribute createAttribute(String value){
		if (Util.isNumeric(value))
			return new AttributeNumeric();
		else
			return new AttributeNominal();
	}
	
	static public Attribute createTypeAttribute(String type){
		if (type == Attribute.NUMERIC)
			return new AttributeNumeric();
		else
			return new AttributeNominal();
	}
	

}
