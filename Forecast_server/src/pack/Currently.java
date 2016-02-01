package pack;

import org.json.simple.JSONObject;

public class Currently {
	//les propriétés
	long time;
	String summary;
	String icon;
	double nearestStormDistance;
	double nearestStormBearing;
	double precipIntensity;
	double precipProbability;
	double temperature;
	double apparentTemperature;
	double dewPoint;
	double humidity;
	double windSpeed;
	double windBearing;
	double visibility;
	double cloudCover;
	double pressure;
	double ozone;
	double temperatureMax;
	double temperatureMin;
	
	public Currently(JSONObject currentlyJSONObject){
		// Get values.
		Object timeObject = currentlyJSONObject.get("time");
		Object summaryObject = currentlyJSONObject.get("summary");
		Object iconObject = currentlyJSONObject.get("icon");
		Object nearestStormDistanceObject = currentlyJSONObject.get("nearestStormDistance");
		Object nearestStormBearingObject = currentlyJSONObject.get("nearestStormBearing");
		Object precipIntensityObject = currentlyJSONObject.get("precipIntensity");
		Object precipProbabilityObject = currentlyJSONObject.get("precipProbability");
		Object temperatureObject = currentlyJSONObject.get("temperature");
		Object apparentTemperatureObject = currentlyJSONObject.get("apparentTemperature");
		Object dewPointObject = currentlyJSONObject.get("dewPoint");
		Object humidityObject = currentlyJSONObject.get("humidity");
		Object windSpeedObject = currentlyJSONObject.get("windSpeed");
		Object windBearingObject = currentlyJSONObject.get("windBearing");
		Object visibilityObject = currentlyJSONObject.get("visibility");
		Object cloudCoverObject = currentlyJSONObject.get("cloudCover");
		Object pressureObject = currentlyJSONObject.get("pressure");
		Object ozoneObject = currentlyJSONObject.get("ozone");
		Object temperatureMinObject = currentlyJSONObject.get("temperatureMin");
		Object temperatureMaxObject = currentlyJSONObject.get("temperatureMax");
		
		
		// Get typed values.
		if(summaryObject != null)
			this.summary = summaryObject.toString();
		if(iconObject != null)
			this.icon = iconObject.toString();
		if(timeObject != null)
			this.time = Long.parseLong(timeObject.toString());
		if(nearestStormDistanceObject != null)
			this.nearestStormDistance = Double.parseDouble(nearestStormDistanceObject.toString());
		if(nearestStormBearingObject != null)
			this.nearestStormBearing = Double.parseDouble(nearestStormBearingObject.toString());
		if(precipIntensityObject != null)
			this.precipIntensity = Double.parseDouble(precipIntensityObject.toString());
		if(precipProbabilityObject != null)
			this.precipProbability = Double.parseDouble(precipProbabilityObject.toString());
		if(temperatureObject != null)
			this.temperature = Double.parseDouble(temperatureObject.toString());
		if(apparentTemperatureObject != null)
			this.apparentTemperature = Double.parseDouble(apparentTemperatureObject.toString());
		if(dewPointObject != null)
			this.dewPoint = Double.parseDouble(dewPointObject.toString());
		if(humidityObject != null)
			this.humidity = Double.parseDouble(humidityObject.toString());
		if(windSpeedObject != null)
			this.windSpeed = Double.parseDouble(windSpeedObject.toString());
		if(windBearingObject != null)
			this.windBearing = Double.parseDouble(windBearingObject.toString());
		if(visibilityObject != null)
			this.visibility = Double.parseDouble(visibilityObject.toString());
		if(cloudCoverObject != null)
			this.cloudCover = Double.parseDouble(cloudCoverObject.toString());
		if(pressureObject != null)
			this.pressure = Double.parseDouble(pressureObject.toString());
		if(ozoneObject != null)
			this.ozone = Double.parseDouble(ozoneObject.toString());
		if(temperatureMaxObject != null)
			this.temperatureMax = Double.parseDouble(temperatureMaxObject.toString());
		if(temperatureMinObject != null)
			this.temperatureMin = Double.parseDouble(temperatureMinObject.toString());
		
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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

	public double getNearestStormDistance() {
		return nearestStormDistance;
	}

	public void setNearestStormDistance(double nearestStormDistance) {
		this.nearestStormDistance = nearestStormDistance;
	}

	public double getNearestStormBearing() {
		return nearestStormBearing;
	}

	public void setNearestStormBearing(double nearestStormBearing) {
		this.nearestStormBearing = nearestStormBearing;
	}

	public double getPrecipIntensity() {
		return precipIntensity;
	}

	public void setPrecipIntensity(double precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public double getPrecipProbability() {
		return precipProbability;
	}

	public void setPrecipProbability(double precipProbability) {
		this.precipProbability = precipProbability;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getApparentTemperature() {
		return apparentTemperature;
	}

	public void setApparentTemperature(double apparentTemperature) {
		this.apparentTemperature = apparentTemperature;
	}

	public double getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(double dewPoint) {
		this.dewPoint = dewPoint;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getWindBearing() {
		return windBearing;
	}

	public void setWindBearing(double windBearing) {
		this.windBearing = windBearing;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}

	public double getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(double cloudCover) {
		this.cloudCover = cloudCover;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getOzone() {
		return ozone;
	}

	public void setOzone(double ozone) {
		this.ozone = ozone;
	}
	
	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	
}
