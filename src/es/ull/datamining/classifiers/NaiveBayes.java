package es.ull.datamining.classifiers;

import es.ull.datamining.core.Dataset;
import es.ull.datamining.core.Instance;
import es.ull.datamining.util.Util;

public class NaiveBayes implements Classifier {

	// data
	private final Dataset dataset;

	// model
	private NaiveBayesModel model;

	public NaiveBayes(Dataset dataset) {
		this.dataset = dataset;
		model = new NaiveBayesModel(dataset);
	}

	public String runClassifier(Instance instance) {
		double[] probs = new double[model.nClasses()];
		for (int indexClass = 0; indexClass < probs.length; indexClass++) {
			probs[indexClass] = model.getClassProb(indexClass);
			for (int indexAttribute = 0; indexAttribute < dataset.nAttributes(); indexAttribute++) {
				if (!dataset.getAttribute(indexAttribute).isClass()) {
					double probPost = model.probPost(indexClass,
							indexAttribute, instance.getValue(indexAttribute));
					if (Double.isNaN(probPost))
						probs[indexClass] *= 1;
					else {
						probs[indexClass] *= probPost;
					}
				}
			}
		}

		// normalize probs
		Util.normalizeVector(probs);

		// get max prob index
		return model.getClassName(Util.getIndexMax(probs));
	}

}
