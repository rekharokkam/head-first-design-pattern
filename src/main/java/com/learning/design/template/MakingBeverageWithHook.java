package com.learning.design.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakingBeverageWithHook
{
	public static void main(String[] args)
	{
		CaffeineBeverageWithHook coffee = new CoffeeWithHook ();
		coffee.prepareReceipe();
	}
}

/*
 * Applying Hollywood principle - the parent class tells the subclasses "dont call us we will call u". 
 * Its the parent class which calls the abstract methods implemented in the subclasses through inheritance in the template method.
 * 
 * A template method abstracts class defines - abstract methods, concrete methods and hooks.
 */
abstract class CaffeineBeverageWithHook
{
	/*
	 * Step of an algorith is declared abstract when the subclasses must provide an implementhation for the step.
	 * Hook is used when any part of an algorithm is optional.
	 */
	void prepareReceipe ()
	{
		boilWater ();
		brew ();
		pourInCup ();
		if (customerWantsCondiments ())
		{
			addCondiments ();
		}
	}
	
	void boilWater ()
	{
System.out.println("Boiling water for beverage in parent class");		
	}
	
	void pourInCup ()
	{
System.out.println("Pouring the beverage into cup in parent class");		
	}
	
	abstract void brew ();
	abstract void addCondiments ();
	
	/*
	 * This method is called "HOOK".
	 * 
	 * A hook is a method declared in an abstract class and given only a default or an empty implementhation.
	 * A subclass can either override this method or ignore.
	 */
	boolean customerWantsCondiments ()
	{
		return true;
	}
}

class CoffeeWithHook extends CaffeineBeverageWithHook
{
	void brew ()
	{
System.out.println("Dripping Coffee through filter");		
	}
	
	void addCondiments ()
	{
System.out.println("Adding sugar and milk for Coffee");		
	}
	
	/*
	 * Hook is overriden
	 */
	boolean customerWantsCondiments ()
	{
		String userInput = getUserInput ();
		if (userInput.toLowerCase().equals("y"))
		{
			return true;
		}
		return false;
	}
	
	String getUserInput ()
	{
System.out.println("Would you like some condiments to your coffee like sugar and milk ??");
 		BufferedReader reader = null;
 		
 		try
 		{
 			reader = new BufferedReader (new InputStreamReader (System.in));
 			return reader.readLine(); 			
 		}
 		catch (IOException exception)
 		{	
exception.printStackTrace(System.err); 			
 		}
 		finally
 		{
 			if (null != reader)
 			{
 				try
 				{
 					reader.close();
 				}
 				catch (IOException ioException)
 				{
 					//do nothing
 				}
 			}
 		}
 		return "n";
	}
}


/*
 * Hook customerWantsCondiments is ignored in this class. So this subclass goes with the default implementhation of the parent class
 */

class TeaWithHook extends CaffeineBeverageWithHook
{
	void brew ()
	{
System.out.println("Steeping the tea");		
	}
	
	void addCondiments ()
	{
System.out.println("Adding lemon to tea");		
	}
}