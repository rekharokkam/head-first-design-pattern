package com.learning.design.compound.mvc.model;

import com.learning.design.compound.mvc.BPMObserver;
import com.learning.design.compound.mvc.BeatObserver;

public class HeartModelAdapter implements BeatModelInterface
{
	HeartModelInterface heartModel;
	
	public HeartModelAdapter (HeartModelInterface heartModel)
	{
		this.heartModel = heartModel;
	}
	
	public int getBPM()
	{
		return heartModel.getHeartRate();	
	}

	public void initialize()
	{
		// TODO Auto-generated method stub

	}

	public void off()
	{
		// TODO Auto-generated method stub

	}

	public void on()
	{
		// TODO Auto-generated method stub

	}

	public void setBPM(int bpm)
	{
		// TODO Auto-generated method stub

	}

	public void notifyBPMObservers()
	{
		heartModel.notifyBPMObservers();
	}

	public void notifyBeatObservers()
	{
		heartModel.notifyBeatObservers();
	}

	public void registerBPMObserver(BPMObserver observer)
	{
		heartModel.registerBPMObserver(observer);
	}

	public void registerBeatObserver(BeatObserver observer)
	{
		heartModel.registerBeatObserver(observer);
	}

	public void removeBPMObserver(BPMObserver observer)
	{
		heartModel.removeBPMObserver(observer);
	}

	public void removeBeatObserver(BeatObserver observer)
	{
		heartModel.removeBeatObserver(observer);
	}
}
