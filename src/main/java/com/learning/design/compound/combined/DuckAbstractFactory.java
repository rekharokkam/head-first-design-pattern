package com.learning.design.compound.combined;

/*
 * Abstract Factory Pattern
 */
public interface DuckAbstractFactory
{
	Quackable createMallardDuck ();
	Quackable createRedHeadDuck ();
	Quackable createDuckCall ();
	Quackable createRubberDuck ();
}

class DuckFactory implements DuckAbstractFactory
{
	public Quackable createMallardDuck ()
	{
		return new MallardDuck ();
	}
	
	public Quackable createRedHeadDuck ()
	{
		return new RedHeadDuck ();
	}
	
	public Quackable createDuckCall ()
	{
		return new DuckCall ();
	}
	
	public Quackable createRubberDuck ()
	{
		return new RubberDuck ();
	}
}

class CountingDuckFactory implements DuckAbstractFactory
{
	public Quackable createMallardDuck ()
	{
		return new QuackCounterDecorator (new MallardDuck ()) ;
	}
	
	public Quackable createRedHeadDuck ()
	{
		return new QuackCounterDecorator (new RedHeadDuck ()) ;
	}
	
	public Quackable createDuckCall ()
	{
		return new QuackCounterDecorator (new DuckCall ()) ;
	}
	
	public Quackable createRubberDuck ()
	{
		return new QuackCounterDecorator (new RubberDuck ()) ;
	}
	
}
