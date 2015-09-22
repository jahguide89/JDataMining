package es.ull.datamining.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import es.ull.datamining.experiment.TestTrainSet;
import es.ull.datamining.filters.Filter;

public class Dataset implements Instanciable, Serializable{
	private static final long serialVersionUID = -2292288058027808984L;
	private ArrayList<Attribute> attributes;
	private TestTrainSet testTrainSet;

	public Dataset() {
		attributes = new ArrayList<Attribute>();
		testTrainSet = new TestTrainSet(this);
	}
	
	public Dataset deepCopy() throws Exception
    {
        //Serialization of object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
 
        //De-serialization of object
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        return (Dataset) in.readObject();
    }
      

	public TestTrainSet getTestTrainSet() {
		return testTrainSet;
	}

	public void setTestTrainSet(TestTrainSet testTrainSet) {
		this.testTrainSet = testTrainSet;
	}

	public int nInstances() {
		return attributes.get(0).size();
	}

	public int nClasses() {
		int num = 0;
		for (int i = 0; i < nAttributes(); i++) {
			if (attributes.get(i).isClass()) {
				num++;
			}
		}
		return num;
	}

	public int nAttributes() {
		return attributes.size();
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public double[] getAttributesWeights() {
		double[] weights = new double[nAttributes()];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = getAttribute(i).getWeight();
		}
		return weights;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String[] getHeaders() {
		String[] headers = new String[attributes.size()];
		for (int i = 0; i < headers.length; i++) {
			headers[i] = attributes.get(i).getName();
		}
		return headers;
	}

	public void setHeaders(String[] headers) {
		for (int i = 0; i < headers.length; i++) {
			attributes.get(i).setName(headers[i]);
		}
	}

	private void setTypeAttributes(String[] values) {
		for (int i = 0; i < values.length; i++) {
			attributes.add(AttributeFactory.createAttribute(values[i]));
		}
	}

	public void updateDataAttributes() {
		for (int i = 0; i < attributes.size(); i++) {
			attributes.get(i).updateData();
		}
	}

	/**
	 * Load a CSV file into principal memory.
	 * @param fileName File name with CSV extension. 
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public void read(String fileName) throws FileNotFoundException, IOException {

		if (!attributes.isEmpty())
			attributes = new ArrayList<Attribute>();

		CsvReader file;
		String[] headers;
		String[] values;
		try {
			file = new CsvReader(fileName);
			file.readHeaders();
			headers = file.getHeaders();
			// Set attribute type
			file.readRecord();
			values = new String[headers.length];
			for (int i = 0; i < headers.length; i++) {
				values[i] = file.get(i);
			}
			setTypeAttributes(values);
			// Set values
			do {
				for (int i = 0; i < values.length; i++) {
					values[i] = file.get(i);
				}
				addInstance(Instance.parseValues(values));
			} while (file.readRecord());

			setHeaders(headers);
			updateDataAttributes();
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String fileName) {
		CsvWriter file;
		String[] headers = getHeaders();
		boolean exists = new File(fileName).exists();
		try {
			file = new CsvWriter(new FileWriter(fileName, true), ',');
			// Write headers
			if (!exists) {
				for (int i = 0; i < headers.length; i++) {
					file.write(headers[i]);
				}
				file.endRecord();
			}
			// Write values
			for (int i = 0; i < nInstances(); i++) {
				Instance instance = getInstance(i);
				for (int j = 0; j < instance.size(); j++) {
					file.write(instance.getValue(j).toString());
				}
				file.endRecord();
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void show() {
		for (int i = 0; i < this.getHeaders().length; i++) {
			System.out.print(this.getHeaders()[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < this.nInstances(); i++) {
			String out = this.getInstance(i).toString();
			System.out.println(out);
		}
	}

	public void showInfo() {
		System.out.println("[Name,Weight,IsCLas,Max,Min,Mean,StdDev]");
		for (int i = 0; i < this.nAttributes(); i++) {
			System.out.println(getAttribute(i).toString());
		}
	}

	public Attribute getAttribute(int index) {
		return attributes.get(index);
	}

	public int[] getIndexClasses() {
		ArrayList<Integer> classIndexes = new ArrayList<Integer>();
		for (int i = 0; i < nAttributes(); i++) {
			if (getAttribute(i).isClass())
				classIndexes.add(i);
		}
		int[] indexes = new int[classIndexes.size()];
		for (int i = 0; i < classIndexes.size(); i++) {
			indexes[i] = classIndexes.get(i);
		}
		return indexes;
	}

	public Instance getInstance(int index) {
		Instance instance = new Instance(this.nAttributes());
		instance.setClassIndexes(getIndexClasses());
		if (!testTrainSet.isEmpty())
			instance.setTestTrainingSet(testTrainSet
					.getInstanceTestTrainingset(index));
		for (int i = 0; i < instance.size(); i++) {
			try {
				instance.setValue(i, attributes.get(i).getValue(index));
			} catch (IndexOutOfBoundsException ex) {
				instance.setValue(i, new NominalValue("Null"));
			}
		}
		return instance;
	}

	public void addInstance(Instance instance) {
		for (int i = 0; i < this.nAttributes(); i++) {
			attributes.get(i).addValue(instance.getValue(i));
		}
	}

	public void delInstance(int index) {
		for (int i = 0; i < this.nAttributes(); i++) {
			attributes.get(i).delValue(index);
		}
	}

	public String[] getInstanceClasses(int index) {
		ArrayList<String> instanceClasses = new ArrayList<String>();
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).isClass()) {
				Instance tmp = getInstance(index);
				for (int j = 0; j < tmp.size(); j++) {
					if (!tmp.getValue(j).isNumeric()) {
						String e = (String) tmp.getValue(j).getValue();
						instanceClasses.add(e);
					}
				}
			}
		}
		return (String[]) instanceClasses.toArray();
	}

	public void applyFilter(Filter filter) {
		filter.setDataset(this);
		filter.runFilter();
		updateDataAttributes();
	}

	/**
	 * @param percentege
	 *            of Test set
	 */
	public void addTestTrainingset(double percentege) {
		testTrainSet.addTestTrainingset(percentege);
	}
	
	public void addTestTrainingset(double percentege, boolean random) {
		testTrainSet.addTestTrainingset(percentege, random);
	}
	
	public void addTestTrainingset(Integer[] indexes) {
		testTrainSet.addTestTrainingset(indexes);
	}
	
	/**
	 * @param nTrain number of instances used for training set.
	 */
	public void addTestTrainingset(int nTrain) {
		double percentege = nTrain / (double) nInstances();
		testTrainSet.addTestTrainingset(percentege);
	}
	
	public void addTestTrainingset(int nTrain, int nSets) {
		double percentege = nTrain / (double) nInstances();
		testTrainSet.addTestTrainingset(percentege, nSets);
	}

	public void deleteTrainingset() {
		testTrainSet = new TestTrainSet(this);

	}

	public AttributeClass[] getClassesTable() {
		AttributeClasses classes = new AttributeClasses();
		for (int i = 0; i < nInstances(); i++) {
			classes.addInstanceClass(getInstance(i).getInstanceClass());
		}
		return classes.getClasses();

	}
}
