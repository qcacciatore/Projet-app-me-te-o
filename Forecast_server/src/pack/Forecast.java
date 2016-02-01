package pack;

public class Forecast {

	private double latitude;
	private double longitude;
	private static String timeZone;
	private Currently currently;
	private Hourly hourly;
	private Daily daily;
	
	public Forecast(double latitudeDouble,double longitudeDouble, String mTimeZone, Currently mCurrently, Hourly mHourly, Daily mDaily)
	{
		this.setLatitude(latitudeDouble);
		this.setLongitude(longitudeDouble);
		this.setTimeZone(mTimeZone);
		this.setCurrently(mCurrently);
		this.setHourly(mHourly);
		this.setDaily(mDaily);
	
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public Currently getCurrently() {
		return currently;
	}
	
	public void setCurrently(Currently currently) {
		this.currently = currently;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public static String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	public Hourly getHourly() {
		return hourly;
	}

	public void setHourly(Hourly hourly) {
		this.hourly = hourly;
	}
	
	public Daily getDaily() {
		return daily;
	}

	public void setDaily(Daily daily) {
		this.daily = daily;
	}
	
}
