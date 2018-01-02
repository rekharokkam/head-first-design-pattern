package com.learning.design.proxy.rmitest.service;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.learning.design.proxy.rmitest.MyRemote;
import com.learning.design.proxy.rmitest.Person;

/*
 * Extending UnicastRemoteObject gives the ability to the subclass (MyRemoteImpl) to act as a remote service.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote
{
	private static final long	serialVersionUID	= -2338416369360549281L;

	/*
	 * No Arg constructor throws exception because superclass (UnicastRemoteObject) no arg constructor throws this exception
	 */
	public MyRemoteImpl ()
		throws RemoteException
	{}
	
	public String getWelcomeNote ()
	{
		return "Hi From the Service Object ";
	}
	
	public void alterPerson (Person person)
	{
		person.setId (2000);		
System.out.println("Person inside of the service is : " + person);		 
	}
	
	public static void main(String[] args)
	{
		try
		{
			/*
			 * When the service Object is registered with RMI, RMI actually puts the stub into the registry.
			 * When ever a client looks up for the service Object in reality it gets the Stub in place of the actual service object.
			 */
			MyRemote remoteService = new MyRemoteImpl ();
			/*
			 * When the service object is bound RMI swaps the service for the stub and puts the stub into registry.
			 * When the service is registered rmiregistry must be running.
			 */
			Naming.rebind(args[0] + "/HelloService", remoteService);			
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
	}
}
