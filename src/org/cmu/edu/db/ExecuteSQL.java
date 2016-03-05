package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteSQL {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public ExecuteSQL(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * execute sql to create database
	 * @param	sql
	 * 			sql to executed
	 * */
	public void createDataBase(String sql){
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
	
	public void insertAuto(String sql, String name, String make, int baseprice){
		try {
			PreparedStatement statement = this.conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, make);
			statement.setInt(3, baseprice);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * close resources
	 * */
	public void closeResources(){
		try {
			this.conn.close();
			System.out.println("Resources closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new CreateDB();
		ExecuteSQL executor = new ExecuteSQL();
		executor.createDataBase(GetMySQL.getMySQL(FileName.CREATE_AUTO_TABLE));
	
		executor.insertAuto(GetMySQL.getMySQL(FileName.INSERT_INTO_AUTO_TABLE), "name", "make", 90);
		
		executor.closeResources();
	}
}
