package ID3Tree;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Shuffle {

	private int numOfRows;
	
	//constructor gets number of rows
	public Shuffle(CSVStorage path) {
		numOfRows=path.RowIndex;
	}

	public String[][] mix(String[][] dataMatrix) {
		
		Random rnd = ThreadLocalRandom.current();
	    //cycle down through all row index values until before title index value
		for (int i = numOfRows - 1; i > 1; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // If the index is at 0(title index) run random until acceptable index
	      while (index == 0){
				index = rnd.nextInt(i+1);
			}
	      // Simple swap of data using temp variable
	      String[] a = dataMatrix[index];
	      dataMatrix[index] = dataMatrix[i];
	      dataMatrix[i] = a;
	    }
		return dataMatrix;
	}
	

}
