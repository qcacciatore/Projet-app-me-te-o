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
		static String getForecastVille(Ville choixVille) throws Exception {

	    	//testSystem.out.println("Beginning request");
	    	URL oracle = new URL("https://api.forecast.io/forecast/679db38f647bd021440af09dab85bb67/"+choixVille.getLatVille()+","+choixVille.getLongVille()   );//url pour la connexion https://api.forecast.io/forecast/APIKEY/LATITUDE,LONGITUDE
	        
	    	BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
	    	
	    	if (in == null)
	    	{
	    		System.out.println("ERROR : No data retrieved");
	    		return null;
	    	}
	    	//testSystem.out.println("SUCCESS : data retrieved !");

	        // Get string
	    	//testSystem.out.println("Decoding string from buffered reader");
	        ArrayList<String> list = new ArrayList<String>();
	        String result = null;
	        String line;

	        while((line = in.readLine()) != null){
	            list.add(line);
	            line = in.readLine();
	        }
	        
	        //System.out.println("Printing results :");
	        for(int i = 0; i < list.size(); i++) {
	        	String infos = (list.get(i)).toString();
	        } 
	        
	        String resultString = list.get(0);
	        //System.out.println("resultstring");
	        //System.out.println(resultString);
	        //System.out.println("");
	        //testSystem.out.println("Converting String into JSON Object...");
	        JSONObject json = (JSONObject)new JSONParser().parse(resultString);
	        
	        Forecast forecast = getForecastObjectFromJson(json);
	        
	        if (forecast == null)
	        {
	        	System.out.println("ERROR : Forecast object is null");
	        	return null;
	        }
	        
	        //testSystem.out.println("SUCCESS : Forecast object constructed and ready to be displayed");
	       
	        return resultString;
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

