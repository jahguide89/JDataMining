package es.ull.datamining.experiment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import es.ull.datamining.core.Dataset;

public class TestTrainSet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150185836484997397L;
	/**
	 * 
	 */
	private final Dataset dataset;
	private ArrayList<boolean[]> sets;
	private Random random;

	public TestTrainSet(Dataset dataset) {
		this.dataset = dataset;
		sets = new ArrayList<boolean[]>();
		random = new Random();
	}

	public TestTrainSet(Dataset dataset, Random random) {
		this.dataset = dataset;
		sets = new ArrayList<boolean[]>();
		this.random = random;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public boolean[] getInstanceTestTrainingset(int index) {
		boolean[] instanceTestTrainingset = new boolean[sets.size()];
		for (int i = 0; i < instanceTestTrainingset.length; i++) {
			instanceTestTrainingset[i] = (sets.get(i)[index]);
		}
		return instanceTestTrainingset;
	}

	public boolean[] get(int index) {
		return sets.get(index);
	}

	public void set(int index, boolean[] set) {
		sets.set(index, set);
	}
	
	public boolean isEmpty() {
		return sets.isEmpty();
	}

	private int nextIndex(boolean[] set, int bound) {
		int index;
		do {
			index = random.nextInt(bound);
		} while (set[index]);
		return index;
	}

	/**
	 * @param percentege
	 *            of Test set
	 */	
	public void addTestTrainingset(double percentege) {
		addTestTrainingset(percentege, true);
	}

	public void addTestTrainingset(double percentege, boolean random) {
		boolean[] set = new boolean[this.dataset.nInstances()];
		int bound = (int) (percentege * this.dataset.nInstances());
		int index;
		for (int i = 0; i < bound; i++) {
			if (random)
				index = nextIndex(set, this.dataset.nInstances());
			else
				index = i;
			set[index] = true;
		}
		sets.add(set);
	}
	
	public void addTestTrainingset(Integer[] indexes) {
		boolean[] set = new boolean[this.dataset.nInstances()];
		for (int i = 0; i < indexes.length; i++) {
			set[indexes[i]] = true;
		}
		sets.add(set);
	}

	void addTestTrainingset(double[] percenteges) {
		for (int i = 0; i < percenteges.length; i++) {
			addTestTrainingset(percenteges[i]);
		}
	}

	public void addTestTrainingset(double percentege, int nSets) {
		for (int i = 0; i < nSets; i++) {
			addTestTrainingset(percentege);
		}
	}

	void updateTestTrainingset(double percentege) {
		updateTestTrainingset(percentege, 0);
	}

	void updateTestTrainingsets(double[] percenteges) {
		for (int i = 0; i < percenteges.length; i++) {
			updateTestTrainingset(percenteges[i], i);
		}
	}

	void updateTestTrainingset(double percentege, int index) {
		boolean[] set = new boolean[this.dataset.nInstances()];
		int bound = (int) (percentege * this.dataset.nInstances());
		int nextIndex;
		for (int i = 0; i < bound; i++) {
			nextIndex = nextIndex(set, bound);
			set[nextIndex] = true;
		}
		sets.set(index, set);
	}

	void delTestTrainingset(int index) {
		sets.remove(index);
	}

}