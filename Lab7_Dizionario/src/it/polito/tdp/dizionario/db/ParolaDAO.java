package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ParolaDAO
{
	private List<String> result= new ArrayList<String>();
	
	public List<String> getParole(int lunghezza)
	{
		result.clear();
		Connection conn = DBConnect.getConnection();
		String sql = "select nome from parola HAVING LENGTH(nome)=?";
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, lunghezza);
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
				String s = res.getString("nome");
				result.add(s);
			}
			res.close();
			conn.close();
			return result;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Errore Query");
		}
	}
}
