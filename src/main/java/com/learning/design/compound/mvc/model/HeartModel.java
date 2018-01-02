package com.learning.design.compound.mvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.learning.design.compound.mvc.BPMObserver;
import com.learning.design.compound.mvc.BeatObserver;

public class HeartModel implements HeartModelInterface, Runnable
{
	private int heartRate = 90;
	private int time  = 1000;
	private Random random = new Random (System.currentTimeMillis());
	private Thread thread;
	
	private List<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	private List<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	
	public HeartModel ()
	{
		thread = new Thread (this);
		thread.start();
	}
	
	public void run ()
	{
		int lastRate = -1;
		while (true)
		{
			int change = random.nextInt(10);
			if (random.nextInt(2) == 0)
			{
				change = 0 - change;
			}
			int rate = 60000/ (time + change);
			if (rate < 120 && rate > 50)
			{
				time += change;
				notifyBeatObservers ();
				if (rate != lastRate)
				{
					lastRate = rate;
					notifyBPMObservers();
				}
			}
			try
			{
				Thread.sleep(time);				
			}
			catch (Exception exception)
			{
exception.printStackTrace(System.err);				
			}
		}
	}
	
	public int getHeartRate()
	{
		return 60000/time;
	}

	public void notifyBeatObservers()
	{
		Iterator<BeatObserver> it = beatObservers.iterator();
		while (it.hasNext())
		{
			it.next().updateBeat();
		}
	}

	public void notifyBPMObservers()
	{
		Iterator<BPMObserver> it = bpmObservers.iterator();
		while (it.hasNext())
		{
			it.next().updateBPM();
		}
	}

	public void registerBeatObserver(BeatObserver observer)
	{
		beatObservers.add(observer);		
	}

	public void registerBPMObserver(BPMObserver observer)
	{
		bpmObservers.add(observer);
	}

	public void removeBeatObserver(BeatObserver observer)
	{
		beatObservers.remove(observer);
	}

	public void removeBPMObserver(BPMObserver observer)
	{
		bpmObservers.remove(observer);
	}
}
