package org.cmu.edu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManageOptionTable {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/" + Config.DATABASE;
	
	private static final String USER = Config.USER;
	private static final String PASS = Config.PASSWORD;
	
	private Connection conn = null;
	
	public ManageOptionTable(){
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
	 * if record <option, price> is contained in option_table
	 * @param	option
	 * 			the name of option
	 * 
	 * @param	price
	 * 			the price of option
	 * 
	 * @return	if record is contained in option_table, return the primaraykey;
	 * 			otherwise, return -1
	 * */
	public int selectOption(String option, int price){
		int key = -1;
		try {
			String query = GetMySQL.getMySQL(SQL.SELECT_OPTION);
			PreparedStatement statement = this.conn.prepareStatement(query);
			statement.setString(1, option);
			statement.setInt(2, price);
			key = statement.executeQuery(query).getInt(1);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	
	/*
	 * add option record <option, price> into option_table
	 * @param	option
	 * 			the name of option
	 * 
	 * @param	price
	 * 			the price of option
	 * 
	 * @return	the primary key of the new record
	 * */
	public int addOption(String option, int price){
		int key = this.selectOption(option, price);
		if(key == -1){
			try {
				PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.INSERT_OPTION));
				statement.setString(1, option);
				statement.setInt(2, price);
				statement.execute();
				statement.close();
				key= this.selectOption(option, price);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return key;
	}
	
	/*
	 * delete record <option, price> from option_table
	 * @param	option
	 * 			the name of option
	 * 
	 * @param	price
	 * 			the price of option
	 * */
	public void deleteOption(String option, int price){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.DELETE_OPTION));
			statement.setString(1, option);
			statement.setInt(2, price);
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * update record <option, oldPrice> to <option, newPrice> in option_table
	 * @param	option
	 * 			the name of option
	 * 
	 * @param	oldPrice
	 * 			the origional price
	 * 
	 * @param	newPrice
	 * 			new price
	 * */
	public void updateOption(String option, int oldPrice, int newPrice){
		try {
			PreparedStatement statement = this.conn.prepareStatement(GetMySQL.getMySQL(SQL.UPDATE_OPTION));
			statement.setInt(1, newPrice);
			statement.setString(2, option);
			statement.setInt(3, oldPrice);
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
