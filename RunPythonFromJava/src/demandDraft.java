import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class demandDraft {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public demandDraft() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void doOfs()
	{
		ArrayList <String> fetchedData = GetFinalData.manipulateFetchedData(ExtractFromFile.getString());
		try {
			//JMSQueue.putMessage("ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ="+fetchedData.get(2));
			//JMSQueue.putMessage("ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ="+fetchedData.get(0));
			JMSQueue.putMessage("ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ=A300131");
			JMSQueue.putMessage("ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ=C144031");

		//	JMSQueue.putMessage("FUNDS.TRANSFER,/I/PROCESS,AUTHOR/123456,,TRANSACTION.TYPE=AC,DEBIT.ACCT.NO=sourav,DEBIT.CURRENCY=USD,DEBIT.AMOUNT=10000000,CREDIT.ACCT.NO=sourav,CREDIT.CURRENCY=USD");
			JMSQueue.putMessage("FUNDS.TRANSFER,/I/PROCESS,AUTHOR/123456,,TRANSACTION.TYPE=AC,DEBIT.ACCT.NO=C144031,DEBIT.CURRENCY=USD,DEBIT.AMOUNT=10000000,CREDIT.ACCT.NO=A300131,CREDIT.CURRENCY=USD");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are You Sure???");
		lblAreYouSure.setBounds(182, 54, 103, 14);
		panel.add(lblAreYouSure);
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList <String> data = FetchData.getChangedData();
				data.set(0, "100100");
				data.set(1, "100101");
				PerformTransaction.runInitialTest();
				//PerformTransaction.createTable();
				boolean flag = PerformTransaction.doTransaction(data.get(0), data.get(1), data.get(3));
				
				if (flag == true)
				{
					JFrame frame1;
					try {
						Message window = new Message("TRANSACTION SUCCESSFULL");
						window.frame.setVisible(true);
						OFS.StoreOFS();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					JFrame frame1;
					try {
						Message window = new Message("TRANSACTION UNSUCCESSFULL");
						window.frame.setVisible(true);
						window.dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						WelcomePage window = new WelcomePage();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				frame.dispose();
				//// Do the ofs here under the function written above
				doOfs();
			}
		});
		btnYes.setBounds(91, 132, 89, 23);
		panel.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				JFrame frame1;
				try {
					FetchData window = new FetchData();
					window.frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNo.setBounds(239, 132, 89, 23);
		panel.add(btnNo);
	}
}
