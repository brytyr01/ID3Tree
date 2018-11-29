package ID3Tree;

import java.util.ArrayList;

public class TestData {


	public ArrayList<TestResult> TestTree(Node tree, String[][] testData){
		//number of attributes
		int Attributes = testData[0].length -1;
		//The arraylist of results
		ArrayList<TestResult> allResults = new ArrayList<TestResult>();
		//attribute title list
		ArrayList<String> AttributeTitles = new ArrayList<String>();
		
		//Store index location of each column title
		for(int a=0; a<Attributes; a++){
			AttributeTitles.add(testData[0][a]);
		}
		
		//Loop over entire test data set
		for(int i=1 ; i<testData.length ; i++){
			
			boolean leaf = false;
			Node currentNode = tree;
			String title;					
			double threshold;
			int colNum;
			String prediction=null;
			
			
			//Repeat while no class has been assigned to the test case
			while(leaf==false){
				//get title value
				title = currentNode.getTitleValue();
				//get threshold value
				threshold = currentNode.getThreshold();
				//get column index
				colNum = AttributeTitles.indexOf(title);

				//Check to see if node is a leaf
				if(currentNode.getLeaf() == true){	
					prediction = title;
					leaf = true;
				}

				else{
					
					//gown down the left side of the tree
					if(Double.parseDouble(testData[i][colNum]) <= threshold){			
							
							if(currentNode.getLeftSide().equals(null)){
									prediction = currentNode.getTitleValue();
									leaf = true;
							}							
							
							else{
								currentNode = currentNode.getLeftSide();
							}
						}
					
					//go down right side of the tree
					else{
							//check for leaf node/no child node
							if(currentNode.getRightSide().equals(null)){
									prediction = currentNode.getTitleValue();
									leaf = true;
							}
							
							else{
								currentNode = currentNode.getRightSide();
							}
					
					}
				}
			
			}
			
			
			String actual = testData[i][Attributes];
			
			if(!(prediction==null)) {
				TestResult result = new TestResult(prediction, actual);	
				allResults.add(result);
			}										
		}
	
		return allResults;
	}
	
}
