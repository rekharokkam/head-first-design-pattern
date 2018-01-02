package com.learning.design.compound.mvc.model;

import com.learning.design.compound.mvc.BPMObserver;
import com.learning.design.compound.mvc.BeatObserver;

public interface BeatModelInterface extends BeatObservable
{
	void initialize ();
	void on ();
	void off ();
	void setBPM (int bpm);
	int getBPM ();	
}

interface BeatObservable 
{
	void registerBeatObserver (BeatObserver observer);
	void registerBPMObserver (BPMObserver observer);
	void removeBeatObserver (BeatObserver observer);
	void removeBPMObserver (BPMObserver observer);
	void notifyBeatObservers ();
	void notifyBPMObservers ();
}

