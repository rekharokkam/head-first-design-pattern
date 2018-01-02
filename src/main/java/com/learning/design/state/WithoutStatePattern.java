package com.learning.design.state;

public class WithoutStatePattern
{
	public static void main(String[] args)
	{
		GumballMachineWithoutStatePattern gumballMachine = new GumballMachineWithoutStatePattern (5);
		
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter();
		gumballMachine.turnCrank();		
System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();
System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
System.out.println(gumballMachine);

//gumballMachine.insertQuarter();
//gumballMachine.turnCrank();
//System.out.println(gumballMachine);		
//
//gumballMachine.insertQuarter();
//gumballMachine.turnCrank();
//System.out.println(gumballMachine);		
//
//gumballMachine.insertQuarter();
//gumballMachine.turnCrank();
//System.out.println(gumballMachine);		
//
//gumballMachine.insertQuarter();
//gumballMachine.turnCrank();
//System.out.println(gumballMachine);		

//gumballMachine.insertQuarter();
//gumballMachine.turnCrank();
//System.out.println(gumballMachine);		

	}
}

class GumballMachineWithoutStatePattern
{
	/*
	 * Different internal states
	 */
	private static final int SOLD_OUT = 0;
	private static final int NO_QUARTER = 1;
	private static final int HAS_QUARTER = 2;
	private static final int SOLD = 4;
	
	int gumballCount = 0;
	int currentState = SOLD_OUT;
	
	GumballMachineWithoutStatePattern (int gumballCount) 
	{
		this.gumballCount = gumballCount;
		if (this.gumballCount > 0)
		{
			currentState = NO_QUARTER;
		}			
	}
	
	public String toString ()
	{
		return "\n\nCurrent State : " + currentState + " \n Number of Gumballs : " + gumballCount + "\n\n";
	}
	
	/*
	 * Different Transtions
	 */
	
	void insertQuarter ()
	{
		if (currentState == HAS_QUARTER)
		{
			printErrorMessage("You have already inserted the coin. Please wait till u get the gumball", "insertQuarter");			
		}
		else if (currentState == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball", "insertQuarter");
		}
		else if (currentState == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "insertQuarter");
		}
		else if (currentState == NO_QUARTER)
		{
System.out.println("Your cent has been accepted");
 			currentState = HAS_QUARTER;
		}
	}
	
	void turnCrank () 
	{
		if (currentState == NO_QUARTER)
		{
			printErrorMessage("Please insert cent to get the gumball", "turnCrank");			
		}
		else if (currentState == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball", "turnCrank");
		}
		else if (currentState == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "turnCrank");
		}
		else if (currentState == HAS_QUARTER)
		{
System.out.println("Your gumball is on the way");
			currentState = SOLD;
			dispense (); 			
		}		
	}
	
	void dispense ()
	{
		if (currentState == NO_QUARTER)
		{
			printErrorMessage("Please insert cent to proceed", "dispense");			
		}
		else if (currentState == HAS_QUARTER)
		{
			printErrorMessage("Please call turnCrank", "dispense");
		}
		else if (currentState == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "dispense");
		}
		else if (currentState == SOLD)
		{			
 			if (gumballCount > 0)
 			{
System.out.println("Here comes your gumball."); 				
 				currentState = NO_QUARTER;
 				gumballCount -= 1;
 				
 				if (gumballCount == 0)
 				{
 					currentState = SOLD_OUT;
 				}
 			}
 			else 
 			{
System.out.println("Sorry not enough gumballs to dispense."); 				
 				currentState = SOLD_OUT;
 			}
		}
	}
	
	void ejectQuarter ()
	{
		if (currentState == NO_QUARTER)
		{
			printErrorMessage("You did not insert any cent to return", "ejectQuarter");			
		}
		else if (currentState == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball. Your money cannot be returned", "ejectQuarter");
		}
		else if (currentState == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "ejectQuarter");
		}
		else if (currentState == HAS_QUARTER)
		{
System.out.println("Your cent is being returned");
 			currentState = NO_QUARTER;
		}
		
	}
	
	private void printErrorMessage (String message, String methodName)
	{
System.out.println("Oops................ Please Wait : " + methodName);
System.out.println(message);
	}
}