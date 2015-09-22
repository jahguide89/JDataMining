package es.ull.datamining.core.test;

import java.util.Vector;

public class TestContains {
	
	
	
	public static void main(String[] args) {
		Vector<Double> v =  new Vector<Double>();
		Vector<Double> v2 =  new Vector<Double>();
		Vector<Double> v3 =  new Vector<Double>();
		for (int i = 0; i < 5; i++) {
			v.add(new Double(0d));
		}
		for (int i = 0; i < 5; i++) {
			v2.add(new Double(1d));
		}
		v3.addAll(v);
		v3.addAll(v2);
		for (int i = 0; i < v3.size(); i++) {
			System.out.println(v3.get(i));
		}
	}

}
