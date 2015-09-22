package es.ull.datamining.gui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import es.ull.datamining.classifiers.Classifier;
import es.ull.datamining.classifiers.DecisionRuleFactory;
import es.ull.datamining.classifiers.DistanceMetric;
import es.ull.datamining.classifiers.DistanceWeightFactory;
import es.ull.datamining.classifiers.FixedVoteWeight;
import es.ull.datamining.classifiers.KnnClassifier;
import es.ull.datamining.classifiers.NaiveBayes;
import es.ull.datamining.core.Dataset;
import es.ull.datamining.core.Instance;
import es.ull.datamining.filters.Filter;
import es.ull.datamining.filters.Normalize;
import es.ull.datamining.util.Util;

public class AnalysisController implements IController {
	
	private boolean dataReady;
	
	//Models
	private Dataset dataset;
	private Filter filter;
	private Classifier classifier; 
	private Instance[] predictionSet;
	private String[] predictions;

	private ArrayList<String> filters;

	//Views
	private ViewDatasetFrame viewDatasetFrame;
	private AnalysisFrame analysisFrame;
	private IOptionFilterFrame optionFilterFrame;
	private PredictionSetEditorFrame predictionSetEditorFrame;  
	private AssignFixedWeightFrame assignFixedWeightFrame;
	
	
	public AnalysisController(Dataset dataset) {
		analysisFrame = new AnalysisFrame(this);
		analysisFrame.setVisible(true);
		this.dataset = dataset;
		setDataReady(false);
	}

	public void loadDataset() {
		try {
			dataset.read(analysisFrame.panelDataset.txtDatasetPath.getText());
			setDataReady(true);
		} catch (FileNotFoundException e) {
			setDataReady(false); 
			e.printStackTrace();
		} catch (IOException e) {
			setDataReady(false);
			e.printStackTrace();
		}
	}

	public void saveDataset() {
		dataset.write(Util.getSaveFileName());
		
	}

	public void showDataset() {
		viewDatasetFrame = new ViewDatasetFrame(dataset);
		viewDatasetFrame.setVisible(true);
	}
	
	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public void showOptionFilterFrame() {
		if (optionFilterFrame == null)
			optionFilterFrame = new NormalizeOptionFilterFrame((Normalize)filter);
		((NormalizeOptionFilterFrame) optionFilterFrame).setVisible(true);
	}

	public void applyFilter() {
		if (filters == null){
			filters = new ArrayList<String>();
		}
		filters.add(filter.toString());
		if (optionFilterFrame == null)
			dataset.applyFilter(filter);
		else {
			((Normalize)filter).setScale(((NormalizeOptionFilterFrame) optionFilterFrame).getScale());
			((Normalize)filter).setTranslation(((NormalizeOptionFilterFrame) optionFilterFrame).getTranslation());
			dataset.applyFilter(filter);
		}
	}
	
	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void applyClassifier(int selected, int knn,
			DistanceMetric distanceMetric, String distanceWeight,
			String decisionRule) {
		if (selected == 1){
			classifier = new KnnClassifier.KnnBuilder(dataset)
					.knn(knn)
					.distanceMetric(distanceMetric)
					.distanceWeight(DistanceWeightFactory.create(distanceWeight))
					.decisionRule(DecisionRuleFactory.create(decisionRule))
					.build();
			if (distanceWeight.equals("Fixed Vote Weight"))
				((KnnClassifier) classifier).setWeights(assignFixedWeightFrame.getWeights());
			if (decisionRule.equals("Threshold Majority"))
				((KnnClassifier) classifier).setThreshold(analysisFrame.confPane.getThreshold());
		}else{
			classifier = new NaiveBayes(dataset);
		}
		
	}
	
	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	public boolean isDataReady() {
		return dataReady;
	}

	public void setDataReady(boolean dataReady) {
		this.dataReady = dataReady;
	}

	public IOptionFilterFrame getOptionFilterFrame() {
		return optionFilterFrame;
	}

	public void setOptionFilterFrame(IOptionFilterFrame optionFilterFrame) {
		this.optionFilterFrame = optionFilterFrame;
	}

	public void updateTestTrainingset(double percentege) {
		if (dataset.getTestTrainSet().isEmpty())
			dataset.addTestTrainingset(percentege);
		else{
			dataset.deleteTrainingset();
			dataset.addTestTrainingset(percentege);
		}
	}

	public void runAlgorithm() {
		predictions = new String[predictionSet.length];
		for (int i = 0; i < predictionSet.length; i++) {
			predictionSet[i].setClassIndexes(dataset.getIndexClasses());
			predictions[i] = classifier.runClassifier(predictionSet[i]);
		}
		showResults();
	}	
	
	public void showResults() {
		StringBuilder result = new StringBuilder();
		
		//Info analysis
		result.append("Dataset: ").append(analysisFrame.panelDataset.txtDatasetPath.getText()).append("<br>");
		result.append("Attributes weights: ");//.append(analysisFrame.txtDatasetPath.getText()).append("<br>");
		for (int i = 0; i < dataset.nAttributes(); i++) {
			result.append(dataset.getAttribute(i).getWeight()+" ");
		}
		result.append("<br>");
		if (filters != null){
			result.append("Filters:").append("<br>");
			result.append("<blockquote>");
			for (int i = 0; i < filters.size(); i++) {
				result.append(filters.get(i)).append("<br>");
			}
			result.append("</blockquote>");
		}
		result.append("Classes:").append("<br>");
		result.append("<blockquote>");
		for (int i = 0; i < dataset.getClassesTable().length; i++) {
			result.append(dataset.getClassesTable()[i].getValue()).append("<br>");
		}
		result.append("</blockquote>");
		if (analysisFrame.txtPredictionSet.getText().equals("")){
			result.append("Prediction Set: ").append("Create manually").append("<br>");
		}else
			result.append("Prediction Set: ").append(analysisFrame.txtPredictionSet.getText()).append("<br>");
		result.append("Classifier: ").append(analysisFrame.cbxClassifier.getSelectedItem()).append("<br>");
		if (classifier instanceof KnnClassifier){
			result.append("Options: ").append("<br>");
			result.append("<blockquote>");
			result.append("K: ").append(analysisFrame.confPane.getKnn()).append("<br>");
			result.append("Metric: ").append(analysisFrame.confPane.getMetric().toString()).append("<br>");
			result.append("Weights: ").append(analysisFrame.confPane.getWeight()).append("<br>");
			if (analysisFrame.confPane.getWeight().equals("Fixed Vote Weight")){
				result.append("<blockquote>");
				for (int i = 0; i < assignFixedWeightFrame.getWeights().length; i++) {
					result.append(assignFixedWeightFrame.getWeights()[i]).append("<br>");
				}
				result.append("</blockquote>");
			}
			result.append("Vote rule: ").append(analysisFrame.confPane.getRule()).append("<br>");
			if (analysisFrame.confPane.getRule().equals("Threshold Majority")) {
				result.append("<blockquote>");
				result.append("Threshold: ").append(analysisFrame.confPane.getThreshold()).append("%").append("<br>");
				result.append("</blockquote>");
			}
			result.append("</blockquote>");
		}
		
		//Result classifier
		result.append("<table>");
		result.append("<thead>").append("<tr>");
		result.append("<th>").append("Instance").append("</th>");
		result.append("<th>").append("Prediction class").append("</th>");
		result.append("</tr>").append("</thead>");
		result.append("<tbody>");
		for (int i = 0; i < predictionSet.length; i++) {
			result.append("<tr>");
			result.append("<td>").append(predictionSet[i].toString()).append("</td>");
			result.append("<td>").append(predictions[i]).append("</td>");
			result.append("</tr>");
		}
		result.append("</tbody>");
		result.append("</table>");
		
		analysisFrame.resultsTextArea.setText(result.toString());
	}
	
	public void createPredictionSetEditor() {
		this.predictionSetEditorFrame = new PredictionSetEditorFrame(this);
	}

	public void editPredictionSetEditor() {
		if (predictionSetEditorFrame == null)
			createPredictionSetEditor();
		else
			predictionSetEditorFrame.updateGUI(predictionSetEditorFrame.getText());
		
	}

	public void dropPredictionSetEditor() {
		predictionSetEditorFrame = null;
	}

	public void updatePredictionSetEditor() {
		String[] instances = predictionSetEditorFrame.getInstances();
		if (instances == null)
			predictionSetEditorFrame = null;
		else{
			predictionSet = new Instance[instances.length];
			for (int i = 0; i < instances.length; i++) {
				String[] instance = instances[i].split(",");
				predictionSet[i] = Instance.parseValues(instance); 
			}
		}
	}

	public void loadPredictionSet() {
//		String fileName = Util.getOpenFileName();
		String fileName = analysisFrame.txtPredictionSet.getText();
		if (predictionSet != null)
			predictionSet = null;
		ArrayList <Instance> instances = new ArrayList<Instance>();
		CsvReader file;
		try {
			file = new CsvReader(fileName);
			// Set values
			while (file.readRecord()){
				String[] instance = file.getValues();
				instances.add(Instance.parseValues(instance));
			}
			file.close();
			predictionSet = instances.toArray(new Instance[instances.size()]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void savePredictionSet() {
		String fileName = Util.getSaveFileName();
		CsvWriter file;
		try {
			file = new CsvWriter(new FileWriter(fileName, true), ',');
			// Write values
			for (int i = 0; i < predictionSet.length; i++) {
				Instance instance = predictionSet[i];
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

	public void showVoteWeightFrame(int k) {
		assignFixedWeightFrame = new AssignFixedWeightFrame(k);
	}	
	
}
