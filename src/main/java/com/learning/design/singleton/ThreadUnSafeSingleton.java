package com.learning.design.singleton;

public class ThreadUnSafeSingleton
{

}

/*
 * Singleton class manages its single instance by itself.
 * No other class can instantiate this class because of its private constructor hence has to go through the singleton class for its single instance.
 * Since the single instance is static a global access point to the single instance is provided.
 * Mostly singleton is created lazily.
 * 
 * Javas implementhation of Singleton involves - private constructor, a static method combined with a static variable.
 */
class ThreadUnsafeChocolateBoiler
{
	/*
	 * static variable for class instance so that only one instance of the class exists and also a global access point to the instance is provided.
	 */
	private static ThreadUnsafeChocolateBoiler threadUnsafeChocolateBoiler;
	
	private boolean isEmpty;
	private boolean isBoiled;
	
	/*
	 * Singleton has no public constructor. Its constructor is always private.
	 * There by avoiding other classes to instantiate this class.
	 */
	private ThreadUnsafeChocolateBoiler() 
	{
		isEmpty = true;
		isBoiled = false;
	}
	
	public static ThreadUnsafeChocolateBoiler getInstance ()
	{
		if (null == threadUnsafeChocolateBoiler)
		{
			threadUnsafeChocolateBoiler = new ThreadUnsafeChocolateBoiler ();
		}
		return threadUnsafeChocolateBoiler;
	}

	public boolean getIsBoiled()
	{
		return isBoiled;
	}

	public void setIsBoiled(boolean isBoiled)
	{
		this.isBoiled = isBoiled;
	}

	public boolean getIsEmpty()
	{
		return isEmpty;
	}

	public void setIsEmpty(boolean isEmpty)
	{
		this.isEmpty = isEmpty;
	}
	
	void fill ()
	{
		if (getIsEmpty())
		{
			//fill the boiler
			setIsEmpty(false);
			setIsBoiled(false);
		}
	}
	
	void drain ()
	{
		if (!getIsEmpty() && getIsBoiled())
		{
			//drain the boiler
			setIsEmpty(true);
		}
	}
	
	void boil ()
	{
		if (!getIsEmpty() && !getIsBoiled())
		{
			setIsBoiled(true);
		}
	}
}
