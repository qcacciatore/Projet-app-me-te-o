package pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RecupData {
	

		private static String infos;

		@SuppressWarnings({ "unused" })
		static Forecast getForecastVille(String jsonResult) throws Exception {

	        JSONObject json = (JSONObject)new JSONParser().parse(jsonResult);
	        
	        Forecast forecast = getForecastObjectFromJson(json);
	        
	        if (forecast == null)
	        {
	        	System.out.println("ERROR : Forecast object is null");
	        	return null;
	        }
			return forecast;
	        
	        //testSystem.out.println("SUCCESS : Forecast object constructed and ready to be displayed");
	       
	        //return resultString;
	    }
	    
	    public static Forecast getForecastObjectFromJson(JSONObject json){
	    	if (json == null)
	        {
	        	System.out.println("ERROR : JSON object is null");
	        	return null;
	        }
	        //testSystem.out.println("SUCCESS : JSON object is ready to be parsed");
	        
	        // Get object values one by one.
	        String timezone = (String) json.get("timezone");
	        Object latitude = json.get("latitude");
	        Object longitude =  json.get("longitude");
	        
	        // Parsing currently.
	        JSONObject currentlyJSONObject = (JSONObject) json.get("currently");
	        
	        // Parsing hourly.
	        JSONObject hourlyJSONObject = (JSONObject) json.get("hourly");
	        
	        // Parsing daily.
	        JSONObject dailyJSONObject = (JSONObject) json.get("daily");
	        
	        // Get each values.
	        Currently curentlyObject = new Currently(currentlyJSONObject);
	        Hourly hourlyObject = new Hourly(hourlyJSONObject);
	        Daily dailyObject = new Daily(dailyJSONObject);
	        
	        // Get typed values.
	        double latitudeDouble = new Double(latitude.toString());
	        double longitudeDouble = new Double(longitude.toString());
	        
	        //testSystem.out.println("Creating forecast object...");
	        Forecast forecast = new Forecast(latitudeDouble, longitudeDouble, timezone, curentlyObject, hourlyObject, dailyObject);
			return forecast;
	    	
	    }

	
}

