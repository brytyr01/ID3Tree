package ID3Tree;

import java.util.ArrayList;

public class Prediction {

	private double CorrectCount;
	private double numElements;
	private double cummilatedAccuracy,SummedAccuracy;
	private int count=0;
	
	//this method calculates the accuracy of the algorithm
	public double calculateAccuracy(ArrayList<TestResult> tr) {
		//Total number of test cases
    	numElements = tr.size();
    	//counts correct number of predictions
    	double CorrectCount = 0;
    	double accuracy;//returns accuracy of tree
		// cycle through all the results
    	for(int j = 0;j<numElements;j++) {
    		//get predicted and actual value of the labels
    		String predicted = tr.get(j).getPredicted();
    		String result = tr.get(j).getresult();

    		//If predicted equals result increment count
    		if(predicted.equals(result)) {
    			CorrectCount++;
    		}
    	}
    	this.CorrectCount=CorrectCount;
    	accuracy = (CorrectCount/numElements) * 100;
    	cummilatdAccuracy(accuracy);
    return accuracy = (CorrectCount/numElements) * 100;
	}
	
	//This method returns the percentage of misclassified rows
	public double CalculatePercentageError() {
		return (numElements-CorrectCount)*100;
	}
	
	//This method returns the number of misclassified rows
	public double getMisclassificationAmount() {
		return numElements-CorrectCount;
	}

	private double cummilatdAccuracy(double accuracy) {
		count++;
		SummedAccuracy=SummedAccuracy+accuracy;
		cummilatedAccuracy=(SummedAccuracy)/count;
		return (accuracy);
	}

	//returns summed accuracy divided by num. of tests
	public double getcummilatdAccuracy() {
		return cummilatedAccuracy;
	}

}
