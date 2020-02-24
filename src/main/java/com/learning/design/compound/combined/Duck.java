package com.learning.design.compound.combined;

public abstract class Duck implements Quackable
{
	protected String duckName;
	
	/*
	 * One class One Responsibility. 
	 * Duck has only one responsibility of Quacking. 
	 * Observer pattern responsibility is handled by a different class (DuckSubject) and all the oberver pattern calls (registerObserver, notifyObservers) are delegated to that class.
	 */
	private DuckObservable observable; 
	
	protected Duck (String name)
	{
		duckName = name;
		observable = new DuckSubject (this);
	}

	protected String getDuckName()
	{
		return duckName;
	}

	protected void setDuckName(String duckName)
	{
		this.duckName = duckName;
	}
	
	public String toString ()
	{
		return duckName;
	}
	
	public void registerObserver (DuckObserver observer)
	{
		observable.registerObserver(observer);
	}
	
	public void notifyObservers ()
	{
		observable.notifyObservers();
	}
}

class MallardDuck extends Duck
{
	public MallardDuck ()
	{
		super ("Mallard Duck");
	}

	public void quack()
	{		
System.out.println(duckName + " Quacking");	
		notifyObservers();
	}
}

class RedHeadDuck extends Duck
{
	public RedHeadDuck ()
	{
		super ("Red Head Duck");
	}

	public void quack()
	{		
System.out.println(duckName + " Quacking");		
		notifyObservers();
	}
}

class DuckCall extends Duck
{
	public DuckCall ()
	{
		super ("Duck Call");
	}

	public void quack()
	{		
System.out.println(duckName + " Kwaking");	
		notifyObservers();
	}
}

class RubberDuck extends Duck
{
	public RubberDuck ()
	{
		super ("Rubber Duck");
	}

	public void quack()
	{		
System.out.println(duckName + " Squeaking");	
		notifyObservers();
	}
}

