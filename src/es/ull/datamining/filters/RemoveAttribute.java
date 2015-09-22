package es.ull.datamining.filters;

public class RemoveAttribute extends Filter {
	
	private int index;
	private String name;
	
	public RemoveAttribute(int index) {
		setIndex(index);
	}
	
	public RemoveAttribute(String name) {
		setName(name);
		for (int i = 0; i < dataset.nAttributes(); i++) {
			if (dataset.getAttribute(i).getName().equals(name)) {
				setIndex(i);
			}
		}
	}

	@Override
	public void runFilter() {
		try{
			dataset.getAttributes().remove(index);
		}catch(IndexOutOfBoundsException ex){
			System.out.println("Atributo no encontrado");
		}

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return RemoveAttribute.class.getName();
	}

}
