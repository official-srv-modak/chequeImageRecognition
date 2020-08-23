import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public abstract class ExtractFromFile {

	private static String getDataFromFile()
	{
		File file = new File("output.txt");
		if(!file.exists())
		{
			ExtractImageData.run();
			try
			{
				TimeUnit.MILLISECONDS.sleep(1500);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			file = new File("output.txt");
			/*
			 * if(!file.exists()) { System.out.
			 * println("Some error in extracting the data from the image, contact the admin"
			 * ); return null; }
			 */
		}
		try
		{
			FileInputStream fileIn = new FileInputStream(file);
			String textFromImage = new String(Files.readAllBytes(Paths.get("output.txt")));
			//List <String> textFromImage = Files.readAllLines("output.txt");
			fileIn.close();
			return textFromImage;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	public static String getString()
	{
		ExtractImageData.run();
		try
		{
			TimeUnit.MILLISECONDS.sleep(1500);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		String text = getDataFromFile();
		return text;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getDataFromFile());
	}

}
