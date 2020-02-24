package com.learning.design.observer;

import java.util.ArrayList;
import java.util.List;

/*
 * Its a Weather Display System with 3 Types of displays - CurrentDisplay, WeatherSthats and WeatherForecast.
 * 3 weather parameters are used for display - Temperature, Pressure and Humidity.
 * There is already a working system - WeatherSthation which reads the physical data and passes onto to the application for use.
 *  
 */

public class MyObserverPattern
{

}

/*
 * Stimulator for the class which reads the physical weather data and passes on the data to the application.
 */
class WeatherSthation
{
	public static void main(String[] args)
	{
		//Create the Subject
		WeatherSubject subject = new WeatherSubject ();
		
		//Create the Observers. Observers internally register with Subject passed as the parameter.
		CurrentDisplay currentDisplay = new CurrentDisplay (subject);
		WeatherSthats weatherSthats = new WeatherSthats (subject);
		WeatherForecast weatherForeCast = new WeatherForecast (subject);
		
		//Create sthate change
		subject.measurementsChanged(new WeatherData (1000f, 102.10f, 108.78f));
	}
}

class WeatherData
{
	//Three sthates of WeatherData
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData () {}
	
	public WeatherData (float temperature, float humidity, float pressure)
	{
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	
	public float getHumidity()
	{
		return humidity;
	}

	public void setHumidity(float humidity)
	{
		this.humidity = humidity;
	}

	public float getPressure()
	{
		return pressure;
	}

	public void setPressure(float pressure)
	{
		this.pressure = pressure;
	}

	public float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}
	
	public String toString ()
	{
		return "Pressure : " + getPressure() + "\t Humidity : " + getHumidity() + "\t Temperature : " + getTemperature();
	}
}

/*
 * Observer Pattern provides an Object design where subjects and observers are loosely coupled.
 * By this loose coupling the objects can interact with each other but have minimum knowledge about each other.
 * Subject only knows about the update method in the Observer interface and has no knowledge about the implmenting classes.
 * New Observers can be added at any point of time.
 * Subject and Observer can be reused independent of each other.
 * Changes to either the subject/Observer class doesnt affect each other
 */
interface Subject
{
	void registerObserver (Observer observer);
	void removeObserver (Observer observer);	
	void notifyObservers ();
}

/*
 * This is the Publisher or the Subject class.
 */
class WeatherSubject implements Subject
{
	private WeatherData weatherData;
	
	//Collection to hold all the Observers
	private List<Observer> observers = new ArrayList<Observer> ();
	
	public WeatherData getWeatherData()
	{
		return weatherData;
	}

	public void setWeatherData(WeatherData weatherData)
	{
		this.weatherData = weatherData;
	}

	public void registerObserver (Observer observer)
	{
		observers.add(observer);
	}

	public void measurementsChanged (WeatherData weatherData)
	{
		this.weatherData = weatherData;
		notifyObservers();
	}
	
	public void removeObserver (Observer observer)
	{
		int index = observers.indexOf(observer);
		if (index >= 0)
		{
			observers.remove(index);
		}
	}

	/*
	 * There are 2 ways of notifying the observers with data - PUSH and PULL
	 * PUSH - pass the data to the observers in update method
	 * PULL - Obeservers pull the data they are interested in from the subject reference they have.
	 * PULL is considered more correct and better option compared to PUSH.
	 */

	public void notifyObservers ()
	{
		for (Observer observer : observers)
		{
			observer.update(weatherData);
		}
	}
}

interface Observer
{
	//changedSthate indicates the Subject sthate that changed.
	void update (Object changedSthate);
}

class CurrentDisplay implements Observer
{
	//Maintain a local reference of the subject so that registering and unregistering becomes easier at runtime
	private Subject subject;
	
	public CurrentDisplay(Subject subject)
	{
		this.subject = subject;
		
		//Register as Observer for Subject sthate changes
		subject.registerObserver(this);
	}
	
	public void update (Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE CurrentDisplay - " + changedSthate);

		//PULL
System.out.println("INSIDE THE CurrentDisplay - " + ((WeatherSubject)subject).getWeatherData());		
	}
}

class WeatherSthats implements Observer
{
	//Maintain a local reference of the subject so that registering and unregistering becomes easier at runtime
	private Subject subject;

	public WeatherSthats(Subject subject)
	{
		this.subject = subject;
		
		//Register as Observer for Subject sthate changes
		subject.registerObserver(this);
	}

	public void update (Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE WeatherSthats - " + changedSthate);

		//PULL
System.out.println("INSIDE THE WeatherSthats - " + ((WeatherSubject)subject).getWeatherData());
	}
}

class WeatherForecast implements Observer
{
	//Maintain a local reference of the subject so that registering and unregistering becomes easier at runtime
	private Subject subject;
	
	public WeatherForecast(Subject subject)
	{
		this.subject = subject;
		
		//Register as Observer for Subject sthate changes
		subject.registerObserver(this);
	}
	
	public void update (Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE WeatherForecast - " + changedSthate);

		//PULL
System.out.println("INSIDE THE WeatherForecast - " + ((WeatherSubject)subject).getWeatherData());	
	}
}