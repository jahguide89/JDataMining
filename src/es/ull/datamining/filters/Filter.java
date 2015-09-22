package es.ull.datamining.filters;

import es.ull.datamining.core.Dataset;

public abstract class Filter {
	
	Dataset dataset;
		
	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
	
	public abstract void runFilter();
	
	@Override
	public abstract String toString();

}
