package es.ull.datamining.classifiers;

import es.ull.datamining.core.Instance;


public interface Classifier {
	
//	Dataset dataset;
//	Instance instance;
	
//	public Classifier(Dataset dataset, Instance instance) {
//		setDataset(dataset);
//		setInstance(instance);
//	}
	
	/**
	 * @param instance
	 * @return The result of the classification.
	 */
	public String runClassifier(Instance instance);
	
//	public Dataset getDataset() {
//		return dataset;
//	}
//	public void setDataset(Dataset dataset) {
//		this.dataset = dataset;
//	}
//	public Instance getInstance() {
//		return instance;
//	}
//	public void setInstance(Instance instance) {
//		this.instance = instance;
//	}

}
