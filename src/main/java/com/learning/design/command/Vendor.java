package com.learning.design.command;

/*
 * All the vendor classes are the receivers.
 * Receiver knows hot to perform the work requested and it is the receiver which completes the request for an action.
 */
public class Vendor
{

}

abstract class Light
{
	String location;
	
	Light (String location)
	{
		this.location = location;
	}
	void lightOn()
	{
System.out.println(location + " Light is On");		
	}
	void lightOff()
	{
System.out.println(location + " Light is Off");		
	}
}

class BedRoomLight extends Light
{
	BedRoomLight ()
	{
		super ("Bed Room");
	}
}

class KitchenLight extends Light
{
	KitchenLight ()
	{
		super ("Kitchen");
	}
}

abstract class CeilingFan
{
	static final int OFF = 0;
	static final int LOW = 1;
	static final int MEDIUM = 2;
	static final int HIGH = 3;
	
	String location;
	int currentSpeed;
	
	CeilingFan (String location)
	{
		this.location = location;
		currentSpeed = OFF;
	}
	
	void off()
	{
System.out.println(location + " Fan is OFF");		
		currentSpeed = OFF;
	}
	
	void low ()
	{
System.out.println(location + " Fan is LOW");		
		currentSpeed = LOW;
	}
	
	void medium ()
	{
System.out.println(location + " Fan is in MEDIUM speed");		
		currentSpeed = MEDIUM;
	}
	
	void high()
	{
System.out.println(location + " Fan is in HIGH speed");		
		currentSpeed = HIGH;
	}
	
	int getCurrentSpeed ()
	{
		return currentSpeed;
	}
}

class BedRoomCeilignFan extends CeilingFan
{
	BedRoomCeilignFan ()
	{
		super ("Bed Room");
	}
}

class TV
{
	private static final int DEFAULT_VOLUME = 5;
	int volume = DEFAULT_VOLUME;
	void on ()
	{
System.out.println("TV is turned ON");		
	}
	
	void off ()
	{
System.out.println("TV is turned OFF");		
	}
	
	void setVolume (int volume)
	{
		this.volume = volume;
	}
	
	void reset ()
	{
		volume = DEFAULT_VOLUME;
	}
}