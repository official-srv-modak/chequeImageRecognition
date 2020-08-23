import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public abstract class PerformTransaction {
	
	public static void runInitialTest()
	{
		if(ConnectDatabase.checkConnection() == false)
		{
			System.out.println("Error in MySql Connection");
		}
		else
		{
			//Message.RunWindow("MySql is running doing transaction");
			try
			{
				TimeUnit.MILLISECONDS.sleep(1000);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	public static boolean createTable()
	{
		try
		{
			String sql="create database bank";
			Statement s=ConnectDatabase.db.createStatement();
			s.executeUpdate(sql);
			
			System.out.println("Database created");
			sql="create table bank.acc"+
					"(uid bigint,"+
					 "uname varchar(20),"+
					 "uphno decimal(10,0),"+
					 "uaccno decimal(10,0),"+
					 "upass varchar(20),"+
					 "balance bigint,"+
					 "primary key (uid));";
			
			s = ConnectDatabase.db.createStatement();
			s.executeUpdate(sql);
			System.out.println("Server Table Created");
			return true;
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());
			return false;
		}
	}
	public static boolean doTransaction(String Account1, String Account2, String amount)
	{
		boolean flag = false;
		try
		{
		String sql = "update bank.acc set balance = balance + "+amount+" where uaccno = "+ Account2;
		Statement s= ConnectDatabase.db.createStatement();
		s.executeUpdate(sql);
		sql = "update bank.acc set balance = balance - "+amount+" where uaccno = "+Account1;
		s= ConnectDatabase.db.createStatement();
		s.executeUpdate(sql);
		flag = true;
		return flag;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	}
}
