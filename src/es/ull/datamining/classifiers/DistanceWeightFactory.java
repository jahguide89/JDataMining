package es.ull.datamining.classifiers;

public class DistanceWeightFactory {
	
	public static DistanceWeight create(String name){
		if(name.equals("Nearest Weight"))
			return new NearestWeight();
		else if(name.equals("Fixed Vote Weight"))
			return new FixedVoteWeight(); 
		else
			return null;
	}

}
