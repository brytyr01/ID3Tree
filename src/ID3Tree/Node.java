package ID3Tree;



public class Node {

	private String TitleValue;	//Node title - same as data set attribute
	private Node parent;
	private Node RightSide=null;
	private Node LeftSide=null;
	private double threshold; 	//Value used to split the data 
	private double infoGain;	//Information gain calculated
	private boolean leaf=false;	
	
	//Constructor to create full node
		public Node(String TitleValue, Node parent, Node RightSide, Node LeftSide, double threshold, double infoGain){
			this.TitleValue = TitleValue;
			this.parent = parent;
			this.RightSide = RightSide;
			this.LeftSide = LeftSide;
			this.threshold = threshold;
			this.infoGain = infoGain;
		}
		//Constructor to create leaf node
		public Node(String TitleValue,boolean leaf){
			this.TitleValue = TitleValue;
			this.leaf=leaf;
		}
		//Getters and Setters
		
		public String getTitleValue() {
			return TitleValue;
		}

		public void setTitleIndex(String TitleValue) {
			this.TitleValue = TitleValue;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public Node getRightSide() {
			return RightSide;
		}

		public void setRightSide(Node RightSide) {
			this.RightSide = RightSide;
		}

		public Node getLeftSide() {
			return LeftSide;
		}

		public void setLeftSide(Node LeftSide) {
			this.LeftSide = LeftSide;
		}

		public double getThreshold() {
			return threshold;
		}

		public void setThreshold(double threshold) {
			this.threshold = threshold;
		}

		public double getInfoGain() {
			return infoGain;
		}
		public boolean getLeaf() {
			return leaf;
		}
		
		public void setInfoGain(double infoGain) {
			this.infoGain = infoGain;
		}
	
	
	
}
