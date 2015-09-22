package es.ull.datamining.classifiers;

import java.util.ArrayList;

import es.ull.datamining.core.Dataset;
import es.ull.datamining.core.Instance;

public class KnnClassifier implements Classifier {
	// data
	private final Dataset dataset;

	// configuration
	private final int knn;
	private final DistanceMetric distanceMetric;
	private final DistanceWeight distanceWeight;
	private final DecisionRule decisionRule;

	// data structure support
	private KnnHeap heap;
	private int[] weights;
	private int threshold;

	public KnnClassifier(KnnBuilder builder) {
		this.dataset = builder.dataset;
		this.knn = builder.knn;
		this.distanceMetric = builder.distanceMetric;
		this.distanceWeight = builder.distanceWeight;
		this.decisionRule = builder.decisionRule;
		this.threshold = -1;
	}

	/* (non-Javadoc)
	 * @see es.ull.datamining.classifiers.Classifier#runClassifier(es.ull.datamining.core.Instance)
	 */
	public String runClassifier(Instance instance) {
		heap = new KnnHeap(knn);
		for (int i = 0; i < dataset.nInstances(); i++) {
			double distance = distanceMetric.getDistance(
					dataset.getInstance(i), instance, dataset.getAttributesWeights());
			heap.offer(new KnnHeapElement(i, distance));
		}
		if (distanceWeight != null){
			if (distanceWeight instanceof FixedVoteWeight)
				((FixedVoteWeight) distanceWeight).setWeights(weights);
			distanceWeight.applyWeight(heap);
		}
		return predictClass();
	}

	private String predictClass() {
		BoxVote box = new BoxVote();
		while (!heap.isEmpty()) {
			KnnHeapElement e = heap.poll();
			box.add(dataset.getInstance(e.getIndex()).getInstanceClass(),
					e.getWeight());
		}
		if (decisionRule instanceof ThresholdMajority){
			if (threshold == -1) {
				return box.getWinner(((ThresholdMajority) decisionRule)
						.getThreshold());
			}else
				return box.getWinner((double)threshold/100d);
			
		}else
			return box.getWinner();

	}

	public int getKnn() {
		return knn;
	}

	public DistanceMetric getDistanceMetric() {
		return distanceMetric;
	}

	public DistanceWeight getDistanceWeight() {
		return distanceWeight;
	}

	public DecisionRule getDecisionRule() {
		return decisionRule;
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}
	
	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	private class BoxVote {
		private ArrayList<String> instanceClasses;
		private ArrayList<Double> votes;

		public BoxVote() {
			instanceClasses = new ArrayList<String>();
			votes = new ArrayList<Double>();
		}

		public String getWinner() {
			double max = Double.MIN_VALUE;
			int index = -1;
			for (int i = 0; i < votes.size(); i++) {
				if (votes.get(i) > max) {
					index = i;
					max = votes.get(i);
				}
			}
			return instanceClasses.get(index);
		}

		public String getWinner(double threshold) {
			double max = Double.MIN_VALUE;
			int index = -1;
			double limit = totalVotes() * threshold;
			for (int i = 0; i < votes.size(); i++) {
				if (votes.get(i) > max && votes.get(i) >= limit) {
					index = i;
					max = votes.get(i);
				}
			}
			return instanceClasses.get(index);
		}

		private double totalVotes() {
			double sum = 0d;
			for (int i = 0; i < votes.size(); i++) {
				sum+=votes.get(i);
			}
			return sum;
		}

		public void add(String instanceClass, double weight) {
			int index = instanceClasses.indexOf(instanceClass);
			if (index == -1) {
				instanceClasses.add(instanceClass);
				votes.add(weight);
			} else {
				votes.set(index, votes.get(index) + weight);
			}

		}
	}

	public static class KnnBuilder {
		private final Dataset dataset;
		private int knn;
		private DistanceMetric distanceMetric;
		private DistanceWeight distanceWeight;
		private DecisionRule decisionRule;

		public KnnBuilder(Dataset dataset) {
			this.dataset = dataset;
		}

		public KnnBuilder knn(int knn) {
			this.knn = knn;
			return this;
		}

		public KnnBuilder distanceMetric(DistanceMetric distanceMetric) {
			this.distanceMetric = distanceMetric;
			return this;
		}

		public KnnBuilder distanceWeight(DistanceWeight distanceWeight) {
			this.distanceWeight = distanceWeight;
			return this;
		}

		public KnnBuilder decisionRule(DecisionRule decisionRule) {
			this.decisionRule = decisionRule;
			return this;
		}

		public KnnClassifier build() {
			validateKnnClassifier();
			return new KnnClassifier(this);
		}

		private void validateKnnClassifier() {
			// Do some basic validations to check
			// if user object does not break any assumption of system
			if (this.distanceMetric == null)
				this.distanceMetric = new EuclideanDistance();
		}
	}

}
