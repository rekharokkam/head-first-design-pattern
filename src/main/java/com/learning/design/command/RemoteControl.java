package com.learning.design.command;

/*
 * Command Pattern decouples the Object making a request from the Object that performs the request (Receiver)
 * Requester - Client Object and Receiver - Vendor classes
 * 
 *  
 */
public class RemoteControl
{
	public static void main(String[] args)
	{
		Client client = new Client ();
		client.testCommandPattern();
	}
}

class Invoker
{
	private static final int SLOTS = 7;
	
	Command [] onCommands = new Command [SLOTS];
	Command [] offCommands = new Command [SLOTS];
	Command previousButton;
	
	/*
	 * Invoker holds the Command Object and at some point asks the Command Object to execute a request by calling its execute method.
	 */
	Invoker ()
	{
		Command noCommand = new NoCommand ();
		for (int i = 0; i < SLOTS; i++)
		{
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		previousButton = noCommand;
	}
	
	void setCommand (int slot, Command onCommand, Command offCommand)
	{
		onCommands [slot] = onCommand;
		offCommands [slot] = offCommand;
	}	
	
	void onButtonPress (int slot)
	{		
		onCommands[slot].execute();
		previousButton = onCommands [slot];
	}
	
	void offButtonPress (int slot)
	{		
		offCommands [slot].execute();
		previousButton = offCommands [slot];
	}
	
	void undoButtonPress ()
	{
		previousButton.undo();
	}
}

class Client
{
	Invoker invoker = new Invoker ();
	
	Client ()
	{
		loadInvokerSlots ();
	}
	
	/*
	 * Client is responsible for creating the Command Object and setting its receiver
	 * 
	 */
	private void loadInvokerSlots ()
	{
		Light bedRoomLight = new BedRoomLight ();
		invoker.setCommand(0, new LightOnCommand (bedRoomLight), new LightOffCommand (bedRoomLight));
		
		Light kitchenLight = new KitchenLight ();
		invoker.setCommand(1, new LightOnCommand (kitchenLight), new LightOffCommand (kitchenLight));
		
		CeilingFan bedRoomCeilingFan = new BedRoomCeilignFan ();
		invoker.setCommand(2, new CeilingFanLowCommand (bedRoomCeilingFan), new CeilingFanOffCommand (bedRoomCeilingFan));
		invoker.setCommand(3, new CeilingFanMediumCommand (bedRoomCeilingFan), new CeilingFanOffCommand (bedRoomCeilingFan));
		invoker.setCommand(4, new CeilingFanHighCommand (bedRoomCeilingFan), new CeilingFanOffCommand (bedRoomCeilingFan));
		
		TV tv = new TV ();
		invoker.setCommand(5, new TVOnWithHighVolumeCommand (tv), new TVOffCommand (tv));	
		
		Command[] partyOnCommands = {new LightOnCommand (bedRoomLight), new LightOnCommand (kitchenLight), new CeilingFanHighCommand (bedRoomCeilingFan), new TVOnWithHighVolumeCommand (tv)};
		Command[] partyOffCommands = {new LightOffCommand (bedRoomLight), new LightOffCommand (kitchenLight), new CeilingFanOffCommand (bedRoomCeilingFan), new TVOffCommand (tv)};
		invoker.setCommand(6, new MacroCommand (partyOnCommands), new MacroCommand (partyOffCommands));
	}
	
	void testCommandPattern () 
	{
//		invoker.onButtonPress(0);
//		invoker.offButtonPress(0);
//		invoker.onButtonPress(1);
//		invoker.undoButtonPress();
System.out.println("\n");

//		invoker.onButtonPress(1);
//		invoker.offButtonPress(1);
//		invoker.undoButtonPress();

//		invoker.onButtonPress(3);
//		invoker.offButtonPress(3);
//		invoker.undoButtonPress();
//		invoker.onButtonPress(4);		
//		invoker.undoButtonPress();

		invoker.onButtonPress(6);
System.out.println("\n");
		invoker.offButtonPress(6);
	}
}

/*
 * Command Object encapsulates a request to perform an action (Like to turn on the light) on some object (Bed room Light).
 */
interface Command
{
	/*
	 * execute method encapsulates different actions of receiver to fulfill the request.
	 * In this method Command asks the receiver to perform action(s) to fulfill request.
	 * 
	 * Apart from the Command class no other Object on the outside has the knowledge of what action(s) gets performed and on what Receiver. 
	 * They just know that calling execute() on Command Object would fulfill their request. 
	 */
	void execute ();
	void undo();
}

/*
 * Invoker makes a request by calling the execute method.
 * Concrete Command carries out the request from Invoker by calling different action(s) on the receiver it encapsulates.
 */
class LightOnCommand implements Command
{
	Light receiver ;
	
	LightOnCommand (Light receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		receiver.lightOn();
	}
	
	public void undo ()
	{
		receiver.lightOff();
	}
}

class LightOffCommand implements Command
{
	Light receiver ;
	
	LightOffCommand (Light receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		receiver.lightOff();
	}
	
	public void undo ()
	{
		receiver.lightOn();
	}
}

/*
 * This class is useful when we want to remove the responsibility of handling null.
 */
class NoCommand implements Command
{
	public void execute () {}
	
	public void undo () {}
}

class TVOnWithHighVolumeCommand implements Command
{
	TV receiver;
	
	TVOnWithHighVolumeCommand (TV receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		receiver.on();
		receiver.setVolume(11);
	}
	
	public void undo ()
	{
		receiver.off();
		receiver.reset ();
	}
}

class TVOffCommand implements Command
{
	TV receiver;
	
	TVOffCommand (TV receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		receiver.off();
		receiver.reset ();
	}
	
	public void undo ()
	{
		receiver.on();
		receiver.setVolume(11);
	}
}

class CeilingFanHighCommand implements Command
{
	CeilingFan receiver;
	int previousSpeed;
	
	CeilingFanHighCommand (CeilingFan receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		previousSpeed = receiver.getCurrentSpeed();
		receiver.high();
	}
	
	public void undo ()
	{
		switch (previousSpeed)
		{
			case CeilingFan.LOW :
				receiver.low();
				break;
			case CeilingFan.MEDIUM :
				receiver.medium();
				break;
			case CeilingFan.HIGH :
				receiver.medium();
				break;
			default :
				break;
		}
	}
}

class CeilingFanLowCommand implements Command
{
	CeilingFan receiver;
	int previousSpeed;
	
	CeilingFanLowCommand (CeilingFan receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		previousSpeed = receiver.getCurrentSpeed();
		receiver.low();
	}
	
	public void undo ()
	{
		switch (previousSpeed)
		{
			case CeilingFan.LOW :
				receiver.low();
				break;
			case CeilingFan.MEDIUM :
				receiver.medium();
				break;
			case CeilingFan.HIGH :
				receiver.medium();
				break;
			default :
				break;
		}
	}
}

class CeilingFanMediumCommand implements Command
{
	CeilingFan receiver;
	int previousSpeed;
	
	CeilingFanMediumCommand (CeilingFan receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		previousSpeed = receiver.getCurrentSpeed();
		receiver.medium();
	}
	
	public void undo ()
	{
		switch (previousSpeed)
		{
			case CeilingFan.LOW :
				receiver.low();
				break;
			case CeilingFan.MEDIUM :
				receiver.medium();
				break;
			case CeilingFan.HIGH :
				receiver.medium();
				break;
			default :
				break;
		}
	}
}

class CeilingFanOffCommand implements Command
{
	CeilingFan receiver;
	int previousSpeed;
	
	CeilingFanOffCommand (CeilingFan receiver)
	{
		this.receiver = receiver;
	}
	
	public void execute ()
	{
		previousSpeed = receiver.getCurrentSpeed();
		receiver.off();
	}
	
	public void undo ()
	{
		switch (previousSpeed)
		{
			case CeilingFan.LOW :
				receiver.low();
				break;
			case CeilingFan.MEDIUM :
				receiver.medium();
				break;
			case CeilingFan.HIGH :
				receiver.medium();
				break;
			default :
				break;
		}
	}
}

/*
 * Macro Command is a simple extension of Command which allows multiple commands to be invoked for a request.
 */
class MacroCommand implements Command
{
	Command partyCommands [];
	
	MacroCommand (Command[] partyCommands)
	{
		this.partyCommands = partyCommands;
	}
	
	public void execute ()
	{
		for (int i = 0; i < partyCommands.length; i++)
		{
			partyCommands[i].execute();
		}
	}
	
	public void undo ()
	{
		for (int i = 0; i < partyCommands.length; i++)
		{
			partyCommands[i].undo();
		}
	}
}