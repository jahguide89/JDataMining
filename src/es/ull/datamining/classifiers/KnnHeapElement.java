package es.ull.datamining.classifiers;

import java.util.Comparator;

public class KnnHeapElement implements Comparable<KnnHeapElement>,
		Comparator<KnnHeapElement> {

	private int index;
	private double distance;
	private double weight;

	public KnnHeapElement(int index, double distance,
			double weight) {
		setIndex(index);
		setDistance(distance);
		setWeight(weight);
	}

	public KnnHeapElement(int index, double distance) {
		this(index, distance, 1d);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int compare(KnnHeapElement o1, KnnHeapElement o2) {
		return (o1.distance < o2.distance) ? -1
				: (o1.distance > o2.distance) ? 1 : 0;
	}

	public int compareTo(KnnHeapElement o) {
		return (this.distance < o.distance) ? -1
				: (this.distance > o.distance) ? 1 : 0;
	}

}
