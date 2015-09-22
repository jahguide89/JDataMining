package es.ull.datamining.core.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
	
	public static final String COMMA = ",";
	
	private String csvFile = null;
	private String cvsSplit = null;
	private BufferedReader bufferedReader = null;
	private FileReader fileReader = null;
	private ArrayList<String[]> values;
	
	
	public CsvReader(String csvFile) {
		this.csvFile = csvFile;
		this.cvsSplit = COMMA;
		this.values = new ArrayList<String[]>(); 
		try {
			fileReader = new FileReader(csvFile);
			bufferedReader = new BufferedReader(fileReader);
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initialize() {
		String line = "";
		try {
			while ((line = bufferedReader.readLine()) != null) {
				values.add(line.split(cvsSplit));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
