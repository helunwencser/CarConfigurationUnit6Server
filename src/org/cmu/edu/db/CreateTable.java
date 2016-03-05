package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public CreateTable(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			this.createTable(GetMySQL.getMySQL(SQL.CREATE_AUTO_TABLE));
			
			this.createTable(GetMySQL.getMySQL(SQL.CREATE_OPTIONSET_TABLE));
			
			this.createTable(GetMySQL.getMySQL(SQL.CREATE_OPTION_TABLE));
			
			this.createTable(GetMySQL.getMySQL(SQL.CREATE_MAPPING_TABLE));
			
			this.closeResources();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * execute sql to create table
	 * @param	sql
	 * 			sql to executed
	 * */
	private void createTable(String sql){
		try {
			Statement statement = conn.createStatement();
			statement.execute(sql);
			System.out.println("Execute: ");
			System.out.println("\t" + sql);
			System.out.println("\tsuccessfully!");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * close resources
	 * */
	private void closeResources(){
		try {
			this.conn.close();
			System.out.println("Resources closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
