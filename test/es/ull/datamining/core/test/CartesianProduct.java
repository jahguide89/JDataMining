package es.ull.datamining.core.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import polyglot.ast.For;

public class CartesianProduct {

	protected static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
		List<List<T>> resultLists = new ArrayList<List<T>>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<T>());
			return resultLists;
		} else {
			List<T> firstList = lists.get(0);
			List<List<T>> remainingLists = cartesianProduct(lists.subList(1,
					lists.size()));
			for (T condition : firstList) {
				for (List<T> remainingList : remainingLists) {
					ArrayList<T> resultList = new ArrayList<T>();
					resultList.add(condition);
					resultList.addAll(remainingList);
					resultLists.add(resultList);
				}
			}
		}
		return resultLists;
	}

	public static void main(String[] args) {
		
		ArrayList<String []> strings = new ArrayList<String[]>();
		
		strings.add(new String []{"1","2","3","4","5","6","7","8","9"});
		strings.add(new	String []{"A","B"});
		strings.add(new String []{"X","Y","Z"});
		strings.add(new String []{"10","20","30","40","50","60","70","80","90"});
		
		List<List<String>> lists = new ArrayList<List<String>>();
		
		for (int i = 0; i < strings.size(); i++) {
			List<String> e = Arrays.asList(strings.get(i));
			lists.add(e);	
		}
		
		List<List<String>> result = cartesianProduct(lists);
		

		for (int i = 0; i < strings.size(); i++) {
			System.out.println(strings.get(i).length);
		}
		System.out.println(result.size());
		
		for (List<String> tmp : result){
			System.out.println(tmp);
		}

	}

}
