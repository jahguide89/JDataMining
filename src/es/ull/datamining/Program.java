package es.ull.datamining;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import es.ull.datamining.classifiers.Classifier;
import es.ull.datamining.classifiers.KnnClassifier;
import es.ull.datamining.classifiers.NaiveBayes;
import es.ull.datamining.core.Dataset;
import es.ull.datamining.experiment.ConfusionMatrix;
import es.ull.datamining.experiment.CrossValidation;
import es.ull.datamining.util.Util;


public class Program {
	
	public static void main(String[] args) {
		run();
	}

	public static void run(){
		int opc = -1;
		Scanner sc;
		Dataset dataset = new Dataset();
		do{
			System.out.println("********");
			System.out.println("* MENU *");
			System.out.println("********");
			System.out.println();
			System.out.println("\t1. Cargar dataset");
			System.out.println("\t2. Ver dataset");
			System.out.println("\t3. Info dataset");
			System.out.println("\t4. Guardar dataset");
			System.out.println("\t5. Aplicar Knn");
			System.out.println("\t6. Aplicar Naive Bayes");
			System.out.println("\t7. Cross Validation");
			System.out.println("\t0. Salir");
			System.out.println();
			System.out.print("Introduzca una opcion: ");
			sc = new Scanner(System.in);
			opc = sc.nextInt();
			
			switch (opc) {
			case 1:
				try {
//					dataset.read(Util.getOpenFileName());
//					dataset.read("resources/myDataset3.csv");
//					dataset.read("resources/hv-01.csv");
					dataset.read("resources/glass.csv");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				dataset.show();
				break;
			case 3:
				dataset.showInfo();
				break;
			case 4:
				dataset.write(Util.getSaveFileName());
				break;
			case 5:
				Classifier classifier = new KnnClassifier.KnnBuilder(dataset).knn(3).build();
				
				
				ConfusionMatrix confusionMatrix = new ConfusionMatrix(dataset.getClassesTable());
				System.out.println();
				for (int i = 0; i < dataset.nInstances(); i++) {
					String predict = dataset.getInstance(i).getInstanceClass();
					String prediction = classifier.runClassifier(dataset.getInstance(i));
					System.out.print(dataset.getInstance(i).toString());
					System.out.println("--->"+prediction);
					confusionMatrix.addPrediction(predict, prediction);
				}
//				int i = 126;
//				System.out.print(dataset.getInstance(i).toString());
//				System.out.println("--->"+classifier.runClassifier(dataset.getInstance(i)));
				System.out.println(confusionMatrix.toString());
				System.out.println("Precision predictiva: "+confusionMatrix.getPositivePredictiveValue());
				
				
				
				break;
			case 6:
//				for (int i = 1; i < dataset.nAttributes(); i++) {
//					dataset.getAttribute(i).setIsClass(false);
//				}
//				dataset.getAttribute(0).setIsClass(false);
				dataset.getAttribute(1).setIsClass(false);
				System.out.println(Arrays.toString(dataset.getClassesTable()));
				NaiveBayes classifier2 = new NaiveBayes(dataset);
				System.out.println();
				ConfusionMatrix confusionMatrix2 = new ConfusionMatrix(dataset.getClassesTable());
				for (int i = 0; i < dataset.nInstances(); i++) {
					String predict = dataset.getInstance(i).getInstanceClass();
					String prediction = classifier2.runClassifier(dataset.getInstance(i));
					System.out.print(dataset.getInstance(i).toString());
					System.out.println("--->"+prediction);
					confusionMatrix2.addPrediction(predict, prediction);
				}
				System.out.println(confusionMatrix2.toString());
				System.out.println("Precision predictiva: "+confusionMatrix2.getPositivePredictiveValue());
				break;
			case 7:
				CrossValidation crossValidation = new CrossValidation(dataset,10);
				crossValidation.init();
				ConfusionMatrix cm = new ConfusionMatrix(dataset.getClassesTable());
				Dataset trainDataset = null;
				Dataset testDataset = null;
				
				for (int n = 0; n < 10; n++) {
				
					try {
						trainDataset = dataset.deepCopy();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i = 0; i < trainDataset.nInstances(); i++) {
						if (trainDataset.getInstance(i).isTrain(n)){
							trainDataset.delInstance(i);
						}
					}
					
					try {
						testDataset = dataset.deepCopy();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i = 0; i < testDataset.nInstances(); i++) {
						if (!testDataset.getInstance(i).isTrain(n)){
							testDataset.delInstance(i);
						}
					}
					
					Classifier c = new KnnClassifier.KnnBuilder(trainDataset).knn(3).build();
					
					for (int i = 0; i < testDataset.nInstances(); i++) {
						String predict = testDataset.getInstance(i).getInstanceClass();
						String prediction = c.runClassifier(testDataset.getInstance(i));
						cm.addPrediction(predict, prediction);
					}
				
				}
				
				System.out.println(cm.toString());
				System.out.println("Precision predictiva: "+cm.getPositivePredictiveValue());
				break;
			default:
				break;
			}
		}while(opc != 0);
		sc.close();
	}

}
