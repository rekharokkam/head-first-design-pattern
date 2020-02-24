package com.learning.design.factory;

public class AbstractFactoryPattern
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Need New York style cheese pizza
		AFPizzaStore nyPizzaStore = new AFNYPizzaStore ();
System.out.println(nyPizzaStore.orderPizza("cheese"));

		//Need Chicago style clam pizza
		AFPizzaStore chicagoPizzaStore = new AFNYPizzaStore ();
System.out.println(chicagoPizzaStore.orderPizza("clam"));


	}
}

/*
 * An Abstract Factory gives an interface for creating a family of products. Family here meaning interrelated in a certain context.
 * Each Method of an Abstract Factory is a Factory Method being delcared Abstract. Subclasses of Abstract Factory implement these methods to create concrete products 
 */
interface PizzaIngredientsFactory
{
	Dough createDough ();
	Sauce createSauce ();
	Clam createClam ();
	Cheese createCheese ();
}

/*
 * Subclasses of Abstract Factory produce the same products as that of Abstract Factory but with different implementhations.
 * Here NYPizzaIngredientsFactory & ChicagoPizzaIngredientsFactory are the concrete classes of PizzaIngredientsFactory which produce the same products as that of super class with different implementhations.
 * NYPizzaIngredientsFactory - returning New York Style
 * ChicagoPizzaIngredientsFactory - returning Chicago Style
 */
class NYPizzaIngredientsFactory implements PizzaIngredientsFactory
{
	public Dough createDough ()
	{
		return new ThinCrustDough();
	}
	
	public Sauce createSauce ()
	{
		return new MarinaraSauce ();
	}
	
	public Clam createClam ()
	{
		return new FreshClam ();
	}
	
	public Cheese createCheese ()
	{
		return new ReggianoCheese ();
	}
}

class ChicagoPizzaIngredientsFactory implements PizzaIngredientsFactory
{
	public Dough createDough ()
	{
		return new ThickCrustDough();
	}
	
	public Sauce createSauce ()
	{
		return new PlumTomatoSauce ();
	}
	
	public Clam createClam ()
	{
		return new FrozenClam ();
	}
	
	public Cheese createCheese ()
	{
		return new MozzarellaCheese ();
	}
}

abstract class AFPizzaStore
{	
	protected abstract AFPizza createPizza (String type);
	
	AFPizza orderPizza (String type)
	{
		AFPizza afPizza = createPizza(type);
		
		afPizza.prepare();
		afPizza.bake();
		afPizza.cut();
		afPizza.box();
		
		return afPizza;
	}
}

/*
 * Factory Method Pattern encapsulates the Object creation by letting the subclasses decide which objects to create
 * These subclasses that produce products are called Concrete Creators
 */

class AFNYPizzaStore extends AFPizzaStore
{
	/*
	 * Factory Method implementhation by the subclass
	 */
	protected AFPizza createPizza (String type)
	{
		if (type.equals("cheese"))
		{
			return new CheesePizza(new NYPizzaIngredientsFactory (), "New York Cheese Pizza");
		}
		else if (type.equals("clam"))
		{
			return new ClamPizza (new NYPizzaIngredientsFactory (), "New York Clam Pizza");
		}
		else
			return null;
	}
}

class AFChicagoPizzaStore extends AFPizzaStore
{
	/*
	 * Factory Method implementhation by the subclass
	 */
	protected AFPizza createPizza (String type)
	{
		if (type.equals("cheese"))
		{
			return new CheesePizza(new ChicagoPizzaIngredientsFactory (), "Chicago Cheese Pizza");
		}
		else if (type.equals("clam"))
		{
			return new ClamPizza (new ChicagoPizzaIngredientsFactory (), "Chicago Clam Pizza");
		}
		else
			return null;
	}
}

abstract class AFPizza
{
	PizzaIngredientsFactory pizzaIngredientsFactory;
	
	String name;
	Dough dough;
	Sauce sauce;
	Clam clam;
	Cheese cheese;
	
	AFPizza (PizzaIngredientsFactory pizzaIngredientsFactory, String name)
	{
		this.pizzaIngredientsFactory = pizzaIngredientsFactory;
		this.name = name;
	}
	
	abstract void prepare ();
	
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
	
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (1000);
		sb.append("Name : " + name);
		if (null != dough)
		{
			sb.append(", Dough : " + dough.getName());
		}
		if (null != sauce)
		{
			sb.append(" , Sauce : " + sauce.getName());
		}
		if (null != cheese)
		{
			sb.append(" , Cheese : " + cheese.getName());
		}
		if (null != clam)
		{
			sb.append(" , Clam : " + clam.getName());
		}
		return sb.toString();
		//return "Name : " + name + " , Dough : " + dough.getName() + " , Sauce : " + sauce.getName() + " , Cheese : " + cheese.getName() + " , Clam : " + clam.getName();
	}
}

class CheesePizza extends AFPizza
{
	CheesePizza (PizzaIngredientsFactory pizzaIngredientsFactory, String name)
	{
		super (pizzaIngredientsFactory, name);
	}
	
	void prepare ()
	{
System.out.println("Preparing : " + name);
		dough = pizzaIngredientsFactory.createDough();
		sauce = pizzaIngredientsFactory.createSauce();
		cheese = pizzaIngredientsFactory.createCheese();
	}
}

class ClamPizza extends AFPizza
{
	ClamPizza (PizzaIngredientsFactory pizzaIngredientsFactory, String name)
	{
		super (pizzaIngredientsFactory, name);
	}
	
	void prepare ()
	{
System.out.println("Preparing : " + name);
		dough = pizzaIngredientsFactory.createDough();
		sauce = pizzaIngredientsFactory.createSauce();
		cheese = pizzaIngredientsFactory.createCheese();
		clam = pizzaIngredientsFactory.createClam();
	}
}

abstract class Dough
{
	String name;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}

class ThinCrustDough extends Dough
{
	ThinCrustDough()
	{
		name = "Thin Crust Dough";		
	}
}

class ThickCrustDough extends Dough
{
	ThickCrustDough()
	{
		name = "Thick Crust Dough";		
	}
}

abstract class Sauce
{
	String name;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}	
}

class PlumTomatoSauce extends Sauce
{
	PlumTomatoSauce ()
	{
		name = "Plum Tomato Sauce";
	}
}

class MarinaraSauce extends Sauce
{
	MarinaraSauce ()
	{
		name = "Marinara Sauce";
	}
}

abstract class Clam
{
	String name;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}	
}

class FreshClam extends Clam 
{
	FreshClam ()
	{
		name = "Fresh Clam";
	}
}

class FrozenClam extends Clam
{
	FrozenClam ()
	{
		name = "Frozen Clam";
	}
}

abstract class Cheese
{
	String name;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}

class MozzarellaCheese extends Cheese
{
	MozzarellaCheese ()
	{
		name = "Mozzarella Cheese";
	}
}

class ReggianoCheese extends Cheese
{
	ReggianoCheese ()
	{
		name = "Reggiano Cheese";
	}
}