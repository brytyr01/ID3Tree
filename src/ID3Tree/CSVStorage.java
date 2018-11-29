package ID3Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVStorage {

	public int ColumnCount = 0;
	public int RowIndex = 0;
	public ArrayList<ArrayList<String>> InputtedData = new ArrayList<ArrayList<String>>();
	private String[] RowTemp;								 								
    private int NumOfColumns;
	private File DataFile;
    
	//Constructor
	public CSVStorage(String filePath){
			//create file object
			CreateFileObject(filePath);	
	}
	
		// creates a file object called from constructor
        private void CreateFileObject(String filePath) {
        	DataFile = new File(filePath);
        }
        
        // reads in the data from csv file
        public ArrayList<ArrayList<String>> ReadInData() {
            try {
            		// create buffered reader object
                	BufferedReader buffRead = new BufferedReader(new FileReader(DataFile));
                	
                	//while the csv file has another line to read in
                	while (buffRead.ready()) {		
                		//read in the line and split it on a comma or next line
                		String Temp = buffRead.readLine();
                		RowTemp = Temp.split(",|\\s|;");
                    
                		// Run once to set up columns of arraylists
                		if (ColumnCount == 0){	
	                    	//Gets the number of columns for this dataset
	                    	NumOfColumns = RowTemp.length;
	                    	for(int i=0;i<NumOfColumns;i++){
	                    		InputtedData.add(new ArrayList<String>());
	                    	}
                		}
                		
                	// cycle through each column and add the data	
                    for(int i=0;i<InputtedData.size();i++) {
                    	//OneColumnData.add(RowTemp);
                    	InputtedData.get(i).add(RowTemp[RowIndex]);
                    	RowIndex++;                  
                    }
                    //reset row index for next pass
                    RowIndex = 0;
                    // column count + 1
                    ColumnCount++;
                }
                buffRead.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //sets the final row index size
            RowIndex=InputtedData.get(0).size();
            return InputtedData;
        }
    
}