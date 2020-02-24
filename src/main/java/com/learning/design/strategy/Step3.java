package com.learning.design.strategy;

/*
 * This is an extension to Step2.
 * If there are any changes to flyable / MakeSound interfaces then code needs to be changed in all the implementing classes.
 * So below is a better design for this situation.
 * 
 */
public class Step3
{

}
/*
* Separate what changes in the application and ENCAPSULATE it so that the other parts that remain the same are not affected.
* Later any changes made to the encapsulated code like alteration or extending it doesnt affect the rest of the application.
*/
interface FlyBehaviour
{
	void fly ();
}

class FlyWithWings implements FlyBehaviour
{
	public void fly ()
	{
System.out.println("I am FLYing with Wings to SOAR higher and higher");		
	}
}

class DonotFly implements FlyBehaviour
{
	public void fly ()
	{
System.out.println("I DONT FLY");		
	}
}

/*
* Separate what changes in the application and ENCAPSULATE it so that the other parts that remain the same are not affected.
* Later any changes made to the encapsulated code like alteration or extending it doesnt affect the rest of the application.
*/

interface MakeSound
{
	void makeSound ();
}

class Quack implements MakeSound
{
	public void makeSound ()
	{
System.out.println("I AM QUACKING");		
	}
}

class Squeak implements MakeSound 
{
	public void makeSound ()
	{
System.out.println("I AM SQUEAKING");		
	}
}

class Mute implements MakeSound 
{
	public void makeSound ()
	{
System.out.println("I AM SILENT.");		
	}
}

/*
 * Altered Duck class after redesigning
 */

abstract class Duck3
{
	
	/*
	 * Design Principle - Identify the aspects of application that vary and separate them out from those which stays the same.
	 * DISPLAY and SWIM behaviours remain the same. FlyBehaviour and MakeSound are the behaviours that vary.  So these behaviours are separated from the DUCK class and
	 * encapsulated in separate interfaces.
	 * 
	 * Design Principal - Program to an interface not an implementation. Interface means SUPERTYPE
	 * Interface could be either an interface or an abstract class.
	 * that is why FlyBehaviour and MakeSound interfaces are used instead of the implementing classes.
	 * 
	 * Design Principal - Favour Composition over inheritance.
	 * Instead of having all the behaviours in Duck class and child classes inheriting them, those vary are separated and encapsulated into different classes of their own.
	 * This gives better flexibility so as to change the behaviour at runtime. Like having FlyWithWings or DonotFly. 
	 */
	FlyBehaviour flyBehaviour;
	MakeSound makeSound;
	
	/*
	 * getters and setters of encapsulated behaviours for runtime usage if any
	 */
	
	public FlyBehaviour getFlyBehaviour()
	{
		return flyBehaviour;
	}

	public void setFlyBehaviour(FlyBehaviour flyBehaviour)
	{
		this.flyBehaviour = flyBehaviour;
	}

	public MakeSound getMakeSound()
	{
		return makeSound;
	}

	public void setMakeSound(MakeSound makeSound)
	{
		this.makeSound = makeSound;
	}

	/*
	 * All children exhibit this behaviour. 
	 */
	abstract void display ();

	/*
	 * All child classes get this behaviour through inheritance.
	 */
	void swim ()
	{
System.out.println("Inside the swim method of the duck class");
	}	
	
	void performFly ()
	{
		//Delegate to the Behaviour class
		flyBehaviour.fly();
	}
	
	void performSound ()
	{
		//Delegate to the Behaviour class
		makeSound.makeSound();
	}
}

/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class MallardDuck3 extends Duck3
{
	MallardDuck3 ()
	{
		flyBehaviour = new FlyWithWings();
		makeSound = new Quack (); 
	}
	void display ()
	{
System.out.println("I am MallardDuck3");			
	}
}

/*
 * This type of duck has all the 4 behaviours of parent Duck class
 */
class RedHeadDuck3 extends Duck3 
{
	void display ()
	{
System.out.println("I am RedHeadDuck3");			
	}
}

/*
 * This child class doesnt have the fly behaviour.
 * And also the make sound behaviour is different from that of MallardDuck and RedHeadDuck
 */
class RubberDuck3 extends Duck3
{
	void display()
	{
System.out.println("I am RubberDuck3");		
	}
}

/*
 * This child class has only 2 behaviours - display and swim
 * Doesnt have the behaviour - fly and makeSound
 */
class DecoyDuck3 extends Duck3
{
	void display ()
	{
System.out.println("I am DecoyDuck3");		
	}
} 


