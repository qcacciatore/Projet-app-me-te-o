package pack;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Hourly {
	String summary;
	String icon;
	ArrayList<Currently> data = new ArrayList<Currently>();
	
	
	public Hourly(JSONObject hourlyJSONObject){
		//values
		Object summaryObject = hourlyJSONObject.get("summary");
		Object iconObject = hourlyJSONObject.get("icon");
		
		//get typed values.
				if(summaryObject != null)
					this.summary = summaryObject.toString();
				if(iconObject != null)
					this.icon = iconObject.toString();
				
				JSONArray data = (JSONArray) hourlyJSONObject.get("data");
		        
		        for (Object currentlyJsonObject : data)
				{
		        	Currently hour = new Currently((JSONObject) currentlyJsonObject);
		        	this.data.add(hour);
				}
		        
		        
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public ArrayList<Currently> getData() {
		return data;
	}
	public void setData(ArrayList<Currently> data) {
		this.data = data;
	}

}
