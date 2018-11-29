package ID3Tree;

import java.util.ArrayList;


public class DecissionTreeDriver {

	
	public void SetUpAlgorithm(String FilePath,double training , double testing,int RunAmount) {
	
			//Creates a CSVStorage object and passes path into the constructor
			CSVStorage path = new CSVStorage(FilePath);
			// calls method to read in file data into an ArrayList of ArrayList
			ArrayList<ArrayList<String>> csvFile = path.ReadInData();
			//This method returns a Array as the data had to be 
			//read in using ArrayList
			ListToArray convert = new ListToArray();
			//converts the arraylist of arraylists to a 2D string matrix
			String[][] dataMatrix = convert.DatToArray(csvFile);
			// Test object to retrive the test data
			TestData td = new TestData();
			//prediction object to call calculation methods on test results
			Prediction prediction = new Prediction();
			double sum=0;
			for(int i=0;i<=RunAmount;i++) {
							//Create a instance of a SuffleSort object
							Shuffle suffle = new Shuffle(path);
							
							// Passes in the dataMatrix and randomly shuffles it
							dataMatrix=suffle.mix(dataMatrix);
					
							
							// split data to training and testing 
							SplitData dataSplit = new SplitData(training, testing);
							dataSplit.SplitdataTrainingTesting(dataMatrix);
							
							//Gets training and test data after its been split
							String[][] TrainingDataMatrix = dataSplit.getTrainingData();
							String[][] TestDataMatrix = dataSplit.getTestData();
					
							// train the tree!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
							// Pass in the test data and a top of the trained tree will be returned

							Node node = TrainDecisionTree(TrainingDataMatrix);
							System.out.println("great!!!");
							
							// Create a test object and pass the tree node and test data into it
							ArrayList<TestResult> result = td.TestTree(node, TestDataMatrix);
						
							//Finally pass the ArrayList of test results into the prediction class
							double percentage = prediction.calculateAccuracy(result);
							System.out.println("Test run: "+i+" accuracy: "+percentage);
							sum=sum+percentage;
							System.out.println("Overall Percentage: "+" accuracy: "+prediction.getcummilatdAccuracy());
			}				
		}

	private Node TrainDecisionTree(String[][] trainingDataMatrix) {
		
		//Total number of columns
		int numColumns = trainingDataMatrix[0].length;
		//total number of attributes (row count)-1=label
		int numAttributes = numColumns - 1;
		// object used for array sorting during tree creation
		ArraySorter sort = new ArraySorter(numColumns, trainingDataMatrix.length, trainingDataMatrix);
		//used for all info gain and entropy calculations
		//will create multiple instances during tree building
		InfoGain gain;
		// node object	
		Node node = null;
		//Will be the tip of tree passed back upon tree construction
		Node Parent = new Node("Base",null, null, null, -1000, -1000);
		// An array to store the Column titles
		String title[] = new String[numColumns];
		Node LeftSide;
		Node RightSide;
		String[][] threshData;
		
		//create a node leaf with type of label if only 2 data entries present
		if(trainingDataMatrix.length == 2){
			////return new leaf node
			return new Node(trainingDataMatrix[1][numAttributes],true);
		}	
		
		
		//if only 1 type of label return leaf
		int DifferentLabels = 0;
		//loop through every label
		for(int i=1;i<(trainingDataMatrix.length-1);i++){
			//if the current index label is not equal to the next label index enter loop
			if(   !(trainingDataMatrix[i][numAttributes].equals(trainingDataMatrix[i+1][numAttributes]))){
						// enter if uniqueLabel has been incremented 3 times
						if (DifferentLabels > 2){
								i=trainingDataMatrix.length;
						}
						else{
							DifferentLabels++;
						}
			}
			
			// else if looped through all values and unique elements are 0(meaning the are all one type of label)=leaf
			else if(i== (trainingDataMatrix.length-2) && DifferentLabels == 0){
				//return new leaf node
				return new Node(trainingDataMatrix[1][numAttributes],true);
			}
		}
		
		//cycles through each column title and adds it to a array
		for(int a=0; a<numAttributes; a++){	
			title[a] = trainingDataMatrix[0][a];
		}
		
		//loop through each attribute/column calculate max threshold
		for(int j=0; j<numAttributes;j++){
			// gets a single sorted column on index j
			trainingDataMatrix = sort.sortColumn(j);
			
			//gets new threshold and gain value
			gain = new InfoGain(numColumns, trainingDataMatrix.length, trainingDataMatrix);
			node = gain.getThreshold(j);
			
			//if the new node has a higher infoGain then replace best node
			if(node.getInfoGain() >= Parent.getInfoGain()){
				Parent = node;
			}
		}

		//cycles through each column to find best info gain column
		int columnNumber=-1;
		for(int i=0;i<numAttributes;i++){
			if (title[i] == Parent.getTitleValue()){
				columnNumber = i;
			}
		}
				
		//recursive element
		for(int j=0; j<2; j++){
			//Info gain has been calculated and sortColumn method returns the sorted column by itself		
			trainingDataMatrix = sort.sortColumn(columnNumber);
			// Thresh data is a 2d string array [][]	which is either greater or less then the threshold depending on the value j	
			threshData = sort.sortByThreshold(trainingDataMatrix, Parent.getThreshold(), columnNumber, j);
			//go down left side of tree first
			LeftSide = TrainDecisionTree(threshData);
			Parent.setLeftSide(LeftSide);
			//then go down right side of tree
			if(j==1) {
				RightSide = TrainDecisionTree(threshData);
				Parent.setRightSide(RightSide);
			}	
		}
				
	return Parent;
	}

}
