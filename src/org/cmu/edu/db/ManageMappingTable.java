package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageMappingTable {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public ManageMappingTable(){
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
	 * if record <auto_id, optionSet_id, option_id> is contained in mapping_table
	 * @param	auto_id
	 * 			the primary key of auto
	 * 
	 * @param	optionSet_id
	 * 			the primary key of optionSet
	 * 
	 * @param	option_id
	 * 			the primary key of option
	 * 
	 * @return	if record is contained in mapping_table, return true;
	 * 			otherwise, return false
	 * */
	private boolean isContained(int auto_id, int optionSet_id, int option_id){
		try {
			String query = GetMySQL.getMySQL(FileName.SELECT_MAPPING);
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setInt(1, auto_id);
			statement.setInt(2, optionSet_id);
			statement.setInt(3, option_id);
			ResultSet resultSet = statement.executeQuery(query);
			statement.close();
			while(resultSet.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	/*
	 * add a mapping record into mapping_table, return it's key
	 * @param	auto_id
	 * 			the primary key of auto
	 * 
	 * @param	optionSet_id
	 * 			the primary key of optionSet
	 * 
	 * @param	option_id
	 * 			the primary key of option
	 * */
	public void addMapping(int auto_id, int optionSet_id, int option_id){
		if(!this.isContained(auto_id, optionSet_id, option_id)){
			try {
				PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(FileName.INSERT_MAPPING));
				statement.setInt(1, auto_id);
				statement.setInt(2, optionSet_id);
				statement.setInt(3, option_id);
				statement.execute();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * delete record(s) whose auto_id equals to auto_id from mapping_table
	 * @param	auto_id
	 * 			auto id
	 * */
	public void deleteMapping(int auto_id){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(FileName.DELETE_MAPPING));
			statement.setInt(1, auto_id);
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
