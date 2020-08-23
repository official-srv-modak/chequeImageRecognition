import java.io.IOException;
import java.util.Scanner;

public abstract class ExtractImageData {
	
	static Scanner scan = new Scanner(System.in);
	public static Boolean runCommand(String commands[]) {
		Runtime rt = Runtime.getRuntime();
		Process proc;
		try {
			proc = rt.exec(commands);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	public static void run()
	{
		String [] cmd = {"runExtract.exe"};
		System.out.println(runCommand(cmd));
		System.out.println("exit");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		run();
	}

}
