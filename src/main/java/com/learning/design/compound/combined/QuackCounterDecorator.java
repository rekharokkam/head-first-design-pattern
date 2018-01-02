package com.learning.design.compound.combined;

/*
 * Decorator pattern
 */
public class QuackCounterDecorator implements Quackable
{
	private Quackable duck;
	private static int quackCount;
	
	public QuackCounterDecorator (Quackable duck)
	{
		this.duck = duck;
	}
	
	public void quack ()
	{
		duck.quack();
		quackCount ++;
	}

	public static int getQuackCount()
	{
		return quackCount;
	}	
	
	public void registerObserver (DuckObserver observer)
	{
		duck.registerObserver(observer);
	}
	
	/*
	 * This method has no body because when each Quackable quack() it notifies all the observers registered with it.
	 */
	public void notifyObservers ()
	{}
}
