package com.learning.design.proxy.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.learning.design.proxy.remoteproxy.service.State;

/*
 * A remote proxy acts as a Local Representative to a Remote Object.
 * Remote Object - Object living in the heap of a different JVM or running in a different address space.
 * Local Representative - Object whose methods can be called locally and which in turn gets forwarded to the actual remote Object. 
 */
public interface GumballMachineRemote extends Remote
{
	/*
	 * A method call on the proxy results in the call being transferred over the wire, invoked remotely on the real subject and the result being returned back to the proxy and then to the client.
	 */
	int getCount () throws RemoteException;
	String getLocation () throws RemoteException;
	State getState () throws RemoteException;
}


