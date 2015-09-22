package es.ull.datamining.classifiers;

public class ThresholdMajority implements DecisionRule {
	private double threshold;

	public ThresholdMajority() {
		setThreshold(0.5d);
	}

	public ThresholdMajority(double threshold) {
		setThreshold(threshold);
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

}
