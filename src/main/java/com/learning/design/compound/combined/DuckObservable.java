package com.learning.design.compound.combined;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Observer Pattern
 */
public interface DuckObservable
{
	void registerObserver (DuckObserver duckObserver);
	void notifyObservers ();	
}

interface DuckObserver
{
	void update (Quackable quackable);
}

/*
 * One class One Responsibility
 */
class DuckSubject implements DuckObservable
{
	private List<DuckObserver> observers = new ArrayList<DuckObserver> ();
	private Quackable duck;
	
	public DuckSubject (Quackable duck)
	{
		this.duck = duck;
	}
	
	public void registerObserver (DuckObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers ()
	{
		Iterator<DuckObserver> it = observers.iterator();
		while (it.hasNext())
		{
			it.next().update(duck);
		}
	}
}