package es.ull.datamining.core;
/**
 * @author Ivan Ramirez
 *
 */
public interface Instanciable {
	
	/**
	 * @param instance
	 */
	public void addInstance(Instance instance);
	/**
	 * @param index
	 */
	public void delInstance(int index);
	/**
	 * @param index
	 * @return
	 */
	public Instance getInstance(int index);
	
	/**
	 * @return a class data set array.   
	 */
	public String[] getInstanceClasses(int index);
	

}
