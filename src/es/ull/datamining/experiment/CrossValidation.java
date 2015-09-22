package es.ull.datamining.experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import es.ull.datamining.core.Dataset;
import es.ull.datamining.core.Instance;

public class CrossValidation{
	
	private Dataset dataset;
	private int folds;
	private Random random;
	
	/**
	 * @param dataset - Origin of data set.
	 * @param folds - Number fold.
	 * @param random - Random predefined.
	 */
	public CrossValidation(Dataset dataset, int folds, Random random) {
		this.dataset = dataset;
		this.folds = folds;
		this.random = random;
	}
	
	public CrossValidation(Dataset dataset, int folds) {
		this(dataset, folds, new Random());
	}
	
	public CrossValidation(Dataset dataset) {
		this(dataset, 10, new Random());
	}
	
	public void init(){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < dataset.nInstances(); i++) {
			indexes.add(i);
		}
		Collections.shuffle(indexes, random);
		
		int boundDown = dataset.nInstances() / folds;
		int boundUp = (dataset.nInstances() / folds) + 1;
		int module = dataset.nInstances() % folds;
				
		for (int i = 0; i < module; i++) {
			dataset.addTestTrainingset(indexes.subList((i * boundUp),
					(i * boundUp) + (boundUp)).toArray(new Integer[boundUp]));
		}
		
		for (int i = module; i < folds; i++) {
			dataset.addTestTrainingset(indexes.subList((i * boundDown),
					(i * boundDown) + (boundDown)).toArray(new Integer[boundDown]));
		}
	}
	
	public Instance[] getTrain(int index){
		ArrayList <Instance> train = new ArrayList<Instance>();
		for (int indexInstance = 0; indexInstance < dataset.nInstances(); indexInstance++) {
			Instance instance = dataset.getInstance(indexInstance);
			if (instance.isTrain(index))
				train.add(instance);
		}
		return train.toArray(new Instance[train.size()]);
	}
	
	public Instance[] getTest(int index){
		ArrayList <Instance> train = new ArrayList<Instance>();
		for (int indexInstance = 0; indexInstance < dataset.nInstances(); indexInstance++) {
			Instance instance = dataset.getInstance(indexInstance);
			if (!instance.isTrain(index))
				train.add(instance);
		}
		return train.toArray(new Instance[train.size()]);
	}

}
