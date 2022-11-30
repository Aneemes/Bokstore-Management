package cw2;
																					

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException																				;
import java.sql.Statement																					;

public class ConnectionC 																					{
	
	public Statement connection()																			{
		Connection con;
		Statement stmt=null																					;
		try 																								{
			con = DriverManager.getConnection( "jdbc:mysql://localhost:3307/java_2","root","")			;
			stmt = con.createStatement()																	;
																											} 
		catch (SQLException e1) 																			{
				
			e1.printStackTrace()																			;
																											}
		return stmt																							;
		
																											}

																											}
