package com.learning.design.compound.combined;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Composite Pattern
 */
public class FlockComposite implements Quackable
{
	private List<Quackable> quackables = new ArrayList<Quackable> ();
	
	public void add (Quackable quackable)
	{
		quackables.add(quackable);
	}
	
	public void quack()
	{
		Iterator<Quackable> it = quackables.iterator();
		while (it.hasNext())
		{
			it.next().quack();
		}
	}
	
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (5000);
		
		Iterator<Quackable> it = quackables.iterator();
		while (it.hasNext())
		{
			sb.append("\t" + it.toString());
		}
		
		return sb.toString();
	}
	
	public void registerObserver (DuckObserver observer)
	{
		Iterator<Quackable> it = quackables.iterator();
		while (it.hasNext())
		{
			it.next().registerObserver(observer);
		}
	}
	
	/*
	 * This method has no body because when each Quackable quack() it notifies all the observers registered with it.
	 */
	public void notifyObservers ()
	{}
}
