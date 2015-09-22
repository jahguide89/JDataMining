package es.ull.datamining.classifiers;

import java.util.Iterator;

public class FixedVoteWeight implements DistanceWeight {
	
	private int[] weights;
	
	public FixedVoteWeight() {
		this.weights = null;
	}
	
	public FixedVoteWeight(int[] weights) {
		this.weights = weights;
	}

	public void applyWeight(KnnHeap heap) {
		int index = 0;
		//Assign weights automatically 
		if (weights == null){
			initWeights(heap.size());
		}
		Iterator<KnnHeapElement> it = heap.iterator();
		while (it.hasNext()) {
			KnnHeapElement element = (KnnHeapElement) it.next();
			element.setWeight(weights[index]);
			index++;
		}

	}
	
	private void initWeights(int size) {
		weights = new int[size];
		int w = weights.length;
		for (int i = 0; i < weights.length; i++) {
			weights[i] = w--;
		}
		
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}

	@Override
	public String toString() {
		return "Fixed vote weight";
	}

}
