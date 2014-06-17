import java.util.Enumeration;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

public class Main {

	public static void main(String[] args) throws Exception {
		String root = "/home/loai/weka-3-6-11/data/";
		String[] files = new String[] { "contact-lenses.arff", "credit-g.arff",
				"diabetes.arff" };

		for (String file : files) {
			System.out.println(file);
			DataSource source = new DataSource(root + file);
			Instances data = source.getDataSet();
			// last attribute is the class
			if (data.classIndex() == -1)
				data.setClassIndex(data.numAttributes() - 1);
			// naive bayes
			NaiveBayes naiveBayes = new NaiveBayes();
			Evaluation evaluation = new Evaluation(data);
			evaluation.crossValidateModel(naiveBayes, data, 10, new Random(1));
			System.out
					.println("Cross validation: Naive bayes classifier correctly classified: "
							+ String.format("%.2f", evaluation.pctCorrect())
							+ "%");
			int numTest = data.numInstances() - data.numInstances() / 2;
			Instances train = new Instances(data, 0, data.numInstances() / 2);
			Instances test = new Instances(data, data.numInstances() / 2,
					numTest);
			naiveBayes.buildClassifier(train);
			int correct = 0;
			for (Enumeration<Instance> e = test.enumerateInstances(); e
					.hasMoreElements();) {
				Instance instance = e.nextElement();
				double prediction = naiveBayes.classifyInstance(instance);
				if (prediction == instance.classValue())
					correct++;
			}
			System.out
					.println("Splitting train and test: Naive bayes classifier correctly classified:"
							+ String.format("%.2f", 100.0 * correct / numTest)
							+ "%");
			// knn
			IBk knn = new IBk();
			String[] options = Utils.splitOptions("-K 3");
			knn.setOptions(options);
			// If I don't do this, the KNN classifier gives double the
			// amount of instances !!
			evaluation = new Evaluation(data);
			evaluation.crossValidateModel(knn, data, 10, new Random(0));
			System.out
					.println("Cross validation: KNN classifier correctly classified: "
							+ String.format("%.2f", evaluation.pctCorrect())
							+ "%");
			knn.buildClassifier(train);
			correct = 0;
			for (Enumeration<Instance> e = test.enumerateInstances(); e
					.hasMoreElements();) {
				Instance instance = e.nextElement();
				double prediction = knn.classifyInstance(instance);
				if (prediction == instance.classValue())
					correct++;
			}
			System.out
					.println("Splitting train and test: KNN classifier correctly classified:"
							+ String.format("%.2f", 100.0 * correct / numTest)
							+ "%");
			System.out.println("");

		}

	}
}
