import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class ConnectDatabase {
	
	public static String userdb="root";
	public static String passdb="password";
	private static Connection connection()
	{
		try
		{
			Connection db1= DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false&allowPublicKeyRetrieval=true", userdb, passdb);
			return db1;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Error in establishing connection, check if mysql server is running or not.\n\nTerminated.");
			System.exit(0);
		}
		return null;
	}
	public static Connection db=connection();
;
	public static boolean checkConnection()
	{
		try
		{
			Statement s=db.createStatement();
			String query;
			int count=0;
			query="SHOW DATABASES";
			ResultSet r=s.executeQuery(query);
			String database;
			while(r.next())
			{
				count++;
			}
			if (count!=0)
			{
				System.out.println("Connection Established");
				return true;
			}
			else
				return false;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public static void reset()
	{
		try
		{
			String sql="drop table bank.acc;"; 
			Statement s=db.createStatement();
			s.executeUpdate(sql);
			sql = "create table bank.acc\r\n" + 
					"(\r\n" + 
					"uid bigint,\r\n" + 
					"uname varchar(20),\r\n" + 
					"uphno decimal(10,0),\r\n" + 
					"uaccno decimal(10,0),\r\n" + 
					"upass varchar(20),\r\n" + 
					"balance bigint,\r\n" + 
					"primary key (uid));";
			s=db.createStatement();
			s.executeUpdate(sql);
			
			sql = "insert into bank.acc values(100100, 'sourav', '1234567890', '100100', '1234', 999999999);"; 
			s=db.createStatement();
			s.executeUpdate(sql);
			
			sql = "insert into bank.acc values(100101, 'thanmayi', '1234567890', '100101', '1233', 999999999); ";
			s=db.createStatement();
			s.executeUpdate(sql);
			
			System.out.println("Database created");
		}
		catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
}
