import java.util.ArrayList;
import java.util.Locale.Category;

public class GetFinalData {

	@SuppressWarnings("unused")
	private static String getString(String fetchedData, String flagString)
	{
		String result = new String();
		int index = fetchedData.indexOf(flagString);
		String catchNew = new String();
		if(flagString.length()<2)
		{
			catchNew = new String();
			for(int i = index+2; i<fetchedData.length(); i++)
			{
				if(fetchedData.charAt(i)!='\n' && fetchedData.charAt(i)!=' ' && index!=i)
						{
							//System.out.println(fetchedData.substring(i));
							catchNew = catchNew.concat(fetchedData.substring(i, i+1));
							//System.out.println(catchNew);
						}
				else
					break;
			}
			return catchNew;
		}
			while(true)
			{
				catchNew = new String();
				for(int i = index+flagString.length()-1; i<fetchedData.length(); i++)
				{
					if(fetchedData.charAt(i)!='\n' && fetchedData.charAt(i)!=' ' && index!=i && i<fetchedData.length())
							{
								//System.out.println(fetchedData.substring(i));
								catchNew = catchNew.concat(fetchedData.substring(i, i+1));
								//System.out.println(catchNew);
							}
					else
						break;
				}
			//	System.out.println(catchNew+"**************");
				if(catchNew.length()<3)
					index = index+3+catchNew.length()-2;
				else
					{
						fetchedData = fetchedData.replaceFirst(flagString, "");
						//System.out.println(fetchedData+"*********"+flagString);
						index = fetchedData.indexOf(flagString);
						if(index == -1)
							break;
					}
			}
		if(!catchNew.isEmpty())
			result = catchNew;
		return result;
	}
	public static ArrayList<String> manipulateFetchedData(String fetchedData)
	{
		ArrayList <String> result = new ArrayList<String>();
		result.add(getString(fetchedData, "To"));
		result.add((getString(fetchedData, "Date :-")));
		result.add(getString(fetchedData, "Amount"));
		result.add(getString(fetchedData, "$"));
		result.add(getString(fetchedData, "Headquarters"));
		for(int i=0; i<result.size(); i++)
			for(int j=0; j<result.get(i).length(); j++)
				if(!Character.isLetter(result.get(i).charAt(j)))
				{
					if(i==0 || i==2)
					{
						StringBuilder str = new StringBuilder(result.get(i));
						str = str.deleteCharAt(j);	
						result.set(i, str.toString());
					}
				}
		for(int i = 0; i<result.size(); i++)
			{
				if(result.get(i)==null)
				{
					System.out.println((i+1)+" is empty");
					result.remove(i);
				}
			}
		
		StringBuilder str = new StringBuilder(result.get(0));
		
		
		 if(str.charAt(0)<'a' || str.charAt(0)>'z') { str = str.deleteCharAt(0); str =
				 str.deleteCharAt(0); result.set(0, str.toString()); } // Noise reduction
		
			
		result.set(0, result.get(0).toLowerCase());
		result.set(2, result.get(2).toLowerCase());
		return result;
	}
	public static void main(String[] args) {
		
		
		ArrayList<String> res = manipulateFetchedData(ExtractFromFile.getString());
		for(int i = 0; i<res.size(); i++)
			System.out.println(res.get(i)+"-->"+(i));
	}

}
