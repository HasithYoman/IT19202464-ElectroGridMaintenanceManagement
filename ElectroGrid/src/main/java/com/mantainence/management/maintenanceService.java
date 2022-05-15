package com.mantainence.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Maintenance;

@Path("/Maintenances")
public class maintenanceService {
	Maintenance maintenanceObj = new Maintenance();
	/*@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems1()
	{
	return "Hello";
	}
	*/
	//read
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return maintenanceObj.readMaintenance();
	}
	
	//insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertManagement(@FormParam("name") String name,
	@FormParam("area") String area,
	@FormParam("place") String place,
	@FormParam("time") String time,
	@FormParam("status") String status)
	{
	String output = maintenanceObj.insertMaintenance(name, area, place, time, status);
	return output;
	

	}
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMaintenance(String maintenanceData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(maintenanceData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String maintenanceID = doc.select("maintenanceID").text();
	String output = maintenanceObj.deleteMaintenance(maintenanceID);
	return output;
	}
	
	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMaintenance(String maintenanceData)
	{
	//Convert the input string to a JSON object
	JsonObject maintenanceObject = new JsonParser().parse(maintenanceData).getAsJsonObject();
	//Read the values from the JSON object
	String maintenanceID = maintenanceObject.get("maintenanceID").getAsString();
	String name = maintenanceObject.get("name").getAsString();
	String area = maintenanceObject.get("area").getAsString();
	String place = maintenanceObject.get("place").getAsString();
	String time = maintenanceObject.get("time").getAsString();
	String status = maintenanceObject.get("status").getAsString();
	String output = maintenanceObj.updateMaintenance(maintenanceID, name, area, place, time,status);
	return output;
	}

}
