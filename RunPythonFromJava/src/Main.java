import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	private JFrame frame;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Run the Welcome page
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

}
