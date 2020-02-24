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
	
	private Sthate noQuarterSthate ;
	private Sthate hasQuarterSthate ;
	private Sthate soldSthate;
	private Sthate soldOutSthate;
	private Sthate winnerSthate;
	
	private int gumballCount;
	private Sthate currentSthate = soldOutSthate;
	
	public GumballMachine (int gumballCount, String location) throws RemoteException
	{
		noQuarterSthate = new NoQuarterSthate (this);
		hasQuarterSthate = new HasQuarterSthate (this);
		soldSthate = new SoldSthate (this);
		soldOutSthate = new SoldoutSthate (this);
		winnerSthate = new WinnerSthate (this);
		
		this.gumballCount = gumballCount;
		this.location = location;
		
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
	
	public Sthate getSthate ()
	{
		return currentSthate;
	}
}
