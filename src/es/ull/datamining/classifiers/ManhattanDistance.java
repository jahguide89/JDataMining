package es.ull.datamining.classifiers;

import es.ull.datamining.core.NumericValue;
import es.ull.datamining.core.Instance;

public class ManhattanDistance implements DistanceMetric {

	public double getDistance(Instance instance1, Instance instance2, double[] weights) {
		double distance = 0;
		for (int i = 0; i < instance1.size(); i++) {
			if (instance1.getValue(i) instanceof NumericValue
					&& instance2.getValue(i) instanceof NumericValue)
				distance += Math.abs((Double) instance1.getValue(i).getValue()
						- (Double) instance2.getValue(i).getValue()) * weights[i];
		}
		return distance;
	}
	
	@Override
	public String toString() {
		return "Manhattan distance";
	}

}
