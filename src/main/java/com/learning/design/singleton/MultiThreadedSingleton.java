package com.learning.design.singleton;

public class MultiThreadedSingleton
{

}

/*
 * There are multiple solutions to avoid multiple instances of singleton being created by multiple threads.
 * 1) Making the getInstance synchronised.
 * 2) Eagerly Created instance of the singleton
 * 3) Double Checked locking 
 */

class ThreadSafeChocolateBoiler
{	
	private static ThreadSafeChocolateBoiler threadSafeChocolateBoiler;
	
	/*
	 * Eagerly Created instance. 
	 * In this approach we rely on the JVM to create the singleton instance when the class is loaded before any other thread accesses the unique instance. 
	 */
	//private static ThreadSafeChocolateBoiler threadSafeChocolateBoiler = new ThreadSafeChocolateBoiler();
	
	/*
	 * Double checked locking
	 */
	//private volatile static ThreadSafeChocolateBoiler threadSafeChocolateBoiler;
	
	private boolean isEmpty;
	private boolean isBoiled;
	
	private ThreadSafeChocolateBoiler() 
	{
		isEmpty = true;
		isBoiled = false;
	}
	
	/*
	 * Synchronise this method to avoid Multiple instances getting created by multiple threads.
	 * This approach is appropriate if this method is not being used by a heavy traffic part of the application as synchronisation can degrade the performance.
	 */
	public static synchronized ThreadSafeChocolateBoiler getInstance ()
	{
		if (null == threadSafeChocolateBoiler)
		{
			threadSafeChocolateBoiler = new ThreadSafeChocolateBoiler ();
		}
		return threadSafeChocolateBoiler;
	}
	
	/*
	public static ThreadSafeChocolateBoiler getInstance ()
	{
		return threadSafeChocolateBoiler;
	}
	*/
	
	/*
	 * In Double checked Locking first the instance is checked to see if its been initialised. If not Then only its synchronised.
	 * So in this approach we only synchronise the first time. 
	 * If performance is an issue in the application then this approach can help a lot with the overhead.
	 * 
	public static ThreadSafeChocolateBoiler getInstance ()
	{
		if (null == threadSafeChocolateBoiler)
		{
			synchronized (MultiThreadedSingleton.class)
			{
				if (null == threadSafeChocolateBoiler)
				{
					threadSafeChocolateBoiler = new ThreadSafeChocolateBoiler ();
				}
			}			
		}
		return threadSafeChocolateBoiler;
	} */
	
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