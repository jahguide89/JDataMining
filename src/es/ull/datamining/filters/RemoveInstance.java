package es.ull.datamining.filters;

public class RemoveInstance extends Filter{
	
	private int index;
	
	public RemoveInstance(int index) {
		this.index = index;
	}

	@Override
	public void runFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
