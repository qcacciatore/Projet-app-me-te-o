package pack;

public class WeatherLike {
	private double tempCurrent;
	private double tempHourly;
	private double tempDaily;
	public double getTempCurrent() {
		return tempCurrent;
	}
	public void setTempCurrent(double tempCurrent) {
		this.tempCurrent = tempCurrent;
	}
	public double getTempHourly() {
		return tempHourly;
	}
	public void setTempHourly(double tempHourly) {
		this.tempHourly = tempHourly;
	}
	public double getTempDaily() {
		return tempDaily;
	}
	public void setTempDaily(double tempDaily) {
		this.tempDaily = tempDaily;
	}
	public WeatherLike(double tempCurrent, double tempHourly, double tempDaily) {
		super();
		this.tempCurrent = tempCurrent;
		this.tempHourly = tempHourly;
		this.tempDaily = tempDaily;
	}
	
	public void adviceCurrent(){
		if(tempCurrent < 0){
			System.out.println("Echarpe et bonnet obligatoire");
		}
		
		if(tempCurrent > 0 && tempCurrent < 10){
			System.out.println("Manteau de rigueur");
		}
		
		if(tempCurrent > 10 && tempCurrent < 20){
			System.out.println("Un pull c'est cool");
		}
		
		if(tempCurrent > 20){
			System.out.println("Tenue méga légère ;)");
		}
		
	}
	
	public void adviceHourly(){
		if(tempHourly < 0){
			System.out.println("Echarpe et bonnet obligatoire");
		}
		
		if(tempHourly > 0 && tempHourly < 10){
			System.out.println("Manteau de rigueur");
		}
		
		if(tempHourly > 10 && tempHourly < 20){
			System.out.println("Un pull c'est cool");
		}
		
		if(tempHourly > 20){
			System.out.println("Tenue méga légère ;)");
		}
		
	}
	
}
