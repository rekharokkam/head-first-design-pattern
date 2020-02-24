package com.learning.design.strategy;

/*
 * This is an extension to Step1 class.
 * In this Step since fly and makeSound methods are not exhibited by all the child classes they are moved out of the main class into a separate interface.
 * So that any class exhibiting these behaviours can separately implement these interfaces.
 * Addition of any new Child can implement from the interfaces depending on the behaviours exhibited by them.
 */
public class Step2 
{

}

abstract class Duck2
{
	/*
	 * All children exhibit this behaviour. 
	 */
	abstract void display ();

	/*
	 * All child classes get this behaviour through inheritance.
	 */
	void swim (){
		System.out.println("Inside the swim method of the Duck2 class");
	}
	
	/*
	 * These methods are moved into separate interfaces so that any class exhibiting these behaviours can implement from those interfaces
	 * 
	 * Some child classes have this behaviour and some classes dont.
	 
	abstract void makeSound ();
	
	
	 * Some child classes have this behaviour and some classes dont
	
	abstract void fly ();
	*/		
}

interface Flyable
{
	void fly ();
}

interface Quackable
{
	void quack ();
}



/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class MallardDuck2 extends Duck2 implements Flyable, Quackable 
{
	void display ()
	{
System.out.println("I am MallardDuck2");			
	}
	
	public void quack ()
	{
System.out.println("MallardDuck2 - Quack Quack");			
	}
	
	public void fly ()
	{
System.out.println("MallardDuck2 - flying");			
	}
}

/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class RedHeadDuck2 extends Duck2 implements Flyable, Quackable 
{
	void display ()
	{
System.out.println("I am RedHeadDuck2");			
	}
	
	public void quack ()
	{
System.out.println("RedHeadDuck2 - Quack Quack");			
	}
	
	public void fly ()
	{
System.out.println("RedHeadDuck2 - flying");			
	}
}

/*
 * This child class doesnt have the fly behaviour.
 * And also the make sound behaviour is different from that of MallardDuck and RedHeadDuck
 */
class RubberDuck2 extends Duck2 implements Quackable
{
	void display()
	{
System.out.println("I am RubberDuck2");		
	}
	
	public void quack()
	{
System.out.println("RubberDuck2 - Squeak Squeak");		
	}	
}

/*
 * This child class has only 2 behaviours - display and swim
 * Doesnt have the behaviour - fly and makeSound
 */
class DecoyDuck2 extends Duck2 
{
	void display ()
	{
System.out.println("I am DecoyDuck2");		
	}
}