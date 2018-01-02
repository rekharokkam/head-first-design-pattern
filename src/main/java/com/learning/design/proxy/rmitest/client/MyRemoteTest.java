package com.learning.design.proxy.rmitest.client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.learning.design.proxy.rmitest.MyRemote;
import com.learning.design.proxy.rmitest.Person;

public class MyRemoteTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			/*
			 * RMI deserializes the stub automaticalls. So it is necessary to have the stub class on the client side. If tat is not present then the stub will not be deserialized.
			 */
			MyRemote remoteService = (MyRemote)Naming.lookup("rmi://127.0.0.1/" + args[0] + "/HelloService");
//System.out.println("Output from remote service : " + remoteService.getWelcomeNote());
			
			Person person = new Person (1000);
System.out.println("\nPerson before alteration : " + person);			
			remoteService.alterPerson(person);
System.out.println("\nPerson after alteration :: " + person);			
		}
		catch (RemoteException remoteException)
		{
System.err.println("Inside of the remoteexception");			
remoteException.printStackTrace(System.err);			
		}
		catch (Exception exception )
		{
System.err.println("Inside of the general exception");			
exception.printStackTrace(System.err);			
		}
	}
}
