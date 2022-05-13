package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Supplier {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/egelectrio?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertSupplier(String SupAccNo, String SupUnits, String SupUnitPrice, String SupSdate, String SupEdate)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into supplier(`SupID`,`SupAccNo`,`SupUnits`,`SupUnitPrice`,`SupSdate`,`SupEdate`)" + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, SupAccNo);
			 preparedStmt.setString(3, SupUnits);
			 preparedStmt.setString(4, SupUnitPrice);
			 preparedStmt.setString(5, SupSdate);
			 preparedStmt.setString(6, SupEdate);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newSupplier = readSupplier(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newSupplier + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Supplier.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readSupplier()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Account No</th><th>Units</th><th>Unit Price</th><th>Start Date</th><th>End Date</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from supplier";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String SupID = Integer.toString(rs.getInt("SupID"));
				 String SupAccNo = rs.getString("SupAccNo");
				 String SupUnits = rs.getString("SupUnits");
				 String SupUnitPrice = rs.getString("SupUnitPrice");
				 String SupSdate = rs.getString("SupSdate");
				 String SupEdate = rs.getString("SupEdate");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidSupplierIDUpdate\' name=\'hidSupplierIDUpdate\' type=\'hidden\' value=\'" + SupID + "'>" 
							+ SupAccNo + "</td>"; 
				output += "<td>" + SupUnits + "</td>";
				output += "<td>" + SupUnitPrice + "</td>";
				output += "<td>" + SupSdate + "</td>";
				output += "<td>" + SupEdate + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-supplierid='" + SupID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Supplier.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateSupplier(String SupID, String SupAccNo, String SupUnits, String SupUnitPrice, String SupSdate, String SupEdate)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE supplier SET SupAccNo=?,SupUnits=?,SupUnitPrice=?,SupSdate=?,SupEdate=?"  + "WHERE SupID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, SupAccNo);
			 preparedStmt.setString(2, SupUnits);
			 preparedStmt.setString(3, SupUnitPrice);
			 preparedStmt.setString(4, SupSdate);
			 preparedStmt.setString(5, SupEdate);
			 preparedStmt.setInt(6, Integer.parseInt(SupID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newSupplier = readSupplier();    
			output = "{\"status\":\"success\", \"data\": \"" + newSupplier + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Supplier.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteSupplier(String SupID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from supplier where SupID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(SupID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newSupplier = readSupplier();    
			output = "{\"status\":\"success\", \"data\": \"" +  newSupplier + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Supplier.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
