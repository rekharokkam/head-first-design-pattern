package com.learning.design.adapterandfacade;

public class LeastKnowledgePrinciple
{

}

class Engine
{
	void start () {}
}

class Door
{
	void lock() {}
}

class Key
{
	boolean turns () 
	{
		return true;
	}
}

class Car
{
	/*
	 * Component of the class. By the Principle We can call the methods on this object
	 */
	Engine engine;
	
	Car ()
	{
		engine = new Engine ();
	}
	
	/*
	 * Key - object sent as a parameter to a method. So calling its methods are legal.
	 */
	void start (Key key)
	{
		/*
		 * Door - object created inside of the method. So calling its methods are legal.
		 */
		Door door = new Door ();
		boolean authorised = key.turns();
		
		if (authorised)
		{
			engine.start();
			/*
			 * Calling a local method is legal.
			 */
			updateDashboarddisplay();
			door.lock();
		}
	}
	
	void updateDashboarddisplay () {}
}
