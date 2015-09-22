package es.ull.datamining.classifiers;

import es.ull.datamining.core.NumericValue;
import es.ull.datamining.core.Instance;

public class ChebyshevDistance implements DistanceMetric {

	public double getDistance(Instance instance1, Instance instance2, double[] weights) {
		double distance = Double.MIN_VALUE;
		for (int i = 0; i < instance1.size(); i++) {
			if (instance1.getValue(i) instanceof NumericValue
					&& instance2.getValue(i) instanceof NumericValue) {
				double tmp = Math.abs((Double) instance1.getValue(i).getValue()
						- (Double) instance2.getValue(i).getValue()) * weights[i];
				if (tmp > distance)
					distance = tmp;
			}
		}
		return distance;
	}
	
	@Override
	public String toString() {
		return "Chebyshev Distance";
	}
}
