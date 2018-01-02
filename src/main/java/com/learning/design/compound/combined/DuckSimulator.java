package com.learning.design.compound.combined;

public class DuckSimulator
{
	public static void main(String[] args)
	{
		DuckAbstractFactory countingFactory = new CountingDuckFactory ();
		
		Quackable mallardDuck = countingFactory.createMallardDuck();
		Quackable redHeadDuck = countingFactory.createRedHeadDuck();
		Quackable duckCall = countingFactory.createDuckCall();
		Quackable rubberDuck = countingFactory.createRubberDuck();
		Quackable gooseAdapter = new GooseAdapter (new Goose ());
		
		FlockComposite duckComposite = new FlockComposite ();
		
		duckComposite.add(mallardDuck);
		duckComposite.add(redHeadDuck);
		duckComposite.add(duckCall);
		duckComposite.add(rubberDuck);
		duckComposite.add(gooseAdapter);
		
		FlockComposite mallardFlock = new FlockComposite ();
		
		Quackable mallardDuck1 = countingFactory.createMallardDuck();
		Quackable mallardDuck2 = countingFactory.createMallardDuck();
		Quackable mallardDuck3 = countingFactory.createMallardDuck();
		Quackable mallardDuck4 = countingFactory.createMallardDuck();
		
		mallardFlock.add(mallardDuck1);
		mallardFlock.add(mallardDuck2);
		mallardFlock.add(mallardDuck3);
		mallardFlock.add(mallardDuck4);
		
		duckComposite.add(mallardFlock);

		DuckObserver quackologist = new Quackologist ();
		duckComposite.registerObserver(quackologist);
		
		duckComposite.quack();
	}
}

class Quackologist implements DuckObserver
{
	public void update (Quackable quackable)
	{
System.out.println(" Quackologist is Observing the quack behaviour of : " + quackable.toString());		
	}
}
