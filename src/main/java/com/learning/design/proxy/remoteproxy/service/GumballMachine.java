package com.learning.design.proxy.remoteproxy.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.learning.design.proxy.remoteproxy.GumballMachineRemote;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote
{
	private static final long	serialVersionUID	= -6364926346030257245L;

	public GumballMachine () throws RemoteException {};
	
	static final int FREE_GIFT = 2;
	
	private String location;
	
	private State noQuarterState ;
	private State hasQuarterState ;
	private State soldState;
	private State soldOutState;
	private State winnerState;
	
	private int gumballCount;
	private State currentState = soldOutState;
	
	public GumballMachine (int gumballCount, String location) throws RemoteException
	{
		noQuarterState = new NoQuarterState (this);
		hasQuarterState = new HasQuarterState (this);
		soldState = new SoldState (this);		
		soldOutState = new SoldoutState (this);
		winnerState = new WinnerState (this);
		
		this.gumballCount = gumballCount;
		this.location = location;
		
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
	
	public int getCount() 
	{		
		return gumballCount;
	}

	public String getLocation() 
	{		
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public State getState ()
	{
		return currentState;
	}
}
