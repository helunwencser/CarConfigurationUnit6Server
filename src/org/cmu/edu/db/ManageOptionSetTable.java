package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageOptionSetTable {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public ManageOptionSetTable(){
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
	 * if <optionSet> is contained in optionSet_table
	 * @param	optionSet
	 * 			the name of optionSet
	 *
	 * @return	if record is contained in option_table, return the primary key;
	 * 			otherwise, return -1
	 * */
	public int selectOptionSet(String optionSet){
		String query = GetMySQL.getMySQL(SQL.SELECT_OPTIONSET);
		int key = -1;
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setString(1, optionSet);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				key = resultSet.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	
	/*
	 * add optionSet into optionSet_table
	 * @param	optionSet
	 * 			option set
	 * 
	 * @return	the primary key of the record
	 * */
	public int addOptionSet(String optionSet){
		int key = this.selectOptionSet(optionSet);
		if(key == -1){
			try {
				PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.INSERT_OPTIONSET));
				statement.setString(1, optionSet);
				statement.execute();
				statement.close();
				key = this.selectOptionSet(optionSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return key;
	}
	
	/*
	 * delete optionSet from optionSet_table
	 * @param	optionSet
	 * 			optionSet to be deleted
	 * */
	public void deleteOptionSet(String optionSet){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.DELETE_OPTIONSET));
			statement.setString(1, optionSet);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * update optionSet
	 * @param	oldOptionSet
	 * 			the origional option set
	 * 
	 * @param	newOptionSet
	 * 			the new optionSet
	 * */
	public void updateOptionSet(String oldOptionSet, String newOptionSet){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.UPDATE_OPTIONSET));
			statement.setString(1, newOptionSet);
			statement.setString(2, newOptionSet);
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
}
