package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect 
{
	private static String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=root";
	private static Connection conn;
	
	public static Connection getConnection()
	{	
		try 
		{	
			conn = DriverManager.getConnection(jdbcURL);
			return conn;
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
