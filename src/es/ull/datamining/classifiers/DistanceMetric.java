package es.ull.datamining.classifiers;

import es.ull.datamining.core.Instance;

public interface DistanceMetric {
	
	/**
	 * @param instance1
	 * @param instance2
	 * @param weights
	 * @return
	 */
	public double getDistance(Instance instance1, Instance instance2, double[] weights);

}
