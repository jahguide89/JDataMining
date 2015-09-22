package es.ull.datamining.util;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Util {
	/**
	 * @return
	 */
	public static String getOpenFileName() {
		JFileChooser chooser = new JFileChooser("resources/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public static String getSaveFileName() {
		JFileChooser chooser = new JFileChooser("resources/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * @param message
	 * @return
	 */
	public static String getSaveFileName(String message) {
		JFileChooser chooser = new JFileChooser("resources/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showDialog(null,message);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * @param string
	 * @return
	 */
	public static boolean isNumeric(String string) {
		try  
		  {  
		    Double.parseDouble(string);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		    return false;  
		  }  
		  return true;  
	}

	/**
	 * @param vector
	 * @return
	 */
	public static int getIndexMax(double[] vector) {
		int indexMax = -1;
		double max = Double.MIN_NORMAL;
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] > max){
				max = vector[i];
				indexMax = i;
			}		
		}
		return indexMax;
	}

	/**
	 * @param vector
	 */
	public static void normalizeVector(double[] vector) {
		double sum = 0;
		for (int i = 0; i < vector.length; i++) {
			sum+=vector[i];
		}
		for (int i = 0; i < vector.length; i++) {
			vector[i] /= sum;
		}
	}

}
