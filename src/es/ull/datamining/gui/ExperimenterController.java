package es.ull.datamining.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import es.ull.datamining.classifiers.Classifier;
import es.ull.datamining.classifiers.DecisionRuleFactory;
import es.ull.datamining.classifiers.DistanceMetric;
import es.ull.datamining.classifiers.DistanceWeightFactory;
import es.ull.datamining.classifiers.KnnClassifier;
import es.ull.datamining.classifiers.NaiveBayes;
import es.ull.datamining.core.Dataset;
import es.ull.datamining.experiment.ConfusionMatrix;
import es.ull.datamining.experiment.CrossValidation;
import es.ull.datamining.util.Util;

public class ExperimenterController implements IController {

	// Views
	private ExperimenterFrame experimenterFrame;
	private JKnnOptionsFrame knnOptionsFrame;
	private AssignFixedWeightFrame assignFixedWeightFrame;

	// Models
	private Dataset dataset;
	private String pathDataset;
	private boolean DatasetReady;
	private Dataset trainSet;
	private String pathTrainSet;
	private boolean trainDataReady;
	private Dataset testSet;
	private String pathTestSet;
	private boolean testDataReady;
	private String classifierStr;
	private Classifier classifier;
	private int k;
	private DistanceMetric distanceMetric;
	private String distanceWeightStr;
	private int[] attributesWeights;
	private String decisionRuleStr;
	private int threshold;
	private int folds;
	private ConfusionMatrix confusionMatrix;

	public ExperimenterController() {
		experimenterFrame = new ExperimenterFrame(this);
		experimenterFrame.setVisible(true);
	}

	public void loadDataset() {
		dataset = new Dataset();
		pathDataset = Util.getOpenFileName();
		try {
			dataset.read(pathDataset);
			setDatasetReady(true);
		} catch (FileNotFoundException e) {
			setDatasetReady(false);
			e.printStackTrace();
		} catch (IOException e) {
			setDatasetReady(false);
			e.printStackTrace();
		}

	}

	public void loadTrainSet() {
		trainSet = new Dataset();
		pathTrainSet = Util.getOpenFileName();
		try {
			trainSet.read(pathTrainSet);
			setTrainDataReady(true);
		} catch (FileNotFoundException e) {
			setTrainDataReady(false);
			e.printStackTrace();
		} catch (IOException e) {
			setTrainDataReady(false);
			e.printStackTrace();
		}

	}

	public void loadTestSet() {
		testSet = new Dataset();
		setPathTestSet(Util.getOpenFileName());
		try {
			testSet.read(pathTrainSet);
			setTestDataReady(true);
		} catch (FileNotFoundException e) {
			setTestDataReady(false);
			e.printStackTrace();
		} catch (IOException e) {
			setTestDataReady(false);
			e.printStackTrace();
		}

	}

	public void setClassifier(String classifierStr) {
		if (classifierStr.equals("K - Nearest Neighbor")) {
			JKnnOptionsFrame knnOptionsFrame = new JKnnOptionsFrame(this);
			knnOptionsFrame.setVisible(true);
		}
		this.classifierStr = classifierStr;
	}

	public void setAttributesWeights(int k) {
		assignFixedWeightFrame = new AssignFixedWeightFrame(k);
		this.attributesWeights = assignFixedWeightFrame.getWeights();
	}

	public void setK(int k) {
		this.k = k;
	}

	public void setClassifierStr(String classifierStr) {
		this.classifierStr = classifierStr;
	}

	public void setDistanceMetric(DistanceMetric distanceMetric) {
		this.distanceMetric = distanceMetric;
	}

	public void setDistanceWeightStr(String distanceWeightStr) {
		this.distanceWeightStr = distanceWeightStr;
	}

	public void setAttributesWeights(int[] attributesWeights) {
		this.attributesWeights = attributesWeights;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setDecisionRuleStr(String distanceRuleStr) {
		this.decisionRuleStr = distanceRuleStr;
	}

	public boolean isTrainDataReady() {
		return trainDataReady;
	}

	public void setTrainDataReady(boolean trainDataReady) {
		this.trainDataReady = trainDataReady;
	}

	public String getPathTrainSet() {
		return pathTrainSet;
	}

	public void setPathTrainSet(String pathTrainSet) {
		this.pathTrainSet = pathTrainSet;
	}

	public String getPathDataset() {
		return pathDataset;
	}

	public void setPathDataset(String pathDataset) {
		this.pathDataset = pathDataset;
	}

	public boolean isTestDataReady() {
		return testDataReady;
	}

	public boolean isDatasetReady() {
		return DatasetReady;
	}

	public void setDatasetReady(boolean datasetReady) {
		DatasetReady = datasetReady;
	}

	public void setTestDataReady(boolean testDataReady) {
		this.testDataReady = testDataReady;
	}

	public String getPathTestSet() {
		return pathTestSet;
	}

	public void setPathTestSet(String pathTestSet) {
		this.pathTestSet = pathTestSet;
	}

	public void viewTrainSet() {
		ViewDatasetFrame viewDatasetFrame = new ViewDatasetFrame(trainSet);
		viewDatasetFrame.setVisible(true);
	}

	public void viewTestSet() {
		ViewDatasetFrame viewDatasetFrame = new ViewDatasetFrame(testSet);
		viewDatasetFrame.setVisible(true);
	}

	public void viewDataset() {
		ViewDatasetFrame viewDatasetFrame = new ViewDatasetFrame(dataset);
		viewDatasetFrame.setVisible(true);
	}

	public void saveTrainSet() {
		trainSet.write(Util.getSaveFileName("Save Train Set"));
	}

	public void saveTestSet() {
		testSet.write(Util.getSaveFileName("Save Test Set"));
	}

	public void loadTrainTestSet() {
		// Load test set
		try {
			testSet = dataset.deepCopy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < testSet.nInstances(); i++) {
			if (!testSet.getInstance(i).isTrain(0)) {
				testSet.delInstance(i);
			}
		}
		// Load train set
		try {
			trainSet = dataset.deepCopy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < trainSet.nInstances(); i++) {
			if (trainSet.getInstance(i).isTrain(0)) {
				trainSet.delInstance(i);
			}
		}
	}

	public void generateCrossValidation(int folds) {
		// TODO Auto-generated method stub
		CrossValidation crossValidation = new CrossValidation(dataset, folds);
		crossValidation.init();
		this.folds = folds;
	}

	public void generateCrossValidation(int folds, Random random) {
		// TODO Auto-generated method stub
		CrossValidation crossValidation = new CrossValidation(dataset, folds,
				random);
		crossValidation.init();
		this.folds = folds;
	}

	public void generateTestTrainSet(double percentage) {
		// TODO Auto-generated method stub
		dataset.addTestTrainingset(percentage);
	}

	public void generateTestTrainSet(double percentage, Random random) {
		// TODO Auto-generated method stub
		dataset.getTestTrainSet().setRandom(random);
		dataset.addTestTrainingset(percentage);
	}

	public void generateTestTrainSet(double percentage, boolean random) {
		// TODO Auto-generated method stub
		dataset.addTestTrainingset(percentage, random);
	}

	public void runExperimenter() {
		// TODO Auto-generated method stub
		System.out.println("Run Experimenter");
		if (experimenterFrame.configurationPane.loadSetPane.isVisible()) {
			confusionMatrix = new ConfusionMatrix(trainSet.getClassesTable());
			// Create classifier
			if (classifierStr.equals("K - Nearest Neighbor")) {
				classifier = new KnnClassifier.KnnBuilder(trainSet)
						.knn(k)
						.distanceMetric(distanceMetric)
						.distanceWeight(
								DistanceWeightFactory.create(distanceWeightStr))
						.decisionRule(
								DecisionRuleFactory.create(decisionRuleStr))
						.build();
				if (distanceWeightStr.equals("Fixed Vote Weight"))
					((KnnClassifier) classifier).setWeights(attributesWeights);
				if (decisionRuleStr.equals("Threshold Majority"))
					((KnnClassifier) classifier).setThreshold(threshold);
			} else if (classifierStr.equals("Naive Bayes")) {
				classifier = new NaiveBayes(trainSet);
			}
			// run classifier
			for (int i = 0; i < testSet.nInstances(); i++) {
				String predict = testSet.getInstance(i).getInstanceClass();
				String prediction = classifier.runClassifier(testSet
						.getInstance(i));
				confusionMatrix.addPrediction(predict, prediction);
			}
		} else {

			confusionMatrix = new ConfusionMatrix(dataset.getClassesTable());

			// cross validation
			if (experimenterFrame.configurationPane.newSetPane.rdbtnCrossValidation
					.isSelected()) {
				for (int i = 0; i < folds; i++) {
					try {
						trainSet = dataset.deepCopy();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int j = 0; j < trainSet.nInstances(); j++) {
						if (trainSet.getInstance(j).isTrain(i)) {
							trainSet.delInstance(j);
						}
					}
					try {
						testSet = dataset.deepCopy();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int j = 0; j < testSet.nInstances(); j++) {
						if (!testSet.getInstance(j).isTrain(i)) {
							testSet.delInstance(j);
						}
					}
					// Create classifier
					if (classifierStr.equals("K - Nearest Neighbor")) {
						classifier = new KnnClassifier.KnnBuilder(trainSet)
								.knn(k)
								.distanceMetric(distanceMetric)
								.distanceWeight(
										DistanceWeightFactory
												.create(distanceWeightStr))
								.decisionRule(
										DecisionRuleFactory
												.create(decisionRuleStr))
								.build();
						if (distanceWeightStr.equals("Fixed Vote Weight"))
							((KnnClassifier) classifier)
									.setWeights(attributesWeights);
						if (decisionRuleStr.equals("Threshold Majority"))
							((KnnClassifier) classifier)
									.setThreshold(threshold);
					} else if (classifierStr.equals("Naive Bayes")) {
						classifier = new NaiveBayes(trainSet);
					}
					// run classifier
					for (int k = 0; k < testSet.nInstances(); k++) {
						String predict = testSet.getInstance(k)
								.getInstanceClass();
						String prediction = classifier.runClassifier(testSet
								.getInstance(k));
						confusionMatrix.addPrediction(predict, prediction);
					}

				}
				// simple validation
			} else if (experimenterFrame.configurationPane.newSetPane.rdbtnSplit
					.isSelected()) {
				// Create classifier
				if (classifierStr.equals("K - Nearest Neighbor")) {
					classifier = new KnnClassifier.KnnBuilder(trainSet)
							.knn(k)
							.distanceMetric(distanceMetric)
							.distanceWeight(
									DistanceWeightFactory
											.create(distanceWeightStr))
							.decisionRule(
									DecisionRuleFactory.create(decisionRuleStr))
							.build();
					if (distanceWeightStr.equals("Fixed Vote Weight"))
						((KnnClassifier) classifier)
								.setWeights(attributesWeights);
					if (decisionRuleStr.equals("Threshold Majority"))
						((KnnClassifier) classifier).setThreshold(threshold);
				} else if (classifierStr.equals("Naive Bayes")) {
					classifier = new NaiveBayes(trainSet);
				}
				// run classifier
				for (int i = 0; i < testSet.nInstances(); i++) {
					String predict = testSet.getInstance(i).getInstanceClass();
					String prediction = classifier.runClassifier(testSet
							.getInstance(i));
					confusionMatrix.addPrediction(predict, prediction);
				}
			}
		}
		showResults();

	}

	public void showResults() {
		// TODO
		StringBuilder result = new StringBuilder();

		// Info analysis

		if (!experimenterFrame.configurationPane.loadSetPane.isVisible()) {
			result.append("Dataset: ").append("\n");
			result.append("\t").append(pathDataset).append("\n");
			if (experimenterFrame.configurationPane.newSetPane.rdbtnCrossValidation
					.isSelected()) {
				result.append("Cross Validation").append("\n");
				result.append("\tFolds: ").append(folds).append("\n");
			} else {
				result.append("Simple Validation").append("\n");
				result.append("\tTrain set percentage: ")
						.append(experimenterFrame.configurationPane.newSetPane.spnTrainPercent
								.getValue()).append("\n");
				if (experimenterFrame.configurationPane.newSetPane.rdbtnOrder
						.isSelected())
					result.append("\tOrder preserved").append("\n");
				else
					result.append("\tRandom Sample").append("").append("\n");
			}
			if (experimenterFrame.configurationPane.newSetPane.chkSeed
					.isSelected()) {
				result.append("Seed selected").append("\n");
				result.append("\tSeed value: ")
						.append(experimenterFrame.configurationPane.newSetPane.txtSeed
								.getText()).append("\n");
			}
		} else {
			result.append("Trainset: ").append("\n");
			result.append("\t").append(pathTrainSet).append("\n");
			result.append("Testset: ").append("\n");
			result.append("\t").append(pathTestSet).append("\n");
		}

		result.append("\nClassifier: ").append(classifierStr).append("\n");
		if (classifier instanceof KnnClassifier) {
			result.append("\nOptions: ").append("\n");
			result.append("\tK: ").append(k).append("\n");
			result.append("\tMetric: ").append(distanceMetric).append("\n");
			result.append("\tWeights: ").append(distanceWeightStr).append("\n");
			if (distanceWeightStr.equals("Fixed Vote Weight")) {
				for (int i = 0; i < attributesWeights.length; i++) {
					result.append("\t\t");
					result.append(attributesWeights[i]).append("\n");
				}
			}
			result.append("\tVote rule: ").append(decisionRuleStr).append("\n");
			if (decisionRuleStr.equals("Threshold Majority")) {
				result.append("\t\tThreshold: ").append(threshold).append("%")
						.append("\n");
			}
		}
		result.append("\n\n");
		// Confusion Matrix
		result.append(confusionMatrix.toString()).append("\n");
		// Predictive Value
		result.append("Predictive Value: ")
				.append(confusionMatrix.getPositivePredictiveValue() * 100 + "")
				.append("%");

		experimenterFrame.resultsTextArea.setText(result.toString());
	}

}
