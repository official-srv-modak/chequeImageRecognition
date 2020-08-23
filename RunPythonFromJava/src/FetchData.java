import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FetchData {

	public JFrame frame;
	private static JTextField to;
	private static JTextField from;
	private static JTextField date;
	private static JTextField amount;
	private static JTextField ddRef;
	public JFrame frame1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { FetchData
		 * window = new FetchData(); window.frame.setVisible(true); } catch (Exception
		 * e) { e.printStackTrace(); } } });
		 */
	}

	/**
	 * Create the application.
	 */
	public FetchData() {
		initialize();
	}
	public static ArrayList<String> manipulateFetchedData(String fetchedData)
	{
		ArrayList <String> result = new ArrayList<String>();
		int index = fetchedData.indexOf("To");
		while(true)
		{
			String catchNew = new String();
			for(int i = index; i<fetchedData.length(); i++)
			{
				if(fetchedData.charAt(i)!='\n' && fetchedData.charAt(i)!=' ' && index!=i)
						catchNew.concat(fetchedData.substring(i, i+1));
			}
			System.out.println(catchNew);
			if(catchNew.length()<3)
				index = index+3+catchNew.length();
			else
				break;
		}
		return result;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private ArrayList<String> initialize() {
		ArrayList <String> fetchedData = GetFinalData.manipulateFetchedData(ExtractFromFile.getString());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 279);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTo.setBounds(53, 44, 46, 14);
		frame.getContentPane().add(lblTo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFrom.setBounds(53, 69, 46, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setBounds(53, 97, 46, 14);
		frame.getContentPane().add(lblDate);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAmount.setBounds(53, 126, 46, 14);
		frame.getContentPane().add(lblAmount);
		
		JLabel lblDdRef = new JLabel("DD Ref");
		lblDdRef.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDdRef.setBounds(53, 151, 46, 14);
		frame.getContentPane().add(lblDdRef);
		
		
		 for(int i = 0; i<fetchedData.size(); i++)
		 System.out.println(fetchedData.get(i)+"-->"+(i+1));
	
		to = new JTextField(fetchedData.get(0));
		to.setBounds(109, 41, 192, 20);
		frame.getContentPane().add(to);
		to.setColumns(10);
		
		from = new JTextField(fetchedData.get(2));
		from.setBounds(109, 66, 192, 20);
		frame.getContentPane().add(from);
		from.setColumns(10);
		
		
		String temp = fetchedData.get(1);
		temp = temp.replace("Date :-", " ");
		temp = temp.trim();
		date = new JTextField(temp);
		date.setBounds(109, 94, 86, 20);
		frame.getContentPane().add(date);
		date.setColumns(10);
		
		temp = fetchedData.get(3);
		temp = temp.replace("S$", " ");
		temp = temp.trim();
		amount = new JTextField(temp);
		amount.setBounds(109, 123, 86, 20);
		frame.getContentPane().add(amount);
		amount.setColumns(10);
		
		temp = fetchedData.get(4);
		temp = temp.replace("Slenature", " ");
		temp = temp.replace(".", "");
		temp = temp.trim();
		ddRef = new JTextField(temp);
		ddRef.setBounds(109, 148, 255, 20);
		frame.getContentPane().add(ddRef);
		ddRef.setColumns(10);
		
		fetchedData = getChangedData();
		
		JButton Submit = new JButton("Submit");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							demandDraft window = new demandDraft();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		Submit.setBounds(177, 213, 89, 23);
		frame.getContentPane().add(Submit);
		return fetchedData;
	}
	
	public static ArrayList <String> getChangedData()
	{
		ArrayList <String> result= new ArrayList <String>();
		result.add(to.getText());
		result.add(from.getText());
		result.add(date.getText());
		result.add(amount.getText());
		result.add(ddRef.getText());
		return result;
	}
}
