package com.learning.design.strategy;

public class Step1 
{
}	
/*
 * Parent class
 */
abstract class Duck1
{
	/*
	 * All children exhibit this behaviour. 
	 */
	abstract void display ();

	/*
	 * All child classes get this behaviour through inheritance.
	 */
	void swim ()
	{
System.out.println("Inside the swim method of the Duck1 class");
	}
	
	/*
	 * Some child classes have this behaviour and some classes dont.
	 */
	abstract void makeSound ();
	
	/*
	 * Some child classes have this behaviour and some classes dont
	 */
	abstract void fly ();		
}

/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class MallardDuck1 extends Duck1 
{
	void display ()
	{
System.out.println("I am MallardDuck1");			
	}
	
	void makeSound ()
	{
System.out.println("MallardDuck1 - Quack Quack");			
	}
	
	void fly ()
	{
System.out.println("MallardDuck1 - flying");			
	}
}

/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class RedHeadDuck1 extends Duck1 
{
	void display ()
	{
System.out.println("I am RedHeadDuck1");			
	}
	
	void makeSound ()
	{
System.out.println("RedHeadDuck1 - Quack Quack");			
	}
	
	void fly ()
	{
System.out.println("RedHeadDuck1 - flying");			
	}
}

/*
 * This child class doesnt have the fly behaviour.
 * And also the make sound behaviour is different from tat of MallardDuck and RedHeadDuck
 */
class RubberDuck1 extends Duck1
{
	void display()
	{
System.out.println("I am RubberDuck1");		
	}
	
	void makeSound()
	{
System.out.println("RubberDuck1 - Squeak Squeak");		
	}
	
	/*
	 * This duck doesnt fly
	 */
	void fly ()
	{
//do nothing		
	}
}

/*
 * This child class has only 2 behaviours - display and swim
 * Doesnt have the behaviour - fly and makeSound
 */
class DecoyDuck1 extends Duck1
{
	void display ()
	{
System.out.println("I am DecoyDuck1");		
	}
	
	void makeSound ()
	{
//do nothing		
	}
	
	void fly ()
	{
//do nothing		
	}
}

