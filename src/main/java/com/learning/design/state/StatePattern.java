package com.learning.design.sthate;

import java.util.Random;

public class SthatePattern
{
	public static void main(String[] args)
	{
		GumballMachineWithSthatePattern gumballMachine = new GumballMachineWithSthatePattern (2);
System.out.println(gumballMachine);	

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
System.out.println(gumballMachine);
	}
}

/*
 * Sthate Pattern - allows an object to alter its behaviour when its internal sthate changes. The Object will appear to change its class.
 * First Part of the deifnition - when gumball machine is in NoQuartersthate it accepts coin when inserted but the same machine doesnt accept when it is HasQuarterSthate.
 * Second Part of the deifnition - From the perspective of the client : 
 * 									When an action takes place (when gumball machine sthate changes) gumball machine exhibitis a total different behaviour as though
 * 									it was instantiated from a different class.
 * 
 * GumballMachine is the "Context". Context is the class that can have different internal sthates.
 * 
 * Sthate Pattern allows Object to have many behaviours based on its internal sthate.
 */
class GumballMachineWithSthatePattern
{
	static final int FREE_GIFT = 2;
	
	private Sthate noQuarterSthate ;
	private Sthate hasQuarterSthate ;
	private Sthate soldSthate;
	private Sthate soldOutSthate;
	private Sthate winnerSthate;
	
	private int gumballCount;
	private Sthate currentSthate = soldOutSthate;
	
	GumballMachineWithSthatePattern (int gumballCount)
	{
		noQuarterSthate = new NoQuarterSthate (this);
		hasQuarterSthate = new HasQuarterSthate (this);
		soldSthate = new SoldSthate (this);
		soldOutSthate = new SoldoutSthate (this);
		winnerSthate = new WinnerSthate (this);
		
		this.gumballCount = gumballCount;
		if (gumballCount > 0)
		{
			currentSthate = noQuarterSthate;
		}
	}

	public Sthate getHasQuarterSthate()
	{
		return hasQuarterSthate;
	}

	public void setHasQuarterSthate(Sthate hasQuarterSthate)
	{
		this.hasQuarterSthate = hasQuarterSthate;
	}

	public Sthate getNoQuarterSthate()
	{
		return noQuarterSthate;
	}

	public void setNoQuarterSthate(Sthate noQuarterSthate)
	{
		this.noQuarterSthate = noQuarterSthate;
	}

	public Sthate getSoldOutSthate()
	{
		return soldOutSthate;
	}

	public void setSoldOutSthate(Sthate soldOutSthate)
	{
		this.soldOutSthate = soldOutSthate;
	}

	public Sthate getSoldSthate()
	{
		return soldSthate;
	}

	public void setSoldSthate(Sthate soldSthate)
	{
		this.soldSthate = soldSthate;
	}

	public Sthate getCurrentSthate()
	{
		return currentSthate;
	}

	public void setCurrentSthate(Sthate currentSthate)
	{
		this.currentSthate = currentSthate;
	}

	public int getGumballCount()
	{
		return gumballCount;
	}

	public void setGumballCount(int gumballCount)
	{
		this.gumballCount = gumballCount;
	}
	
	public Sthate getWinnerSthate()
	{
		return winnerSthate;
	}

	public void setWinnerSthate(Sthate winnerSthate)
	{
		this.winnerSthate = winnerSthate;
	}

	/*
	 * Whenever a context gets the request it is delegated to the current sthate to handle.
	 */
	void insertQuarter ()
	{
		currentSthate.insertQuarter();
	}
	
	void ejectQuarter ()
	{
		currentSthate.ejectQuarter();
	}
	
	void turnCrank ()
	{
		currentSthate.turnCrank();
		currentSthate.dispense();
	}
	
	void releaseBall ()
	{
System.out.println("Your Gumball is rolling out");		
		if (gumballCount != 0)
		{
			gumballCount -= 1;
		}
	}
	
	void refill (int gumballCount)
	{
		this.gumballCount = gumballCount;
		currentSthate = noQuarterSthate;
	}
	
	public String toString ()
	{
		return "\n\ncurrent sthate - " + currentSthate.getSthateName() + " :: gumballCount - " + gumballCount + " \n\n";
	}
}

/*
 * Sthate defines a common interface for all the concrete sthates. Since all the sthates implement the same interface they are interchangeable.
 * 
 *  Sthate is used by the Context Object to represent its internal sthate and behavior.
 *  Sthate is not to be called by the client directly. It gets the request only from the context object.
 *  
 */
abstract class Sthate
{
	private String sthateName;
	protected GumballMachineWithSthatePattern gumballMachine;
	
	Sthate (String sthateName, GumballMachineWithSthatePattern gumballMachine)
	{
		this.sthateName = sthateName;
		this.gumballMachine = gumballMachine;		
	}
	
	String getSthateName ()
	{
		return sthateName;
	}
	
	void setSthateName (String sthateName)
	{
		this.sthateName = sthateName;
	}
	
	public GumballMachineWithSthatePattern getGumballMachine()
	{
		return gumballMachine;
	}

	public void setGumballMachine(GumballMachineWithSthatePattern gumballMachine)
	{
		this.gumballMachine = gumballMachine;
	}

	abstract void insertQuarter ();
	abstract void ejectQuarter () ;
	abstract void turnCrank ();
	abstract void dispense () ;
}

/*
 * Concrete Sthates fulfill all the requests made on the Context object.
 * Each Sthate provides its own implementhation for a request. Hence Context behaves different when its current sthate changes.
 */
class NoQuarterSthate extends Sthate
{
	NoQuarterSthate (GumballMachineWithSthatePattern gumballMachine)
	{
		super ("No Quarter Sthate", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("Your Cent has been accepted");
		gumballMachine.setCurrentSthate(gumballMachine.getHasQuarterSthate());
	}
	
	public void ejectQuarter ()
	{
System.out.println("Please insert coin before ejecting");		
	}
	
	public void turnCrank ()
	{
System.out.println("Please insert Coin before trying to get the gumball");		
	}
	
	public void dispense ()
	{
System.out.println("There is no coin to dispense the gumball");		
	}
}

class HasQuarterSthate extends Sthate
{
	private Random randomWinner = new Random (System.currentTimeMillis());
	
	HasQuarterSthate (GumballMachineWithSthatePattern gumballMachine)
	{
		super ("Has Quarter Sthate", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("You have already inserted the coin. Please wait till u get the gumball");		
	}
	
	public void ejectQuarter ()
	{
System.out.println("Here is the coin u inserted");
		gumballMachine.setCurrentSthate(gumballMachine.getNoQuarterSthate());
	}
	
	public void turnCrank ()
	{
System.out.println("You have turned the crank and here comes your gumball");
		int winner = randomWinner.nextInt(10);
		if ( (winner == 0) && (gumballMachine.getGumballCount() >= GumballMachineWithSthatePattern.FREE_GIFT))
		{
			gumballMachine.setCurrentSthate(gumballMachine.getWinnerSthate());
		}
		else
		{
			gumballMachine.setCurrentSthate(gumballMachine.getSoldSthate());
		}
	}
	
	public void dispense ()
	{
System.out.println("Please wait..........");		
	}
}

/*
 * Dispensing of  gum balls i.e the winner could be put in the Soldsthate itself but
 * It would mean representing 2 different sthates (Winner and no winner) in the same sthate class.
 * So the clarity of the class would be lost.
 * Also the Principle - One class One responsibility would be lost by providing 2 different responsibilities to SoldSthate class.
 */
class SoldSthate extends Sthate
{
	SoldSthate (GumballMachineWithSthatePattern gumballMachine)
	{
		super ("Sold Sthate", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("You have already inserted the coin. Please wait till u get the gumball");		
	}
	
	public void ejectQuarter ()
	{
System.out.println("Your gumball is on the way so your coin cannot be returned");		
	}
	
	public void turnCrank ()
	{
System.out.println("Gumball is on the way. Please wait");		
	}
	
	public void dispense ()
	{
System.out.println("Your gumball has been dispensed");		
		gumballMachine.releaseBall();
		if (gumballMachine.getGumballCount() > 0)
		{
			gumballMachine.setCurrentSthate(gumballMachine.getNoQuarterSthate());
		}
		else 
		{
			gumballMachine.setCurrentSthate(gumballMachine.getSoldOutSthate());
		}		
	}
}

class SoldoutSthate extends Sthate
{
	SoldoutSthate (GumballMachineWithSthatePattern gumballMachine)
	{		
		super ("Soldout Sthate", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("Your coin cannot be accepted as machine has no gumball");		
	}
	
	public void ejectQuarter ()
	{
System.out.println("There is no coin to reject");		
	}
	
	public void turnCrank ()
	{
System.out.println("You didnt insert coin to give u gumball");		
	}
	
	public void dispense ()
	{
System.out.println("There is no gumball in the machine to dispense");		
	}
}

class WinnerSthate extends Sthate
{
	WinnerSthate (GumballMachineWithSthatePattern gumballMachine)
	{
		super ("Winner Sthate", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("You have already inserted the coin. Please wait till u get the gumball");		
	}
	
	public void ejectQuarter ()
	{
System.out.println("Your gumball is on the way so your coin cannot be returned");		
	}
	
	public void turnCrank ()
	{
System.out.println("Gumball is on the way. Please wait");		
	}
	
	public void dispense ()
	{
System.out.println("Congrats you won. Here is your gift");

		for (int i = 0; i < GumballMachineWithSthatePattern.FREE_GIFT; i++)
		{
			gumballMachine.releaseBall();
		}
		
		if (gumballMachine.getGumballCount() == 0)
		{
			gumballMachine.setCurrentSthate(gumballMachine.getSoldOutSthate());
		}
		else
		{
			gumballMachine.setCurrentSthate(gumballMachine.getNoQuarterSthate());
		}
	}
}