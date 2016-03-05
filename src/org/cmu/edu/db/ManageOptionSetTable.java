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
	 * @return	if record is contained in option_table, return the ResultSet;
	 * 			otherwise, return null
	 * */
	private ResultSet selectOptionSet(String optionSet){
		String query = GetMySQL.getMySQL(FileName.SELECT_OPTIONSET);
		ResultSet resultSet = null;
		try {
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setString(1, optionSet);
			resultSet = statement.executeQuery(query);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public int addOptionSet(String optionSet){
		int key = -1;
		ResultSet resultSet = this.selectOptionSet(optionSet);
		if(resultSet != null){
			try {
				PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(FileName.INSERT_OPTIONSET));
				statement.setString(1, optionSet);
				statement.execute();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			key = resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
}
