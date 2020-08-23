import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Message {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	
	public static void dispose()
	{
		frame.dispose();
	}
	public static void RunWindow(String Msg)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message window = new Message(Msg);
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
	public Message(String Msg) {
		initialize(Msg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String Msg) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea(Msg);
		textArea.setBounds(111, 35, 210, 121);
		frame.getContentPane().add(textArea);
		
		JButton Ok = new JButton("Ok");
		Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		Ok.setBounds(174, 195, 89, 23);
		frame.getContentPane().add(Ok);
	}
}
