package edu.escuelaing.arep.taller5;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


/**
 * Hello world!
 *
 */
public class App 
{
	/** Property to balanced the load. */
	private static int ii_cont = 1;
	
	/**
	 * Main class that starts the main thread of the service.
	 * 
	 * @param args String array
	 */
	public static void main(String[] args) {
		port(getPort());
		staticFiles.location("/public");
        get("/", (req, res) -> {
            Map<String, Object> lm_model = new HashMap<String, Object>();
            lm_model.put("valid", true);            
            return new ModelAndView(lm_model, "index.vm");
        }, new VelocityTemplateEngine());
        
        get("/add", (req,res) -> {
        	Map<String, Object> lm_model = new HashMap<String, Object>();
        	String ls_values;
        	String ls_res;
        	int li_cont;
        	
        	li_cont = getCont();
            ls_values = req.queryParams("list");
            ls_res = readURL("http://ec2-100-26-178-129.compute-1.amazonaws.com:3500" + li_cont + "/add?data=" + ls_values);
            lm_model.put("valid", false);
            lm_model.put("message", ls_res);
            li_cont++;
            li_cont = (li_cont > 3)?1:li_cont;
            System.out.println(li_cont);
            setCont(li_cont);
            
            return new ModelAndView(lm_model, "index.vm");
		},new VelocityTemplateEngine());
        
        get("/find", (req,res) -> {
        	int li_cont;
        	String ls_res;
        	
        	li_cont = getCont();
        	ls_res = readURL("http://ec2-100-26-178-129.compute-1.amazonaws.com:3500" + li_cont + "/find");
        	li_cont++;
            li_cont = (li_cont > 3)?1:li_cont;
            setCont(li_cont);
            System.out.println(li_cont);
        	res.type("application/json");
        	
        	return ls_res; 
        });
	}
	
	/**
	 * Reads an reasponse from the server
	 * 
	 * @param as_site url to read
	 */
	private static String readURL(String as_site) {
        String ls_resData = null;
        try {
        	URL siteURL = new URL(as_site);
        	URLConnection urlConnection = siteURL.openConnection();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            ls_resData = "";
            while ((inputLine = reader.readLine()) != null) {
                ls_resData += inputLine;
            }
        } catch (IOException x) {
            ls_resData = "";
            System.err.println(x);
        }
        return ls_resData;
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
		return 1234; 
	}

	/**
	 * Method that returns the value of cont
	 * @return the value of cont
	 */
	public static  int getCont() {
		return ii_cont;
	}

	/**
	 * Method that modifies the value of cont
	 * 
	 * @param ii_i the new value of cont
	 */
	public static  void setCont(int ii_i) {
		ii_cont = ii_i;
	}
	
}