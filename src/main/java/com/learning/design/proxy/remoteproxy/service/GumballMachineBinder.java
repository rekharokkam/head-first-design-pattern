package com.learning.design.proxy.remoteproxy.service;

import java.rmi.Naming;

import com.learning.design.proxy.remoteproxy.GumballMachineRemote;

public class GumballMachineBinder
{
	public static void main(String[] args)
	{
		int gumballCount = Integer.parseInt(args[0]);
		try
		{
			GumballMachineRemote gumballMachine = new GumballMachine (gumballCount, args[1]);
			Naming.rebind(args[2] + "/gumballMachine", gumballMachine);
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
	}
}
