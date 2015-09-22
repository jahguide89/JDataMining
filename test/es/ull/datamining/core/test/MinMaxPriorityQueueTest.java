package es.ull.datamining.core.test;

import java.util.Random;

import com.google.common.collect.MinMaxPriorityQueue;

public class MinMaxPriorityQueueTest {

	public static void main(String[] args) {

		MinMaxPriorityQueue<Integer> queue = MinMaxPriorityQueue.maximumSize(3)
				.create();

		Random r = new Random();
		int w = 3;
		
		for (int i = 0; i < 10; i++) {
			int nInt = r.nextInt(10);
			queue.offer(nInt);
			System.out.println();
			System.out.println("peso: "+w--);
		}
		
		System.out.println();
		while (!queue.isEmpty()) {
			Integer value = queue.poll();
			System.out.print(value + " ");
		}
	}
}
