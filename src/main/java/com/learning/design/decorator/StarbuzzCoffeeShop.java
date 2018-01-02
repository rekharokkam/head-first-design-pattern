package com.learning.design.decorator;

public class StarbuzzCoffeeShop
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//Plain Espresso
		Beverage beverage1 = new Espresso ();
System.out.println("Beverage Desc : " + beverage1.getDescription() + " , Cost : $" + beverage1.cost());		
		
		//HouseBlend with Double Mocha and Whip
		Beverage beverage2 = new HouseBlend ();
		
		//Just Notice the way the same variable is being used with new references.
		beverage2 = new Mocha (beverage2);
		beverage2 = new Mocha (beverage2);
		beverage2 = new Whip (beverage2);
System.out.println("Full Description : " + beverage2.getDescription() + " , Cost : $" + beverage2.cost());		
		
		//HouseBlend with Soy, Mocha and Whip
		Beverage beverage3 = new HouseBlend ();
		beverage3 = new Soy (beverage3);
		beverage3 = new Mocha (beverage3);
		beverage3 = new Whip (beverage3);
System.out.println("Full Description : " + beverage3.getDescription() + " , Cost : $" + beverage3.cost());
	}

}

abstract class Beverage
{
	String description = "Unknown Beverage";
	
	Beverage () {}
	
	Beverage (String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	abstract double cost ();
}

class HouseBlend extends Beverage
{
	public HouseBlend()
	{
		super ("House Blend");
	}
	
	double cost ()
	{
		return .89;
	}
}

class Espresso extends Beverage
{
	Espresso ()
	{
		super ("Espresso");
	}
	
	double cost ()
	{
		return 1.99;
	}
}

/*
 * This is a Decorator SuperType.
 * 
 * Subclassing sets behaviour of the supertype statically at the compile time. An Objects behaviour can also be extended through Composition and Delegation dynamically at runtime.
 * Decorators have the same Type as the objects they decorate/wrap.
 * Since Decorator has the same interface as tat of the object it decorats Decorated Objects can be passed in the place of Original Objects. Both decorator and the Component are interchangable.
 * This interchangability can be transparent to the client thereby client need not know wether its dealing with the component or the Decorator.
 * Decorator Inherits from the same TYPE as tat of the object it decorates only for TYPE matching not to get the Same Behaviour.
 *  
 * 
 */
abstract class CondimentDecorator extends Beverage
{
	public abstract String getDescription ();
}

class Mocha extends CondimentDecorator
{
	/*
	 * Decorator holds a reference to the Component it Wraps/decorates.
	 * 
	 */
	Beverage beverage;
	
	Mocha (Beverage beverage)
	{
		this.beverage = beverage;
	}
	
	public String getDescription ()
	{
		return beverage.getDescription() + ", Mocha";
	}
	
	/*
	 * Decorator adds new behaviour not by inheriting it from the supertype but by compising.
	 * Decorator adds its own behaviour either before and/or after delegating to the Component it decorates to do the rest of the task
	 * Decorator changes the behaviour of the Component it decorates by adding new functionality before and/or after (or even in the place of) method calls to the component.
	 * Here Mocha first delegates to the Component it decorates Beverage and then adds its own cost there by changes the behaviour of the Component Beverage class.
	 */
	double cost ()
	{
		return .20 + beverage.cost();
	}
}

class Soy extends CondimentDecorator
{
	/*
	 * Decorator holds a reference to the Component it Wraps/decorats.
	 * 
	 */
	Beverage beverage;
	
	Soy (Beverage beverage)
	{
		this.beverage = beverage;
	}
	
	public String getDescription ()
	{
		return beverage.getDescription() + ", Soy";
	}
	
	/*
	 * Decorator adds new behavior not by inheriting it from the super type but by comprising.
	 * Decorator adds its own behaviour either before and/or after delegating to the Component it decorates to do the rest of the task
	 * Decorator changes the behaviour of the Component it decorates by adding new functionality before and/or after (or even in the place of) method calls to the component.
	 * Here Soy first delegates to the Component it decorates Beverage and then adds its own cost there by changes the behaviour of the Component Beverage class.
	 */
	double cost ()
	{
		return .15 + beverage.cost();
	}
}

class Whip extends CondimentDecorator
{
	/*
	 * Decorator holds a reference to the Component it Wraps/decorats.
	 * 
	 */
	Beverage beverage;
	
	Whip (Beverage beverage)
	{
		this.beverage = beverage;
	}
	
	public String getDescription ()
	{
		return beverage.getDescription() + ", Soy";
	}
	
	/*
	 * Decorator adds new behaviour not by inheriting it from the supertype but by compising.
	 * Decorator adds its own behaviour either before and/or after delegating to the Component it decorates to do the rest of the task
	 * Decorator changes the behaviour of the Component it decorates by adding new functionality before and/or after (or even in the place of) method calls to the component.
	 * Here Whip first delegates to the Component it decorates Beverage and then adds its own cost there by changes the behaviour of the Component Beverage class.
	 */
	double cost ()
	{
		return .10 + beverage.cost();
	}
}