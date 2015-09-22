package es.ull.datamining.filters;

import es.ull.datamining.core.AttributeNumeric;
import es.ull.datamining.core.NumericValue;

/**
 * Sum((x_i-min)/(max-min)*scale+translation)
 * default range [0-1]
 * @author Admin
 *
 */
public class Normalize extends Filter {
	
	private double translation;
	private double scale;
	
	public Normalize() {
		setTranslation(0d);
		setScale(1d);
	}
	
	public Normalize(double translation, double scale) {
		setTranslation(translation);
		setScale(scale);
	}

	@Override
	public void runFilter() {
		for (int i = 0; i < dataset.nAttributes(); i++) {
			if (dataset.getAttribute(i).isNumeric()){
				AttributeNumeric at = (AttributeNumeric) dataset.getAttribute(i);
				double max = at.getMax();
				double min = at.getMin();
				for (int j = 0; j < at.size(); j++) {
					double x = (Double) at.getValue(j).getValue();
					x = (x - min) / (max - min) * scale + translation;
					at.setValue(j, new NumericValue(x));
				}
			}
		}

	}

	public double getTranslation() {
		return translation;
	}

	/**
	 * @param translation - 
	 */
	public void setTranslation(double translation) {
		this.translation = translation;
	}

	public double getScale() {
		return scale;
	}

	/**
	 * @param scale - 
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		return Normalize.class.getName();
	}

}
