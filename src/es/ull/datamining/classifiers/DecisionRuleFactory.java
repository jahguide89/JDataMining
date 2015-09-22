package es.ull.datamining.classifiers;

public class DecisionRuleFactory {
	
	public static DecisionRule create(String name){
		if(name.equals("Threshold Majority"))
			return new ThresholdMajority();
		else
			return null;
	}

}
