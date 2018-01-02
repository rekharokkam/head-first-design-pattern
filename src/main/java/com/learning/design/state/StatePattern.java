package com.learning.design.state;

import java.util.Random;

public class StatePattern
{
	public static void main(String[] args)
	{
		GumballMachineWithStatePattern gumballMachine = new GumballMachineWithStatePattern (2);
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
 * State Pattern - allows an object to alter its behaviour when its internal state changes. The Object will appear to change its class.
 * First Part of the deifnition - when gumball machine is in NoQuarterstate it accepts coin when inserted but the same machine doesnt accept when it is HasQuarterState.
 * Second Part of the deifnition - From the perspective of the client : 
 * 									When an action takes place (when gumball machine state changes) gumball machine exhibitis a total different behaviour as though 
 * 									it was instantiated from a different class.
 * 
 * GumballMachine is the "Context". Context is the class tat can have different internal states.
 * 
 * State Pattern allows Object to have many behaviours based on its internal state.
 */
class GumballMachineWithStatePattern
{
	static final int FREE_GIFT = 2;
	
	private State noQuarterState ;
	private State hasQuarterState ;
	private State soldState;
	private State soldOutState;
	private State winnerState;
	
	private int gumballCount;
	private State currentState = soldOutState;
	
	GumballMachineWithStatePattern (int gumballCount)
	{
		noQuarterState = new NoQuarterState (this);
		hasQuarterState = new HasQuarterState (this);
		soldState = new SoldState (this);		
		soldOutState = new SoldoutState (this);
		winnerState = new WinnerState (this);
		
		this.gumballCount = gumballCount;
		if (gumballCount > 0)
		{
			currentState = noQuarterState;
		}
	}

	public State getHasQuarterState()
	{
		return hasQuarterState;
	}

	public void setHasQuarterState(State hasQuarterState)
	{
		this.hasQuarterState = hasQuarterState;
	}

	public State getNoQuarterState()
	{
		return noQuarterState;
	}

	public void setNoQuarterState(State noQuarterState)
	{
		this.noQuarterState = noQuarterState;
	}

	public State getSoldOutState()
	{
		return soldOutState;
	}

	public void setSoldOutState(State soldOutState)
	{
		this.soldOutState = soldOutState;
	}

	public State getSoldState()
	{
		return soldState;
	}

	public void setSoldState(State soldState)
	{
		this.soldState = soldState;
	}

	public State getCurrentState()
	{
		return currentState;
	}

	public void setCurrentState(State currentState)
	{
		this.currentState = currentState;
	}

	public int getGumballCount()
	{
		return gumballCount;
	}

	public void setGumballCount(int gumballCount)
	{
		this.gumballCount = gumballCount;
	}
	
	public State getWinnerState()
	{
		return winnerState;
	}

	public void setWinnerState(State winnerState)
	{
		this.winnerState = winnerState;
	}

	/*
	 * Whenever a context gets the request it is delegated to the current state to handle.
	 */
	void insertQuarter ()
	{
		currentState.insertQuarter();
	}
	
	void ejectQuarter ()
	{
		currentState.ejectQuarter();
	}
	
	void turnCrank ()
	{
		currentState.turnCrank();
		currentState.dispense();
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
		currentState = noQuarterState;
	}
	
	public String toString ()
	{
		return "\n\ncurrent state - " + currentState.getStateName() + " :: gumballCount - " + gumballCount + " \n\n";
	}
}

/*
 * State defines a common interface for all the concrete states. Since all the states implement the same interface they are interchangeable.
 * 
 *  State is used by the Context Object to represent its internal state and behavior.
 *  State is not to be called by the client directly. It gets the request only from the context object.
 *  
 */
abstract class State
{
	private String stateName;
	protected GumballMachineWithStatePattern gumballMachine;
	
	State (String stateName, GumballMachineWithStatePattern gumballMachine)
	{
		this.stateName = stateName;
		this.gumballMachine = gumballMachine;		
	}
	
	String getStateName ()
	{
		return stateName;
	}
	
	void setStateName (String stateName)
	{
		this.stateName = stateName;
	}
	
	public GumballMachineWithStatePattern getGumballMachine()
	{
		return gumballMachine;
	}

	public void setGumballMachine(GumballMachineWithStatePattern gumballMachine)
	{
		this.gumballMachine = gumballMachine;
	}

	abstract void insertQuarter ();
	abstract void ejectQuarter () ;
	abstract void turnCrank ();
	abstract void dispense () ;
}

/*
 * Concrete States fulfill all the requests made on the Context object.
 * Each State provides its own implementation for a request. Hence Context behaves different when its current state changes.
 */
class NoQuarterState extends State
{
	NoQuarterState (GumballMachineWithStatePattern gumballMachine)
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
	
	HasQuarterState (GumballMachineWithStatePattern gumballMachine)
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
		if ( (winner == 0) && (gumballMachine.getGumballCount() >= GumballMachineWithStatePattern.FREE_GIFT))
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

/*
 * Dispensing of  gum balls i.e the winner could be put in the Soldstate itself but
 * It would mean representing 2 different states (Winner and no winner) in the same state class.
 * So the clarity of the class would be lost.
 * Also the Principle - One class One responsibility would be lost by providing 2 different responsibilities to SoldState class.
 */
class SoldState extends State
{
	SoldState (GumballMachineWithStatePattern gumballMachine)
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
	SoldoutState (GumballMachineWithStatePattern gumballMachine)
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
	WinnerState (GumballMachineWithStatePattern gumballMachine)
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

		for (int i = 0; i < GumballMachineWithStatePattern.FREE_GIFT; i++)
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