package com.learning.design.compound.mvc.controller;

import com.learning.design.compound.mvc.model.BeatModelInterface;
import com.learning.design.compound.mvc.view.DJView;

public class BeatController implements BeatControllerInterface
{
	/*
	 * Maintains both View and Model
	 */
	private DJView view;
	private BeatModelInterface beatModel;
	
	public BeatController (BeatModelInterface beatModel)
	{
		/*
		 * Initialises the Model
		 */
		this.beatModel = beatModel;
		beatModel.initialize();
		
		/*
		 * Creates the View
		 */
		view = new DJView (this, beatModel);
		view.createView();
		view.createControls();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
	
	/*
	 * Manipulates the Model for all user actions
	 */
	public void decreaseBPM()
	{
		beatModel.setBPM(beatModel.getBPM() - 1);
	}

	public void increaseBPM()
	{
		beatModel.setBPM(beatModel.getBPM() + 1);
	}

	public void setBPM(int bpm)
	{
		beatModel.setBPM(bpm);
	}

	public void start()
	{
		beatModel.on();
		view.disableStartMenuItem();
		view.enableStopMenuItem();
	}

	public void stop()
	{
		beatModel.off();
		view.disableStopMenuItem();
		view.enableStartMenuItem();
	}
}
