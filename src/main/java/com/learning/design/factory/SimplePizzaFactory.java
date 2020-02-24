package com.learning.design.factory;

public class SimplePizzaFactory
{

	FMPizza createPizza (String type)
	{
		FMPizza pizza = null;
		if (type.equals("cheese"))
		{
			pizza = new NYStyleCheesePizza();
		}
		else if (type.equals("veggie"))
		{
			pizza = new NYStyleVeggiePizza();
		}
			
		return pizza;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SimplePizzaStore pizzaStore = new SimplePizzaStore (new SimplePizzaFactory ());
System.out.println(pizzaStore.orderPizza("cheese")) ;

	}
}

class SimplePizzaStore
{
	SimplePizzaFactory simplePizzaFactory ;
	
	SimplePizzaStore (SimplePizzaFactory simplePizzaFactory)
	{
		this.simplePizzaFactory = simplePizzaFactory;
	}
	
	/*
	 * This part of the code is what varies so has been moved from the part of the code that remains the same.
	FMPizza createPizza (String type)
	{
		FMPizza pizza = null;
		if (type.equals("cheese"))
		{
			pizza = new NYStyleCheesePizza();
		}
		else if (type.equals("veggie"))
		{
			pizza = new NYStyleVeggiePizza();
		}
			
		return pizza;
	}*/
	
	FMPizza orderPizza (String type)
	{
		FMPizza pizza = simplePizzaFactory.createPizza (type);
		
		/*
		 * This is the part of the code that varies. Because in future if any new variety of pizza is added this part of the code changes.
		 * So its been moved out to SimplePizzaFactory class.
		 * If in future if pizza stores pffering changes then this code needs to be modified.
		 * So here this part of the code is not closed for modification.
		 * 
		if (type.equals("cheese"))
		{
			pizza = new NYStyleCheesePizza();
		}
		else if (type.equals("veggie"))
		{
			pizza = new NYStyleVeggiePizza();
		}*/
		
		/*
		 * This part of the code remains the same as the process of making pizza has not changed in years.
		 */
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
}

