package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DButil {

	public DButil() {}
		// TODO Auto-generated constructor stub
		public static Connection getConnection() {
			Connection con =null;
					try
			{
						Class.forName("com.mysql.cj.jdbc.Driver");  
			   			 con=DriverManager.getConnection(  
			   			"jdbc:mysql://localhost:3306/library","root","12345");
			}
					catch(Exception e)
					{
						System.out.println(e);
					}
					return con;
			// TODO Auto-generated constructor stub
		}


	}
