package es.ull.datamining.util;

/**
 * @author Admin
 *
 */
public class Probability {

	private static final double NORM_CONST = Math.sqrt(2 * Math.PI);;

	/**
	 * Density function of normal distribution.
	 * 
	 * @param x
	 *            the value to get the density for
	 * @param mean
	 *            the mean
	 * @param stdDev
	 *            the standard deviation
	 * @return the density
	 */
	public static double normalDens(double x, double mean, double stdDev) {

		double diff = x - mean;

		return (1 / (NORM_CONST * stdDev))
				* (Math.exp(-((diff * diff) / (2 * (stdDev * stdDev)))));
	}

	/**
	 * P(value|target) = P(target^value)/P(target)
	 * 
	 * @param values
	 * @param instancesClass
	 * @param value
	 * @param target
	 * @return
	 */
	public static double conditionalProbability(String[] values,
			String[] instancesClass, String value, String target) {
		double prob = 0;
		double count = 0;
		for (int i = 0; i < values.length; i++) {
			if (instancesClass[i].equals(target)) {
				count++;
				if (values[i].equals(value))
					prob++;
			}
		}
		return prob / count;
	}

	/**
	 * P(value|target) = P(target^value)/P(target)
	 * 
	 * P(A_i=c_j|c_k) = (n_ijk + 1) / (n_k + s_i) n_ijk: number of examples in
	 * c_k where A_i = v_j n_k: number of example in c_k s_i: number of possible
	 * values for A_i
	 * 
	 * @param values
	 * @param instancesClass
	 * @param value
	 * @param target
	 * @param nAttributeClass
	 * @return
	 */
	public static double condProbLaplaceSmooth(String[] values,
			String[] instancesClass, String value, String target,
			int nAttributeClass) {
		double prob = 0;
		double count = 0;
		for (int i = 0; i < values.length; i++) {
			if (instancesClass[i].equals(target)) {
				count++;
				if (values[i].equals(value))
					prob++;
			}
		}
		return (prob + 1) / (count + (double) nAttributeClass);
	}

	/**
	 * @param values
	 * @param instancesClass
	 * @param target
	 * @return
	 */
	public static double conditionalMean(double[] values,
			String[] instancesClass, String target) {
		double mean = 0;
		double count = 0;
		for (int i = 0; i < values.length; i++) {
			if (instancesClass[i].equals(target)) {
				mean += values[i];
				count++;
			}
		}
		return mean / count;
	}

	/**
	 * @param values
	 * @param instancesClass
	 * @param target
	 * @param mean
	 * @return
	 */
	public static double conditionalStandardDeviation(double[] values,
			String[] instancesClass, String target, double mean) {
		double stdDev = 0;
		double count = 0;
		for (int i = 0; i < values.length; i++) {
			if (instancesClass[i].equals(target)) {
				stdDev += (values[i] - mean) * (values[i] - mean);
				count++;
			}
		}
		return Math.sqrt(stdDev / (count - 1));
	}

}
