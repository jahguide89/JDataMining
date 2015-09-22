package es.ull.datamining.core.test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {
	
	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(3,
				new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						return o1 > o2 ? -1 : 1;
					}
				});
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			int nInt = r.nextInt(10);
			queue.offer(nInt);
			System.out.print(nInt+" ");
			if (queue.size() > 3)
				queue.remove();
		}
		System.out.println();
		while(!queue.isEmpty()){
			Integer value = queue.poll();
			System.out.print(value+" ");
		}
	}

}
