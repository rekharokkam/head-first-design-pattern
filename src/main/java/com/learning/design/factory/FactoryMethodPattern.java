package com.learning.design.factory;

import java.util.ArrayList;

/*
 * All Factory Patterns encapsulate Object creation.
 * 
 */

public class FactoryMethodPattern
{
	public static void main(String[] args)
	{
		FMPizzaStore nyPizzaStore = new FMNYPizzaStore ();
System.out.println(nyPizzaStore.orderPizza("cheese"));

		FMPizzaStore chicagoPizzaStore = new FMChicagoPizzaStore ();
System.out.println(chicagoPizzaStore.orderPizza("cheese"));

	}
}

/*
 * This class is abstract because its attributes are different for different subclasses even though the behavior (methods) remain the same for all the subclasses
 */
abstract class FMPizza
{
	String dough;
	String name;
	String sauce;
	ArrayList<String> toppings = new ArrayList<String>();
	
	public String getDough()
	{
		return dough;
	}

	public void setDough(String dough)
	{
		this.dough = dough;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSauce()
	{
		return sauce;
	}

	public void setSauce(String sauce)
	{
		this.sauce = sauce;
	}

	public ArrayList<String> getToppings()
	{
		return toppings;
	}

	public void setToppings(ArrayList<String> toppings)
	{
		this.toppings = toppings;
	}

	void prepare ()
	{
System.out.println(name + " is being prepared");		
	}
	
	void bake ()
	{
System.out.println(name + " is being baked");		
	}
	
	void cut ()
	{
System.out.println(name + " is cut");		
	}
	
	void box ()
	{
System.out.println(name + " is boxed");		
	}
	
	public String toString()
	{
		return "Name : " + getName() + " , dough : " + getDough() + "Sauce : " + getSauce() + "toppings : " + getToppings();
	}
}

class NYStyleCheesePizza extends FMPizza
{
	NYStyleCheesePizza ()
	{
		name = "New York Style Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Mariana Sauce";
		toppings.add("Grated Reggiano Cheese");
	}
}

class ChicagoStyleCheesePizza extends FMPizza 
{
	ChicagoStyleCheesePizza ()
	{
		name = "Chicago Style deep dish cheese pizza";
		dough = "Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
	}
}

class NYStyleVeggiePizza extends FMPizza
{
	NYStyleVeggiePizza ()
	{
		name = "New York Style Veggie Pizza";
		dough = "Thin Crust Dough";
		sauce = "Mariana Sauce";
		toppings.add("Grated Reggiano Cheese");
	}
}

class ChicagoStyleVeggiePizza extends FMPizza 
{
	ChicagoStyleVeggiePizza ()
	{
		name = "Chicago Style deep dish veggie pizza";
		dough = "Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
		toppings.add("Shredded Mozzarella Cheese");
	}
}

/*
 * This is Abstract Creator Class.
 * It defines an abstract factory method that the subclasses implement to create products.
 * Any other method implemented in the Abstract Creator are written to operate on the product produced by the factory method.
 * Abstract Creator has no knowledge of the concrete product that would be created in the factory method which is purely decide by the subclasses. 
 */
abstract class FMPizzaStore
{	
	/*
	 * This is the FACTORY METHOD.
	 * Factory method handles Object creation and encapsulates in a subclass.
	 * It is abstract so that the subclasses can decide which class to create and there by client code (here the orderPizza method) does not know the concrete product it is working on.
	 * This method returns a product that is usually being used in the other methods of Superclass - FMPizzaStore.
	 * Factory Method normally returns a single Product.
	 * 
	 */
	protected abstract FMPizza createPizza (String type);
	
	/*
	 * Abstract Creator often contains code that depends on the anstract product returned by the factory method implemented by the subclasses.
	 * 
	 */
	FMPizza orderPizza (String type)
	{
		FMPizza fmPizza = createPizza(type);
		
		fmPizza.prepare();
		fmPizza.bake();
		fmPizza.cut();
		fmPizza.box();
		
		return fmPizza;
	}
}

/*
 * Factory Method Pattern encapsulates the Object creation by letting the subclasses decide which objects to create
 * These subclasses that produce products are called Concrete Creators
 */

class FMNYPizzaStore extends FMPizzaStore
{
	/*
	 * Factory Method implementhation by the subclass
	 */
	protected FMPizza createPizza (String type)
	{
		if (type.equals("cheese"))
		{
			return new NYStyleCheesePizza ();
		}
		else if (type.equals("veggie"))
		{
			return new NYStyleVeggiePizza ();
		}
		else
			return null;
	}
}

class FMChicagoPizzaStore extends FMPizzaStore
{
	/*
	 * Factory Method implementhation by the subclass
	 */
	protected FMPizza createPizza (String type)
	{
		if (type.equals("cheese"))
		{
			return new ChicagoStyleCheesePizza ();
		}
		else if (type.equals("veggie"))
		{
			return new ChicagoStyleVeggiePizza ();
		}
		else
			return null;
	}
}