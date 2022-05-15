package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import javax.ws.rs.Path;


public class Maintenance {
	private static String url = "jdbc:mysql://localhost:3306/eg";
	private static String userName = "root";
	private static String password = "12Hasith3";
	
	
	
	  public Connection connect() { Connection con = null;
	  
	  try { Class.forName("com.mysql.jdbc.Driver"); 
	  con=DriverManager.getConnection(url,userName,password); //For testing
	  System.out.print("Successfully connected"); } catch(Exception e) {
	  System.out.println("Database connection is not success!!!"); }
	  
	  return con; }
	 
	
	/*
	 * private Connection connect() { //Create database connection String dbURL =
	 * "jdbc:mysql://localhost:3306/eg"; String dbUser = "root"; String dbPass =
	 * "12Hasith3";
	 * 
	 * 
	 * 
	 * Connection con = null; try { Class.forName("com.mysql.jdbc.Driver");
	 * //Provide the correct details: DBServer/DBName, username, password con =
	 * DriverManager.getConnection(dbURL, dbUser, dbPass); } catch (Exception e)
	 * {e.printStackTrace();} return con; }
	 */
	
	
	//insert method
		public String insertMaintenance(String name, String area, String place, String time, String status)
		{
		   String output = "";
		  try
		   {
		      Connection con = connect();
		      if (con == null)
		   {
		    return "Error while connecting to the database";
		   }
		   // create a prepared statement
		   String query = "insert into maintenance (maintenanceID,name,area,place,time,status) values (?,?,?,?,?,?)";
		   
		   
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		   // binding values
		   preparedStmt.setInt(1, 0);
		   preparedStmt.setString(2, name);
		   preparedStmt.setString(3, area);
		   preparedStmt.setString(4, place);
		   preparedStmt.setString(5, time);
		   preparedStmt.setString(6, status);
		
		
		  //execute the statement
		  preparedStmt.execute();
		  con.close();
		  
		  String newMaintenance = readMaintenance();
		  output = "{\"status\":\"success\", \"data\": \"" + newMaintenance + "\"}";
		  //output = "Inserted successfully";
		  }
		  catch (Exception e)
		  {
		  //output = "Error while inserting";
		   output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Maintenance.\"}";
		  System.err.println(e.getMessage());
		  }
		  return output;
		  }
		
	
	//read
	
	public String readMaintenance()
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
	   output = "<table border='1'><tr><th>name</th>"
	            +"<th>area</th><th>place</th>"
	            + "<th>time</th>"
	            + "<th>status</th>"
	            + "<th>Update</th><th>Remove</th></tr>";
	   
	    String query = "select * from maintenance";
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	   while (rs.next())
	   {
	     String maintenanceID = Integer.toString(rs.getInt("maintenanceID"));
	     String name = rs.getString("name");
	     String area = rs.getString("area");
	     String place = rs.getString("place");
	     String time = rs.getString("time");
	     String status = rs.getString("status");
	     
	// Add a row into the html table
	     
	    output += "<tr><td><input id='hidMaintenanceIDUpdate' type='hidden' value='"+maintenanceID+"'>"
	    	   + name + "</td>";
	    output += "<td>" + area + "</td>";
	    output += "<td>" + place + "</td>";
	
	    output += "<td>" + time + "</td>";
	    
	    output += "<td>" + status + "</td>";
	// buttons
	    output += "<td><input name='btnUpdate' "
	    + " type='button' value='Update' class='btnUpdate btn btn-secondary' data-maintenanceID='\"+maintenancesID+\"'></td>"
	    + "<td><form method='post' action='items.jsp'>"
	    + "<input name='btnRemove' "
	    + " type='submit' value='Remove' class='btnRemove btn btn-secondary' data-maintenanceID='\"+maintenanceID+\"' >"
	    + "<input name='hidmaintenanceIDDelete' type='hidden' "
	    + " value='" + maintenanceID + "'>" + "</form></td></tr>";
	    }
	    con.close();
	// Complete the html table
	    output += "</table>";
	    }
	   catch (Exception e)
	  {
	      output = "Error while reading the maintenance.";
	      System.err.println(e.getMessage());
	  }
	    return output;
	 }
	
	 //delete
	
	public String deleteMaintenance(String maintenanceID)
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
	      String query = "delete from maintenance where maintenanceID=?";
	      PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	      preparedStmt.setInt(1, Integer.parseInt(maintenanceID));
	// execute the statement
	      preparedStmt.execute();
	      con.close();
	      //output = "Deleted successfully";
	      String newMaintenance = readMaintenance();
		  output = "{\"status\":\"success\", \"data\": \"" + newMaintenance + "\"}";
	 }
	 catch (Exception e)
	 {
	      //output = "Error while deleting the item.";
		 output =  "{\"status\":\"error\", \"data\": \"Error while deleting the Maintenance.\"}";
	      System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	//update
	public String updateMaintenance(String maintenanceID, String name, String area, String place, String time,String status)
	
	{
	    String output = "";
	try
	{
	   Connection con = connect();
	   if (con == null)
	    {  return "Error while connecting to the database for updating."; }
	// create a prepared statement
	  String query = "UPDATE maintenance SET name=?,area=?,place=?,time=?,status=? WHERE maintenanceID=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	   preparedStmt.setString(1, name);
	   preparedStmt.setString(2, area);
	   preparedStmt.setString(3, place);
	   preparedStmt.setString(4, time);
	   preparedStmt.setString(4, status);
	   preparedStmt.setInt(5, Integer.parseInt(maintenanceID));
	// execute the statement
	   preparedStmt.execute();
	   con.close();
	   String newMaintenance = readMaintenance();
	   output = "{\"status\":\"success\", \"data\": \"" + newMaintenance + "\"}";
	   
	   //output = "Updated successfully";
	}
	catch (Exception e)
	{
	   //output = "Error while updating the item.";
		output =  "{\"status\":\"error\", \"data\": \"Error while update the Maintenance.\"}";
	    System.err.println(e.getMessage());
	}
	   return output;
 }



}
