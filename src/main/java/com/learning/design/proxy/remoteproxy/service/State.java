package com.learning.design.proxy.remoteproxy.service;

import java.io.Serializable;
import java.util.Random;

public abstract class State implements Serializable
{
	private String stateName;
	/*
	 * transient keyword is added to tell the JVM not to serialize this object.
	 */
	transient protected GumballMachine gumballMachine;
	
	State (String stateName, GumballMachine gumballMachine)
	{
		this.stateName = stateName;
		this.gumballMachine = gumballMachine;		
	}
	
	public String getStateName ()
	{
		return stateName;
	}
	
	void setStateName (String stateName)
	{
		this.stateName = stateName;
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
class NoQuarterState extends State
{
	NoQuarterState (GumballMachine gumballMachine)
	{
		super ("No Quarter State", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("Your Cent has been accepted");
		gumballMachine.setCurrentState(gumballMachine.getHasQuarterState());
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

class HasQuarterState extends State
{
	private Random randomWinner = new Random (System.currentTimeMillis());
	
	HasQuarterState (GumballMachine gumballMachine)
	{
		super ("Has Quarter State", gumballMachine);
	}
	
	public void insertQuarter ()
	{
System.out.println("You have already inserted the coin. Please wait till u get the gumball");		
	}
	
	public void ejectQuarter ()
	{
System.out.println("Here is the coin u inserted");
		gumballMachine.setCurrentState(gumballMachine.getNoQuarterState());
	}
	
	public void turnCrank ()
	{
System.out.println("You have turned the crank and here comes your gumball");
		int winner = randomWinner.nextInt(10);
		if ( (winner == 0) && (gumballMachine.getGumballCount() >= GumballMachine.FREE_GIFT))
		{
			gumballMachine.setCurrentState(gumballMachine.getWinnerState());
		}
		else
		{
			gumballMachine.setCurrentState(gumballMachine.getSoldState());
		}
	}
	
	public void dispense ()
	{
System.out.println("Please wait..........");		
	}
}

class SoldState extends State
{
	SoldState (GumballMachine gumballMachine)
	{
		super ("Sold State", gumballMachine);
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
			gumballMachine.setCurrentState(gumballMachine.getNoQuarterState());
		}
		else 
		{
			gumballMachine.setCurrentState(gumballMachine.getSoldOutState());
		}		
	}
}

class SoldoutState extends State
{
	SoldoutState (GumballMachine gumballMachine)
	{		
		super ("Soldout State", gumballMachine);
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

class WinnerState extends State
{
	WinnerState (GumballMachine gumballMachine)
	{
		super ("Winner State", gumballMachine);
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
			gumballMachine.setCurrentState(gumballMachine.getSoldOutState());
		}
		else
		{
			gumballMachine.setCurrentState(gumballMachine.getNoQuarterState());
		}
	}
}