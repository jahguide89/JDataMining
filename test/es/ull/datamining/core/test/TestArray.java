package es.ull.datamining.core.test;

import java.util.Arrays;

public class TestArray {
	
	public static void main(String[] args) {
		
		String[] a = {"A","B","C","D","E","F","G"};
		String[] b = {"A","B","C","D","E","F","G"};
		
		String[] c = new String[1];
		c[0] = "HELLO";
		
		String stringa = Arrays.toString(a);
		String stringb = Arrays.toString(b);
		String stringc = Arrays.toString(c);
		if (stringa.equals(stringb))	
			System.out.println(stringc);
	}

}
