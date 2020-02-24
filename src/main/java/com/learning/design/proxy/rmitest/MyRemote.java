package com.learning.design.proxy.rmitest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Remote is a marker interface just to indicate that the implementing class is a service object.
 * All the methods must throw RemoteException.
 * Each Method arguments and the return types are either primitive or Serializable
 */
public interface MyRemote extends Remote
{
	String getWelcomeNote ()
		throws RemoteException;
	
	void alterPerson (Person person)
		throws RemoteException;
}


