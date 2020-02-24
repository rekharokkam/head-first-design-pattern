package com.learning.design.proxy.remoteproxy;

public class GumballMonitor
{
	private GumballMachineRemote gumballMachineRemote;
	
	public GumballMonitor (GumballMachineRemote gumballMachineRemote)
	{
		this.gumballMachineRemote = gumballMachineRemote;
	}
	
	public void generateReport ()
	{
		try
		{
System.out.println("Location : " + gumballMachineRemote.getLocation());
System.out.println("Current inventory : " + gumballMachineRemote.getCount());
System.out.println("Current Sthate : " + gumballMachineRemote.getSthate().getSthateName());
		}
		catch (Exception exception)
		{
System.out.println("Sorry the report cannot be generated now. Please try again after some time");
exception.printStackTrace(System.err);
		}
	}
}
