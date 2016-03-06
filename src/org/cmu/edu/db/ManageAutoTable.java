package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageAutoTable {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public ManageAutoTable(){
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
	 * if record <autoName, make, basePrice> is contained is auto_table
	 * @param	autoName
	 * 			the name of automobile
	 * 
	 * @param	make
	 * 			the name of make
	 * 
	 * @param	basePrice
	 * 			the basePrice of automobile
	 * 
	 * @return	if the record is contained in auto_table, return the primary key;
	 * 			otherwise, return -1
	 * */
	public int selectAuto(String autoName, String make, int basePrice){
		int key = -1;
		try {
			String query = GetMySQL.getMySQL(SQL.SELECT_AUTO);
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setString(1, autoName);
			statement.setString(2, make);
			statement.setInt(3, basePrice);
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
	 * add record <autoName, make, basePrice> into auto_table
	 * @param	autoName
	 * 			the name of automobile
	 * 
	 * @param	make
	 * 			the name of make
	 * 
	 * @param	basePrice
	 * 			the basePrice of automobile
	 * 
	 * @return	the primary key of inserted record
	 * */
	public int addAuto(String autoName, String make, int basePrice){
		int key = this.selectAuto(autoName, make, basePrice);
		if(key == -1){
			try {
				PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.INSERT_AUTO));
				statement.setString(1, autoName);
				statement.setString(2, make);
				statement.setInt(3, basePrice);
				statement.execute();
				statement.close();
				key = this.selectAuto(autoName, make, basePrice);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return key;
	}
	
	/*
	 * delete record from auto_table
	 * @param	autoName
	 * 			the name of automobile
	 * 
	 * @param	make
	 * 			the name of make
	 * 
	 * @param	basePrice
	 * 			the basePrice of automobile
	 * */
	public void deleteAuto(String autoName, String make, int basePrice){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.DELETE_AUTO));
			statement.setString(1, autoName);
			statement.setString(2, make);
			statement.setInt(3, basePrice);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * update automobile name
	 * @param	oldAutoName
	 * 			the origional automobile name
	 * 
	 * @param	newAutoName
	 * 			the new automobile name
	 * */
	public void updateAutoName(String oldAutoName, String newAutoName){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.UPDATE_AUTO_NAME));
			statement.setString(1, newAutoName);
			statement.setString(2, oldAutoName);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * update the make of automobile
	 * @param	autoName
	 * 			the name of automobile
	 * 
	 * @param	make
	 * 			the new make name
	 * */
	public void updateAutoMake(String autoName, String make){
		try{
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.UPDATE_AUTO_MAKE));
			statement.setString(1, make);
			statement.setString(2, autoName);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * update the baseprice of automobile
	 * @param	autoName
	 * 			the name of automobile
	 * 
	 * @param	basePrice
	 * 			the new basePrice
	 * 
	 * */
	public void updateAutoBasePrice(String autoName, int basePrice){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.UPDATE_AUTO_BASEPRICE));
			statement.setInt(1, basePrice);
			statement.setString(2, autoName);
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
