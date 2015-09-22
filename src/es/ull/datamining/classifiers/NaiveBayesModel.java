package es.ull.datamining.classifiers;

import es.ull.datamining.core.Attribute;
import es.ull.datamining.core.AttributeClass;
import es.ull.datamining.core.AttributeNominal;
import es.ull.datamining.core.Dataset;
import es.ull.datamining.core.Value;
import es.ull.datamining.util.Probability;

public class NaiveBayesModel {

	private final Dataset dataset;

	private double[][] means;
	private double[][] devs;
	private double[] classProbs;
	private AttributeClass[] classes;

	public NaiveBayesModel(Dataset dataset) {
		this.dataset = dataset;
		buildModel();
	}

	private void buildModel() {
		// Build class table
		classes = dataset.getClassesTable();

		// Get probabilities each classes
		classProbs = getClassProbabilities();

		// update conditionals means
		means = getConditionalMeans();

		// update conditionals standard deviations
		devs = getConditionalStdDevs();
	}

	private double[][] getConditionalStdDevs() {
		String[] instancesClass = getInstancesClass();

		double[][] array = new double[classes.length][dataset.nAttributes()];

		for (int indexClass = 0; indexClass < array.length; indexClass++) {
			String actualClass = classes[indexClass].getValue();
			for (int indexAttribute = 0; indexAttribute < array[indexClass].length; indexAttribute++) {
				Attribute actualAttribute = dataset
						.getAttribute(indexAttribute);
				if ((!actualAttribute.isClass())
						&& (actualAttribute.isNumeric())) {
					double[] numericAttribute = getNumericAttribute(indexAttribute);
					array[indexClass][indexAttribute] = Probability
							.conditionalStandardDeviation(numericAttribute,
									instancesClass, actualClass,
									getMean(indexClass, indexAttribute));
				}
			}
		}
		return array;
	}

	private double[][] getConditionalMeans() {

		String[] instancesClass = getInstancesClass();

		double[][] array = new double[classes.length][dataset.nAttributes()];

		for (int indexClass = 0; indexClass < array.length; indexClass++) {
			String actualClass = classes[indexClass].getValue();
			for (int indexAttribute = 0; indexAttribute < array[indexClass].length; indexAttribute++) {
				Attribute actualAttribute = dataset
						.getAttribute(indexAttribute);
				if ((!actualAttribute.isClass())
						&& (actualAttribute.isNumeric())) {
					double[] numericAttribute = getNumericAttribute(indexAttribute);
					array[indexClass][indexAttribute] = Probability
							.conditionalMean(numericAttribute, instancesClass,
									actualClass);
				}
			}
		}
		return array;
	}

	private double[] getNumericAttribute(int indexAttribute) {
		double[] numericAttribute = new double[dataset.nInstances()];
		for (int i = 0; i < dataset.nInstances(); i++) {
			numericAttribute[i] = dataset.getAttribute(indexAttribute)
					.getValue(i).toDouble();
		}
		return numericAttribute;
	}

	private String[] getInstancesClass() {
		String[] array = new String[dataset.nInstances()];
		for (int i = 0; i < dataset.nInstances(); i++) {
			array[i] = dataset.getInstance(i).getInstanceClass();
		}
		return array;
	}

	private double[] getClassProbabilities() {
		double[] probs = new double[classes.length];
		double size = (double) dataset.nInstances();
		for (int i = 0; i < probs.length; i++) {
			probs[i] = classes[i].getFrequency() / size;
		}
		return probs;
	}
	
	private String[] getNominalAttribute(int indexAttribute) {
		String[] nominalAttribute = new String[dataset.nInstances()];
		for (int i = 0; i < dataset.nInstances(); i++) {
			nominalAttribute[i] = dataset.getAttribute(indexAttribute)
					.getValue(i).toString();
		}
		return nominalAttribute;
	}

	public double[] getClassProbs() {
		return classProbs;
	}

	public double getMean(int indexClass, int indexAttribute) {
		return means[indexClass][indexAttribute];
	}

	public double getDev(int indexClass, int indexAttribute) {
		return devs[indexClass][indexAttribute];
	}

	public double getClassProb(int indexAttribute) {
		return classProbs[indexAttribute];
	}

	public String getClassName(int indexClass) {
		return classes[indexClass].getValue();
	}
	
	public int nClasses() {
		return classes.length;
	}

	public double probPost(int indexClass, int indexAttribute, Value value) {
		if (value.isNumeric()) {
			double mean = getMean(indexClass, indexAttribute);
			double stdDev = getDev(indexClass, indexAttribute);
			return Probability.normalDens(value.toDouble(), mean, stdDev);
		} else {
			String[] nominalAttribute = getNominalAttribute(indexAttribute);
			String[] instancesClass = getInstancesClass();
			int nAttributeClass = ((AttributeNominal) dataset
					.getAttribute(indexAttribute)).getClasses().size();
			return Probability.condProbLaplaceSmooth(nominalAttribute,
					instancesClass, value.toString(), getClassName(indexClass),
					nAttributeClass);
		}
	}

	

}
