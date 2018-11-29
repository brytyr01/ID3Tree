package ID3Tree;

public class ArraySorter {
	
	private String[][] data;
	private int ArrayLength;
	
	//constructor
	public ArraySorter(int numColumns, int length, String[][] trainingDataMatrix) {
		data=trainingDataMatrix;
		this.ArrayLength=length;
	}
	
	//Sort the entire data set based on one column
		public String[][] sortColumn(int columnNumber){
			String[] SingleColumn = new String[ArrayLength];
			
			for(int k=0; k<ArrayLength ; k++){
				SingleColumn[k] = data[k][columnNumber];			//Stores the column that is to be sorted
			}
			OrderColumn(SingleColumn,columnNumber);

			return data;
		}
		
		// order column sort lowest to highest
		private void OrderColumn(String[] UnsortedArray, int colNum) {
		    String[] row1;
		    String row2; 
		    for(int i=1; i<UnsortedArray.length; i++) {
		    	for(int j=1; j<UnsortedArray.length-i; j++) {
		    		//works the value up through the array
			        if(Double.parseDouble(UnsortedArray[j]) > Double.parseDouble((UnsortedArray[j+1]))) {
			        	row1= data[j];
			        	row2 = UnsortedArray[j];
			            data[j] = data[j+1];
			            UnsortedArray[j] = UnsortedArray[j+1];
			            data[j+1] = row1;
			            UnsortedArray[j+1] = row2;
			        }
		    	}
		    }
		}	
		

		// gives back the less then or greater then threshold array
		public String[][] sortByThreshold(String[][] data, double threshold, int colNum, int val){
			
			double currIndex;
			
			//less then threshold wanted
			if(val == 0){
				boolean split = false;
				int count = 1;
				while(split==false){
					currIndex = Double.parseDouble(data[count][colNum]);
					if(currIndex > threshold){
						split = true;
					}
					count++;
				}
				//create an 2d array to hold the greater then threshold data
				String[][] UnderThreshold = new String[count-1][data[0].length];
				//copies title row into the array
				System.arraycopy(data, 0, UnderThreshold, 0, UnderThreshold.length);
				return UnderThreshold;
			}
			//To get the dataset when evaluating the greater than threshold part
			else{
				boolean split = false;
				int count = 1;
				while(split==false){
					
					currIndex = Double.parseDouble(data[count][colNum]);

					if(currIndex > threshold){
						split = true;
					}
					count++;
				}
				//create an 2d array to hold the greater then threshold data
				String[][] OverThreshold = new String[(data.length-count+2)][data[0].length];
				//copies title row into the array
				System.arraycopy(data, 0, OverThreshold, 0, OverThreshold.length);
				//copy the data into the array
				System.arraycopy(data, (count-1), OverThreshold, 1, OverThreshold.length-1);
				return OverThreshold;
				
			}
			
			
		}
	
}
