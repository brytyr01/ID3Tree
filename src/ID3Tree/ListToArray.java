package ID3Tree;

import java.util.ArrayList;

public class ListToArray {
    
	public String[][] DatToArray(ArrayList<ArrayList<String>> arrayList){
		//gets column count
		int Columns = arrayList.size();
		//gets row count
 		int Rows = arrayList.get(0).size();
 		//create array to transfer data to
 		String[][] ArrayMatrix = new String[Rows][Columns];
 		// loop through each column
 		for(int i=0;i<Columns;i++) {
 			//loop through each row
 			for(int j=0;j<Rows;j++) {
 				//transfer the arraylist value to the array matrix
 				ArrayMatrix[j][i] = arrayList.get(i).get(j);
 			}
 		}
 		return ArrayMatrix;
     }

	
}
