package edu.escuelaing.arep.taller5.api;

import static spark.Spark.*;

import org.eclipse.jetty.util.StringUtil;

import edu.escuelaing.arep.taller5.db.ApplicationException;
import edu.escuelaing.arep.taller5.db.DbConnection;
public class RestController {
	
	/**
	 * Main class that starts the main thread of the service.
	 * 
	 * @param args String array
	 */
	public static void main(String[] args) {
		port(getPort());
		get("/add", (req,res) -> {
			String ls_data;
			String ls_res;
			
			ls_data = req.queryParams("data");
			ls_res = "";
			try
			{
				if (StringUtil.isNotBlank(ls_data))
				{
					DbConnection.insert(ls_data);
					ls_res = ApplicationException.INSERT_SUCCESSFUL;
				}
				else 
					throw new ApplicationException(ApplicationException.ERROR_NAME);
			}
			catch(ApplicationException e) 
			{
				ls_res = e.getMessage();
			}
            
            return ls_res;
		});
		
		get("/find", (req,res) -> {
			res.type("application/json");
            String ls_res;
            ls_res = "";
            try 
            {
            	ls_res = DbConnection.findAll();
            }
            catch(ApplicationException e) 
			{
				ls_res = "{\"Message\":\""+e.getMessage()+"\"}";
			}
            
            return ls_res;
			
		});
	}
	
	/**
	 * Specifies the port on which the service will run.
	 * 
	 * @return The port where the service will be run.
	 */
	public static int getPort() {    
		if (System.getenv("PORT") != null)
		{            
			return Integer.parseInt(System.getenv("PORT"));      
		} 
		return 4567; 
	}

}
