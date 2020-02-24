package com.learning.design.proxy.remoteproxy.service;

import java.io.Serializable;
import java.util.Random;

public abstract class Sthate implements Serializable
{
	private String sthateName;
	/*
	 * transient keyword is added to tell the JVM not to serialize this object.
	 */
	transient protected GumballMachine gumballMachine;
	
	Sthate (String sthateName, GumballMachine gumballMachine)
	{
		this.sthateName = sthateName;
		this.gumballMachine = gumballMachine;		
	}
	
	public String getSthateName ()
	{
		return sthateName;
	}
	
	void setSthateName (String sthateName)
	{
		this.sthateName = sthateName;
	}
	
	public GumballMachine getGumballMachine()
	{
		return gumballMachine;
	}

	public void setGumballMachine(GumballMachine gumballMachine)
	{
		this.gumballMachine = gumballMachine;
	}

	abstract void insertQuarter ();
	abstract void ejectQuarter () ;
	abstract void turnCrank ();
	abstract void dispense () ;
}
class NoQuarterSthate extends Sthate
{
	NoQuarterSthate (GumballMachine gumballMachine)
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
	
	HasQuarterSthate (GumballMachine gumballMachine)
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
		if ( (winner == 0) && (gumballMachine.getGumballCount() >= GumballMachine.FREE_GIFT))
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

class SoldSthate extends Sthate
{
	SoldSthate (GumballMachine gumballMachine)
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
	SoldoutSthate (GumballMachine gumballMachine)
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
	WinnerSthate (GumballMachine gumballMachine)
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

		for (int i = 0; i < GumballMachine.FREE_GIFT; i++)
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