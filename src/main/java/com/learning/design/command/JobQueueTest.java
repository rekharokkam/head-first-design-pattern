package com.learning.design.command;

import java.util.Random;

public class JobQueueTest
{
	public static void main(String[] args)
	{
		JobInvoker [] invokers = {new JobInvoker(1), new JobInvoker(2), new JobInvoker (3)};
		JobCommand[] jobCommands = {new RayTrace(), new CompilerTask (), new DownloadRequester ()};
		
		Random random = new Random ();
		int counter = 0;
		do
		{
			int invokerNumber = random.nextInt(invokers.length);
			int jobCommandNumber = random.nextInt(jobCommands.length);
			
			invokers[invokerNumber].setJobCommand(jobCommands[jobCommandNumber]);
			invokers[invokerNumber].executeJob();			
		}
		while (++counter < 5);
	}
}

interface JobCommand
{
	void execute ();
}

abstract class Job implements JobCommand
{
	String name;
	
	Job (String name)
	{
		this.name = name;
	}

	String getName()
	{
		return name;
	}

	void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
	
	public void execute ()
	{
System.out.println("Executing " + name + " Task");		
	}
}

class RayTrace extends Job
{
	RayTrace ()
	{
		super ("Ray Trace");
	}
}

class CompilerTask extends Job 
{
	CompilerTask ()
	{
		super ("Compiler Task");
	}
}

class DownloadRequester extends Job
{
	DownloadRequester ()
	{
		super ("Download Requester");
	}
}

class JobInvoker
{
	private JobCommand jobCommand;
	private int number = 0;
	
	JobInvoker (int number)
	{		
		this.number = number;
	}
	
	void setJobCommand (JobCommand jobCommand)
	{
		this.jobCommand = jobCommand;
	}
	
	JobCommand getJobCommand ()
	{
		return jobCommand;
	}
	
	void executeJob ()
	{
System.out.println("Invoker " + number + " - Executing the Task " + jobCommand);		
		jobCommand.execute();
	}
}