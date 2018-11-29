package ID3Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class InfoGain {

	private String[][] data;
	private int columnCount;
	private int rowCount;
	private ArrayList<String> labels = new ArrayList<String>();
	ArrayList<Double> thresholdArr = new ArrayList<Double>();
	private double totalEntropy;
	
	//Constructor
	public InfoGain(int NumCol, int NumRow, String[][] dataSet){
		this.data = dataSet;		
		this.columnCount = NumCol;
		this.rowCount = NumRow;
	}
	

	public Node getThreshold(int column){
		
		//puts each unique labels into an arraylist
		SetUniqueLabels();
		// Puts all the columns values into a separate arraylist to use as thres values
		SetThresholdList(column);
		//gets entropy value of column
		totalEntropy = getTotalEntropy();
		
		double TempGain;
		ArrayList<Double> ThresholdStorage = new ArrayList<>();
		ArrayList<Double> GainStorage = new ArrayList<>();
		
		for(int i = 0;i< thresholdArr.size(); i++ ){						//Loop over each possible threshold value
			TempGain = getGain(thresholdArr.get(i), column);				//Get the information gain value for passed threshold
			ThresholdStorage.add(thresholdArr.get(i));
			GainStorage.add(TempGain);
		}
		
		double maxGain = -10;
		double bestThresh = -10;
		double thresh;
		double gain;
		//loop through the thresholds to find the best gain for the column and set it
		for (int j=0;j<ThresholdStorage.size();j++) {
		    thresh = ThresholdStorage.get(j);
		    gain = GainStorage.get(j);
		    if(maxGain<gain){								
		    	maxGain = gain;
		    	bestThresh = thresh;
		    }
		}
		
		return new Node(data[0][column],null, null, null, bestThresh, maxGain);																		
	}


	private void SetUniqueLabels() {

		for(int i=1;i< rowCount;i++){
			//gets each unique label and places it in a label arraylist
			String label = data[i][columnCount-1];
			if (!labels.contains(label)){
				labels.add(label);
			}
		}
	}

	private void SetThresholdList(int column) {
		//cycles through the column and adds the values for an array
		for(int i=1;i< rowCount;i++){
			double value = Double.parseDouble(data[i][column]);	//Record all possible threshold values		
			thresholdArr.add(value);
		}
		
	}
	
	
	// gets entropy value for the whole column
	private double getTotalEntropy() {
		// stores a count for each occurrence of each label outcome
		int[] LabelCount = new int[labels.size()];
		// gets the size/number of rows of the column
		double dataSize = (data.length-1);
		//initialize entropy as 0
		double entropy = 0;
		
		//Initializes each count value as 0
		for(int i=0;i<LabelCount.length;i++){
			LabelCount[i] = 0;
		}
		//loop through each label
		for(int j=0;j < labels.size(); j++){
			//loop through the column data for label matches
			for(int i=1 ; i<data.length; i++){
				//if label matches column label +1
				if (labels.get(j).equals(data[i][columnCount-1])){
						LabelCount[j]=LabelCount[j]+1;
				}
			}
			double matches = LabelCount[j];
			double result = matches/dataSize;
			entropy = entropy - result* this.logCalculation(result, 2);
		}
		
		return entropy;
	}

	//helper method to log calculations
	private double logCalculation(double count, double base){
		return (Math.log(count)/Math.log(base));
	}

	//calculates gain for the given threshold
	public double getGain(Double threshold, int column){
		double entropyLower = 0;											
		double entropyHigher = 0;
		double GreaterCount = 0;
		double lessCount = 0;
		ArrayList<String> lower = new ArrayList<String>();
		ArrayList<String> higher = new ArrayList<String>();	

		// sorts the data into 2 arraylists based on if the value is greater or less then the threshold
		for(int i=1 ; i<data.length ; i++){
			
			if (threshold>=Double.parseDouble(data[i][column])){
				lower.add(data[i][columnCount-1]);
			}
			else{
				higher.add(data[i][columnCount-1]);
			}
		}
		
		//run through each unique label
		for(int i=0 ; i<labels.size() ; i++ ){
			String current = labels.get(i);
			int count = 0;
			// run through the lower arraylist and count matches to the label 
			for(int j=0 ; j<lower.size(); j++){
				if (lower.get(j).equals(current)){
					count++;
				}
			}
			// calculate entropy for lower then threshold arraylist
			double fraction;
			lessCount = lower.size();
			//calculate entropy if count greater then 0
			if (count > 0){
				fraction = count/lessCount;
				entropyLower =entropyLower - (fraction)* logCalculation(fraction, 2);
			}			
		}
		

		//run through each unique label
		for(int i=0 ; i<labels.size() ; i++ ){
			String current = labels.get(i);
			int count = 0;
			// run through the lower arraylist and count matches to the label 
			for(int j=0 ; j<higher.size(); j++){
				if (higher.get(j).equals(current)){
					count++;
				}
			}
			double fraction;
			GreaterCount = higher.size();
			//calculate entropy if count greater then 0
			if(count > 0){
				fraction = count/GreaterCount;
				entropyHigher =entropyHigher - (fraction)* logCalculation(fraction, 2);
			}
			 
		}
		//calculate gain
		int numSamples = (data.length - 1);
		double gain = totalEntropy - ((entropyLower*lessCount)/numSamples) - ((entropyHigher*GreaterCount)/numSamples);
		return gain;
	
	}
}
