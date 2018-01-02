package com.learning.design.proxy.remoteproxy.client;

import java.rmi.Naming;

import com.learning.design.proxy.remoteproxy.GumballMachineRemote;
import com.learning.design.proxy.remoteproxy.GumballMonitor;

public class RemoteProxyTest
{
	public static void main(String[] args)
	{
		String locations[] = {"rmi://127.0.0.1/UC/gumballMachine", "rmi://127.0.0.1/SFO/gumballMachine", "rmi://127.0.0.1/LA/gumballMachine"}; 
		try
		{
			for (int i = 0; i < locations.length; i++)
			{
				GumballMachineRemote gumballMachine = (GumballMachineRemote)Naming.lookup(locations[i]);
				GumballMonitor gumballMonitor = new GumballMonitor (gumballMachine);
				gumballMonitor.generateReport();
System.out.println("\n\n");				
			}
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
	}
}
