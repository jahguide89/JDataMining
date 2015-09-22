package es.ull.datamining.filters;

import es.ull.datamining.core.AttributeNumeric;
import es.ull.datamining.core.NumericValue;

/**
 * Sum((x_i-avg)/stdev)
 * 
 * @author Administrador
 *
 */
public class Standardized extends Filter {

	@Override
	public void runFilter() {
		for (int i = 0; i < dataset.nAttributes(); i++) {
			if (dataset.getAttribute(i).isNumeric()){
				AttributeNumeric at = (AttributeNumeric) dataset.getAttribute(i);
				double avg = at.getAvg();
				double stdev = at.getStdDev();
				for (int j = 0; j < at.size(); j++) {
					double x = (Double) at.getValue(j).getValue();
					x = (x - avg) / stdev;
					at.setValue(j, new NumericValue(x));
				}
			}
		}
		

	}

	@Override
	public String toString() {
		return Standardized.class.getName();
	}

}
