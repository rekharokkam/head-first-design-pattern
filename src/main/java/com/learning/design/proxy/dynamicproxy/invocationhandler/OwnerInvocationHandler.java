package com.learning.design.proxy.dynamicproxy.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.learning.design.proxy.dynamicproxy.PersonBean;

public class OwnerInvocationHandler implements InvocationHandler
{
	private PersonBean personBean;
	
	public OwnerInvocationHandler (PersonBean personBean)
	{
		this.personBean = personBean;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException
	{
		try
		{
			if (method.getName().equals("setHotOrNot"))
			{
				throw new IllegalAccessException ("HotOrNot Attribute can be set only by others");
			}
			return method.invoke(personBean, args);
		}

		/*
		 * this exception occurs when the real subject throws an exception.
		 */
		catch (InvocationTargetException invocationTargetException)
		{
invocationTargetException.printStackTrace(System.err);
		}
		return null;
	}
}
