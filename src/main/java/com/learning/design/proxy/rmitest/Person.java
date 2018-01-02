package com.learning.design.proxy.rmitest;

import java.io.Serializable;

public class Person implements Serializable
{
	private static final long	serialVersionUID	= -3017842304261191890L;
	private long id;
	
	public Person () {}
	
	public Person (int id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public String toString()
	{
		return "Id : " + id;
	}
}
