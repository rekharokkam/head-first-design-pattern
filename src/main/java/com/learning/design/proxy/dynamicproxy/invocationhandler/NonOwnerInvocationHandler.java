package com.learning.design.proxy.dynamicproxy.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.learning.design.proxy.dynamicproxy.PersonBean;

public class NonOwnerInvocationHandler implements InvocationHandler
{
	private PersonBean personBean;
	
	public NonOwnerInvocationHandler (PersonBean personBean)
	{
		this.personBean = personBean;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException
	{
		try
		{
			if ( 
					(method.getName().startsWith("get")) 
					|| 
					(method.getName().equals("setHotOrNot"))
				)
			{
				return method.invoke(personBean, args);
			}
			throw new IllegalAccessException ("Only the person can modify his/her profile");
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
