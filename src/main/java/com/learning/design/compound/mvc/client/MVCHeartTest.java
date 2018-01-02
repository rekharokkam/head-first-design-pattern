package com.learning.design.compound.mvc.client;

import com.learning.design.compound.mvc.controller.BeatController;
import com.learning.design.compound.mvc.controller.BeatControllerInterface;
import com.learning.design.compound.mvc.model.BeatModelInterface;
import com.learning.design.compound.mvc.model.HeartModel;
import com.learning.design.compound.mvc.model.HeartModelAdapter;
import com.learning.design.compound.mvc.model.HeartModelInterface;

public class MVCHeartTest
{
	public static void main(String[] args)
	{
		HeartModelInterface heartModel = new HeartModel ();
		BeatModelInterface beatModel = new HeartModelAdapter (heartModel);
		BeatControllerInterface beatController = new BeatController (beatModel);
	}
}
