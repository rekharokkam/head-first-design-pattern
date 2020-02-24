package com.learning.design.observer;

import java.util.Observable;

public class JDKObserverPattern
{}

/*
 * Stimulator for the class which reads the physical weather data and passes on the data to the application.
 */
class JDKWeatherSthation
{
	public static void main(String[] args)
	{
		//Create the Subject
		WeatherObservable observable = new WeatherObservable ();
		
		//Create the Observers. Observers internally register with Subject passed as the parameter.
		JDKCurrentDisplay currentDisplay = new JDKCurrentDisplay (observable);
		JDKWeatherSthats weatherSthats = new JDKWeatherSthats (observable);
		JDKWeatherForecast weatherForeCast = new JDKWeatherForecast (observable);
		
		//Create sthate change
		observable.measurementsChanged(new WeatherData (1000f, 102.10f, 108.78f));
	}
}

/*
 * Negetive side effects of javas Observer Pattern
 * Observable is a class there by OO principle is violated having to program to implmenthation than to the interface
 * Since Observable is a class and needs to be subclassed it hinders from having this behaviour in an existing class which is already subclassesed .
 * There is no possibility of swapping Observable with some other implementhation (Custom) since its a class and not an interface. So there is just one fixed behaviour of java.util.Observable
 * 
 * Observable protects setChanged Method hence it cannot be called unless Observable is subclassed. This means Observable cannot be instantiated and composed in our Objects.
 * Compulsorily it needs to be inherited thereby violates the second design principal Favor Composition over Inheritance.  
 */

class WeatherObservable extends Observable
{
	private WeatherData weatherData;
	
	public WeatherData getWeatherData()
	{
		return weatherData;
	}

	public void setWeatherData(WeatherData weatherData)
	{
		this.weatherData = weatherData;
	}

	public void measurementsChanged (WeatherData weatherData)
	{
		//Reqd for JDK implementhation
		setChanged();
		
		/*
		 * There are 2 ways of notifying the observers with data - PUSH and PULL
		 * PUSH - pass the data to the observers in update method
		 * PULL - Obeservers pull the data they are interested in from the subject reference.
		 * PULL is considered more correct and better option.
		 * 
		 * PUSH is used here and also there is facility for PULL since this method inturn calls the update method of observer passing this as the reference.
		 */
		notifyObservers(weatherData);
	}
}

class JDKCurrentDisplay implements java.util.Observer
{
	//Maintain a local reference of Observable so that registering and unregistering becomes easier at runtime
	private Observable observable;
	
	public JDKCurrentDisplay(Observable observable)
	{
		this.observable = observable;
		
		//Register as Observer for Subject sthate changes
		observable.addObserver(this);
	}
	
	public void update (Observable subject, Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE JDKCurrentDisplay - " + changedSthate);

		//PULL
System.out.println("INSIDE THE JDKCurrentDisplay - " + ((WeatherObservable)observable).getWeatherData());		
	}
}

class JDKWeatherSthats implements java.util.Observer
{
	//Maintain a local reference of Observable so that registering and unregistering becomes easier at runtime
	private Observable observable;

	public JDKWeatherSthats(Observable observable)
	{
		this.observable = observable;
		
		//Register as Observer for Subject sthate changes
		observable.addObserver(this);
	}

	public void update (Observable subject, Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE JDKWeatherSthats - " + changedSthate);

		//PULL
System.out.println("INSIDE THE JDKWeatherSthats - " + ((WeatherObservable)observable).getWeatherData());
	}
}

class JDKWeatherForecast implements java.util.Observer
{
	//Maintain a local reference of the Observable so that registering and unregistering becomes easier at runtime
	private Observable observable;
	
	public JDKWeatherForecast(Observable observable)
	{
		this.observable = observable;
		
		//Register as Observer for Subject sthate changes
		observable.addObserver(this);
	}
	
	public void update (Observable subject, Object changedSthate)
	{
		//PUSH
System.out.println("INSIDE THE JDKWeatherForecast - " + changedSthate);

		//PULL
System.out.println("INSIDE THE JDKWeatherForecast - " + ((WeatherObservable)observable).getWeatherData());	
	}
}
