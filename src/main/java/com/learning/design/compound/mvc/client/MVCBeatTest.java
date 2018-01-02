package com.learning.design.compound.mvc.client;

import com.learning.design.compound.mvc.controller.BeatController;
import com.learning.design.compound.mvc.controller.BeatControllerInterface;
import com.learning.design.compound.mvc.model.BeatModel;
import com.learning.design.compound.mvc.model.BeatModelInterface;

public class MVCBeatTest
{
	public static void main(String[] args)
	{
		BeatModelInterface beatModel = new BeatModel ();
		BeatControllerInterface beatController = new BeatController (beatModel);
	}
}
