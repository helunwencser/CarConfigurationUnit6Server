package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * create database automobiles
 * */
public class CreateDB {
	
    private static String jdbcDriver = "com.mysql.jdbc.Driver";

    public CreateDB(){
        
    	try {
			Class.forName(jdbcDriver);
			
	    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=" + Config.USER + "&password=" + Config.PASSWORD);
	        
	    	Statement statement = connection.createStatement();
	        
	    	statement.executeUpdate(GetMySQL.getMySQL(SQL.CREATEDB));
	    	
	    	System.out.println("Create database successfully!");
	    	
	    	statement.close();
	    	
	    	connection.close();
	    	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
}
