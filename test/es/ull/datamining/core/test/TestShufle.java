package es.ull.datamining.core.test;

import java.util.ArrayList;
import java.util.Collections;

public class TestShufle {
	
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			a.add(i);
		}
		System.out.println("----------------------------");
		System.out.println(a.toString());
		System.out.println("----------------------------");
		Collections.shuffle(a);
		System.out.println("----------------------------");
		System.out.println(a.toString());
		System.out.println("----------------------------");
		System.out.println(a.subList(0, 9).toString());
		System.out.println("----------------------------");
		System.out.println(a.subList(10, 20).toString());
		System.out.println("----------------------------");
	}

}
