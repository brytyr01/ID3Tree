package ID3Tree;

public class SplitData {

	
	private String[][] TrainingData;
	private String[][] TestData;
	private double TrainingSize;
	
	
	public SplitData(double TrainingSize,double TestSize) {
		this.TrainingSize=TrainingSize;
	}

	

	public void SplitdataTrainingTesting(String[][] dataMatrix) {
		
		//gets data set length
		double length = dataMatrix.length;
		
		// rounds up the training size length
		// by casting to int it removes the decimal point rounds down
		int trainingSizeCount =  (int)(length*((double)TrainingSize/100.0));
		int testSizeCount = (int)length - trainingSizeCount;
		
		
		//set the size for both the arrays
		TrainingData= new String[trainingSizeCount][];
		TestData= new String[testSizeCount][];
		
		
		// copies the shuffled data to the training array and test array
		
			System.arraycopy(dataMatrix, 0, TrainingData, 0, trainingSizeCount);
			//Copy title row
			TestData[0] = dataMatrix[0];
			System.arraycopy(dataMatrix, trainingSizeCount, TestData, 1, testSizeCount-1);
		
		
		
	}
	
	public String[][] getTestData() {
		return TestData;
	}

	public String[][] getTrainingData() {
		return TrainingData;
	}

	

	
	
	
}
