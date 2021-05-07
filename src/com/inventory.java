package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class inventory {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbcompany", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertInventory(String name, String type, String store, String quantity) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into inventory(`inventoryId`,`inventoryName`,`inventoryType`,`inventoryStore`,`inventoryQuantity`)"
					+ "values ( ?,  ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, store);
			preparedStmt.setString(5, quantity);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newInventorys = readInventory();
			output = "{\"status\":\"success\", \"data\": \"" + newInventorys + "\"}";
			
		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Inventory.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}
	
	public String readInventory() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Inventory Name</th><th>Type</th><th>Store</th><th>Quantity</th><th>Update</th><th>Remove</th></tr>"; 
			
			String query = "select * from inventory";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String inventoryId = Integer.toString(rs.getInt("inventoryId"));
				String inventoryName = rs.getString("inventoryName");
				String inventoryType = rs.getString("inventoryType");
				String inventoryStore = rs.getString("inventoryStore");
				String inventoryQuantity = rs.getString("inventoryQuantity");
				
				// Add into the html table
				
				
				
				output += "<tr><td><input id='hidInventoryIDUpdate' name='hidInventoryIDUpdate' type='hidden' value='"+ inventoryId + "'>" + inventoryName + "</td>";
				output += "<td>" + inventoryType + "</td>";
				output += "<td>" + inventoryStore + "</td>";
				output += "<td>" + inventoryQuantity + "</td>";
				// buttons
				
				   output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
	                        + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger' data-inventoryid='"
	                        + inventoryId + "'>" + "</td></tr>";
				
				
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		} 
		catch (Exception e) {
			output = "Error while reading the Inventorys.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateInventory(String inventoryId, String name, String type, String store, String quantity) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE inventory SET inventoryName=?,inventoryType=?,inventoryStore=?,inventoryQuantity=? WHERE inventoryId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, store);
			preparedStmt.setString(4, quantity);
			preparedStmt.setInt(5, Integer.parseInt(inventoryId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newInventorys = readInventory();
			output = "{\"status\":\"success\", \"data\": \"" + newInventorys + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Inventory.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteInventory(String inventoryId) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from inventory where inventoryId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inventoryId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newInventorys = readInventory();
			output = "{\"status\":\"success\", \"data\": \"" + newInventorys + "\"}";
		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Inventory.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
