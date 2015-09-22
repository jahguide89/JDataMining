package es.ull.datamining.filters;

import java.util.ArrayList;

import es.ull.datamining.core.Attribute;
import es.ull.datamining.core.AttributeClass;
import es.ull.datamining.core.AttributeFactory;
import es.ull.datamining.core.AttributeNominal;
import es.ull.datamining.core.Value;
import es.ull.datamining.filters.Filter;

public class AddAttribute extends Filter {
	
	private String name;
	private String[] classes;
	private String type;

	public AddAttribute(String name,String[] classes, String type) {
		setName(name);
		setClasses(classes);
		setType(type);
	}
	
	public AddAttribute(String name, String type) {
		this(name,null, type);
	}
	
	public AddAttribute(String type) {
		this("Unassigned",null, type);
	}

	@Override
	public void runFilter() {
		Attribute at = AttributeFactory.createTypeAttribute(type);
		at.setName(name);
		if (!at.isNumeric() && classes != null) {
			for (int i = 0; i < classes.length; i++) {
				((AttributeNominal) at).addClass(new AttributeClass(classes[i]));
			}
		}
		at.setValues(new ArrayList<Value>(dataset.nInstances()));
		dataset.getAttributes().add(at);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getClasses() {
		return classes;
	}

	public void setClasses(String[] classes) {
		this.classes = classes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return AddAttribute.class.getName();
	}

}
