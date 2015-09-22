package es.ull.datamining.classifiers;

import java.util.Iterator;

public class NearestWeight implements DistanceWeight {

	public void applyWeight(KnnHeap heap) {
		Iterator<KnnHeapElement> it = heap.iterator();
		while (it.hasNext()) {
			KnnHeapElement element = (KnnHeapElement) it.next();
			try{
				element.setWeight(1d / element.getDistance());
			}catch (ArithmeticException e) {
				element.setWeight(Double.MAX_VALUE);
			}
		}

	}
	
	@Override
	public String toString() {
		return "Nearest weight";
	}

}
