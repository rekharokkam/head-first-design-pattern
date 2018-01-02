package com.learning.design.adapterandfacade;

/*
 * In the below Sample Client needs Duck like behaviour.
 */
public class AdapterPattern
{
	public static void main(String[] args)
	{
		Duck duck = new MallardDuck ();
		Turkey turkey = new WildTurkey ();
		
		/* 
		 * Client Sees only the Target Interface
		 */
		Duck turkeyAdapter = new TurkeyAdapter (turkey);
		
System.out.println("Pure Duck Behaviour Starts Here .............");
		duck.quack();
		duck.fly();
System.out.println("Pure Duck Behaviour Ends Here .............");

System.out.println("\n\nPure Turkey Behaviour Starts Here .............");
		turkey.gobble();
		turkey.fly();
System.out.println("Pure Turkey Behaviour Starts Here .............");

System.out.println("\n\nPure TurkeyAdapter Behaviour Starts Here .............");
		turkeyAdapter.quack();
		turkeyAdapter.fly();
System.out.println("Pure TurkeyAdapter Behaviour Starts Here .............");
	}
}

/*
 * This is the Target Interface - The one Client is interested in.
 */
interface Duck
{
	void quack ();
	void fly ();
}

class MallardDuck implements Duck
{
	public void quack ()
	{
System.out.println("I AM MallardDuck QUACKING");		
	}
	
	public void fly ()
	{
System.out.println("I AM MallardDuck flying");		
	}
}

/*
 * This is the Adaptee
 */
interface Turkey
{
	void gobble ();
	void fly ();
}

class WildTurkey implements Turkey
{
	public void gobble ()
	{
System.out.println("I AM WildTurkey GOBBLING");		
	}
	
	public void fly ()
	{
System.out.println("I AM WildTurkey flying");
	}
}

/*
 * Adapter Implements the Target Interface and is composed with the Adaptee
 */
class TurkeyAdapter implements Duck
{
	private Turkey turkey;
	
	TurkeyAdapter (Turkey turkey)
	{
		this.turkey = turkey;
	}
	
	/*
	 * All the requests are delegated to the Adaptee.
	 * 
	 */
	public void quack ()
	{
		turkey.gobble();
	}

	public void fly ()
	{
		turkey.fly();
	}
}