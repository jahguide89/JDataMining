package es.ull.datamining.filters;

import es.ull.datamining.core.Instance;

public class AddInstance extends Filter{
	
	private Instance newInstance;
	
	public AddInstance(Instance newInstance) {
		this.setNewInstance(newInstance);
	}

	@Override
	public void runFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return AddInstance.class.getName();
	}

	public Instance getNewInstance() {
		return newInstance;
	}

	public void setNewInstance(Instance newInstance) {
		this.newInstance = newInstance;
	}

}
