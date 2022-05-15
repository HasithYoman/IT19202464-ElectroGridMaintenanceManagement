package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/MaintenanceAPI")
public class MaintenanceAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Maintenance maintenanceObj = null;
    
    public MaintenanceAPI() {
        super();
      
    }
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("come to post");
		Maintenance maintenance = new Maintenance();
		String output = maintenance.insertMaintenance(request.getParameter("name"),
				 request.getParameter("area"),
				request.getParameter("place"),
				request.getParameter("time"),
				request.getParameter("status"));
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("came here");
		Maintenance maintenance = new Maintenance();
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = maintenance.updateMaintenance(paras.get("hidMaintenanceIDSave").toString(),
		 paras.get("name").toString(),
		paras.get("area").toString(),
		paras.get("place").toString(),
		paras.get("time").toString(),
		paras.get("status").toString()
		);
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Maintenance maintenance = new Maintenance();
		Map paras = getParasMap(request);
		 String output = maintenance.deleteMaintenance(paras.get("maintenanceID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				 }
	 	}
		catch (Exception e)
			 {
			 	}
		return map;
				}

}
