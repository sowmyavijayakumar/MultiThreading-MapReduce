import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WeatherStation {

	String location;
	ArrayList<Measurement> measure;
	static ArrayList<WeatherStation> stations=new ArrayList<WeatherStation>();
	
       //constructor of WeatherStation
	public WeatherStation(String loc,ArrayList<Measurement> m){
		location=loc;
		measure=m;
	}
	
//function to compute average temperature measured by the weather station between startTime & endTime
	public double averageTemperature(int startTime, int endTime) {
		return measure.stream().filter(m -> m.time > startTime && m.time < endTime)
				      .mapToDouble(m -> m.temperature).average().getAsDouble();
	}
	
//function to compute median of all temperatures measured by all the weather stations with a location in listOfLocations
	public static double medianTemperature(int startTime, int endTime,ArrayList<String> listOfLocations) 
	{
	
		ArrayList<Measurement> filteredtemp=(ArrayList<Measurement>) stations.stream().filter(s->listOfLocations.contains(s.location))
											.map(s->s.measure).flatMap(l->l.stream()).collect(Collectors.toList());
		
		ArrayList<Double> temp= (ArrayList<Double>) filteredtemp.stream().filter(s-> s.getTime()>startTime && s.getTime()< endTime)
						.map(s -> s.temperature).collect(Collectors.toList());
		
		Collections.sort(temp);
		Double temparray[]=new Double[temp.size()];
		for (int i = 0; i < temparray.length; i++) 
		temparray[i]=temp.get(i);
		
		double median;
		int middle=temparray.length/2;
		
		if(temparray.length % 2==0)
			median=(temparray[middle]+temparray[middle+1])/2;			
		else
			median=temparray[middle];
		return(median);
	}
	
//function to count number of times temperature is measured by weather stations using MapReduce 
	public static int countTemperatures(double t){
		return stations.parallelStream()
			.map(s->s.measure).flatMap(l->l.stream())
			.reduce(0, (sum, m) -> m.temperature>=t-1 && m.temperature<=t+1 ? sum += 1 : sum , (sum1, sum2) -> sum1 + sum2);
		
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
		ArrayList<Measurement> galwayM=new ArrayList<Measurement> ();
		galwayM.add(new Measurement(10,10.2));
		galwayM.add(new Measurement(11,10.7));
		galwayM.add(new Measurement(12,11.2));
		galwayM.add(new Measurement(13,12.5));
		galwayM.add(new Measurement(14,12.3));
		galwayM.add(new Measurement(15,13.5));
		galwayM.add(new Measurement(16,13.8));
		galwayM.add(new Measurement(17,11.1));
		galwayM.add(new Measurement(18,10.4));
		WeatherStation galwayWS=new WeatherStation("Ireland",galwayM);		
		stations.add(galwayWS);		
		Double avgGalway=galwayWS.averageTemperature(11,18);
		System.out.println("Average temperature at Galway:"+avgGalway);
		
		ArrayList<Measurement> cork=new ArrayList<Measurement>();
		cork.add(new Measurement(10,12.2));
		cork.add(new Measurement(11,13.7));
		cork.add(new Measurement(12,12.2));
		cork.add(new Measurement(13,13.5));
		cork.add(new Measurement(14,14.7));
		cork.add(new Measurement(15,15.6));
		cork.add(new Measurement(16,16.2));
		cork.add(new Measurement(17,17.3));
		cork.add(new Measurement(18,16.1));
		WeatherStation corkWS=new WeatherStation("Ireland",cork);		
		stations.add(corkWS);		
		Double avgcork=corkWS.averageTemperature(11,18);
		System.out.println("Average temperature at Cork:"+avgcork);
	
		
		ArrayList<Measurement> london=new ArrayList<Measurement>();
		london.add(new Measurement(10,11.2));
		london.add(new Measurement(11,12.7));
		london.add(new Measurement(12,13.8));
		london.add(new Measurement(13,14.5));
		london.add(new Measurement(14,15.1));
		london.add(new Measurement(15,18.8));
		london.add(new Measurement(16,17.7));
		london.add(new Measurement(17,14.6));
		london.add(new Measurement(18,15.5));
		london.add(new Measurement(19,14.4));
		WeatherStation londonWS=new WeatherStation("UK",london);		
		stations.add(londonWS);		
		Double avglondon=londonWS.averageTemperature(11,18);
		System.out.println("Average temperature at London:"+avglondon);
		
		ArrayList<String> listoflocations=new ArrayList<String>();
		listoflocations.add("Ireland");
		//listoflocations.add("london");
		
		Double medianGalway=medianTemperature(9, 18, listoflocations);
		System.out.println("Median of temperature in Ireland:"+medianGalway);
		
		int C=countTemperatures(15);
		System.out.println("Count of temperature around 15 degree: "+C);
	
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class Measurement {
	int time;
	double temperature;
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	
	Measurement(int t, double temp) {
		time = t;
		temperature = temp;
	}
}

