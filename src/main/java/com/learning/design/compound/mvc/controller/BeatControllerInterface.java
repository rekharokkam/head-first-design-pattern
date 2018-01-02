package com.learning.design.compound.mvc.controller;

public interface BeatControllerInterface
{
	void start ();
	void stop ();
	void increaseBPM ();
	void decreaseBPM ();
	void setBPM (int bpm);
}
