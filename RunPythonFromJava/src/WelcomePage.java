import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage {

	public JFrame frame, frame1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage();
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
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.RED);
		lblWelcome.setBounds(149, 11, 128, 33);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnScanDd = new JButton("SCAN DD");
		btnScanDd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FetchData window = new FetchData();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnScanDd.setBounds(172, 139, 89, 23);
		frame.getContentPane().add(btnScanDd);
		
		JLabel author = new JLabel("Authors:- Sourav Modak, Sivasangari, Thanmayi, Roopa, Vikas");
		author.setHorizontalAlignment(SwingConstants.CENTER);
		author.setBounds(10, 51, 414, 14);
		frame.getContentPane().add(author);
		
		JButton Reset = new JButton("RESET ACCOUNT");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase.reset();
			}
		});
		Reset.setBounds(149, 227, 138, 23);
		frame.getContentPane().add(Reset);
	}
}
