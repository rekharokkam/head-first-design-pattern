package com.learning.design.template;

public class MakingBeverage
{
	public static void main(String[] args)
	{
		CaffeineBeverage tea = new Tea ();
		tea.prepareReceipe();
		
System.out.println("\n\n");

		CaffeineBeverage coffee = new Coffee ();
		coffee.prepareReceipe();
	}
}

abstract class CaffeineBeverage
{
	/*
	 * This is the Template Method.
	 * Template Method defines the steps of an algorithm and allows the subclasses to provide implementation for one or more steps.
	 * Template Method defines an algorithm as a set of Steps. One or more steps are defined to be abstract and implemented by the subclasses.
	 * Algorithms structure remains the same and is not altered. 
	 * 
	 * This method is defined final to prevent the subclasses from altering the structure of algorithm.
	 * 
	 * Template method defines the sequence of an algorithm as a set of steps with each step being represented by a method.
	 */
	final void prepareReceipe ()
	{
		boilWater ();
		brew ();
		pourInCup ();
		addCondiment ();
	}
	
	void boilWater ()
	{
System.out.println("Boiling water in parent class");		
	}
	
	void pourInCup ()
	{
System.out.println("Pouring Beverage into Cup in parent class");		
	}
	
	abstract void brew ();
	abstract void addCondiment ();
}

class Tea extends CaffeineBeverage
{
	void brew ()
	{
System.out.println("Steeping the Tea");		
	}
	
	void addCondiment ()
	{
System.out.println("Adding lemon for Tea");		
	}
}

class Coffee extends CaffeineBeverage
{
	void brew ()
	{
System.out.println("Dripping coffee through filter");		
	}
	
	void addCondiment ()
	{
System.out.println("Adding sugar and milk for coffee");		
	}
}

