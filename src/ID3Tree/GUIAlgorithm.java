package ID3Tree;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUIAlgorithm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private boolean Default_runs = false;
	private boolean Default_Address = false;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAlgorithm frame = new GUIAlgorithm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIAlgorithm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(264, 108, 37, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(264, 151, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90"}));
		comboBox.setBounds(252, 66, 37, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90"}));
		comboBox_1.setBounds(302, 66, 37, 20);
		contentPane.add(comboBox_1);
		
		
		
		JButton btnGo = new JButton("GO!");
		btnGo.setBounds(264, 203, 89, 23);
		
		btnGo.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	 String address = textField_1.getText();
	        	 String numOfRuns = textField.getText();
	        	 int runs;
	        	 double testing = Double.parseDouble(comboBox.getSelectedItem().toString());
	        	 double training = Double.parseDouble(comboBox_1.getSelectedItem().toString());
	        	
	        	 if(testing+training!=100) {
	        		training=30;
	        		 testing=70;
	        	 }
	        	 if(numOfRuns.isEmpty()) {
	        		Default_runs = true;
	        		runs = 10;
	        		textField.setText("10");
	        	}else {
	        		runs = Integer.parseInt(numOfRuns);
	        	}
	        	if(address.isEmpty()) {
	        		Default_Address = true;
	        		textField_1.setText("C:\\Users\\Brian Tyrrell\\Documents\\machineLearningAssignment3\\owls.csv");
	        	}
	        	
	        	//create a Algorithm driver object and and pass in the desired runs
	        	DecissionTreeDriver driver = new DecissionTreeDriver();
	        	// set default runs
	        	if(Default_Address&&Default_runs) {
	        		driver.SetUpAlgorithm("C:\\Users\\Brian Tyrrell\\Documents\\machineLearningAssignment3\\owls.csv",training,testing,runs);
	        	}
	        	//set defaults runs and get address
	        	else if(Default_Address) {
	        		driver.SetUpAlgorithm(textField_1.getText(),70,30,runs);
	        	}
	        	//set default address and use runs
	        	else if(Default_runs){
	        		driver.SetUpAlgorithm("C:\\Users\\Brian Tyrrell\\Documents\\machineLearningAssignment3\\owls.csv",training,testing,runs);
	        	}else {
	        		// runs algorithm and passes in text
	        		driver.SetUpAlgorithm(textField_1.getText(),training,testing,runs);
	        	}
	        	 
	        	 
	          }
	       });
		contentPane.add(btnGo);
		
		JLabel lblNewLabel = new JLabel("Number of runs:");
		lblNewLabel.setBounds(117, 108, 86, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("File Address:");
		lblNewLabel_1.setBounds(117, 151, 86, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCDecisionTree = new JLabel("ID3 Decision Tree");
		lblCDecisionTree.setBounds(134, 37, 146, 33);
		contentPane.add(lblCDecisionTree);
		
		JLabel lblTrainingtestingSplit = new JLabel("Training/Testing Split:");
		lblTrainingtestingSplit.setBounds(117, 66, 117, 20);
		contentPane.add(lblTrainingtestingSplit);
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90"}));
//		comboBox.setBounds(252, 66, 37, 20);
//		contentPane.add(comboBox);
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"10", "20", "30", "40", "50", "60", "70", "80", "90"}));
//		comboBox_1.setBounds(302, 66, 37, 20);
//		contentPane.add(comboBox_1);
		
		JLabel lblTesting = new JLabel("Testing:");
		lblTesting.setBounds(246, 46, 46, 14);
		contentPane.add(lblTesting);
		
		JLabel lblTraining = new JLabel("Training:");
		lblTraining.setBounds(304, 46, 46, 14);
		contentPane.add(lblTraining);

	}
}
