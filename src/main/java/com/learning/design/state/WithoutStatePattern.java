package com.learning.design.sthate;

public class WithoutSthatePattern
{
	public static void main(String[] args)
	{
		GumballMachineWithoutSthatePattern gumballMachine = new GumballMachineWithoutSthatePattern (5);
		
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

class GumballMachineWithoutSthatePattern
{
	/*
	 * Different internal sthates
	 */
	private static final int SOLD_OUT = 0;
	private static final int NO_QUARTER = 1;
	private static final int HAS_QUARTER = 2;
	private static final int SOLD = 4;
	
	int gumballCount = 0;
	int currentSthate = SOLD_OUT;
	
	GumballMachineWithoutSthatePattern (int gumballCount)
	{
		this.gumballCount = gumballCount;
		if (this.gumballCount > 0)
		{
			currentSthate = NO_QUARTER;
		}			
	}
	
	public String toString ()
	{
		return "\n\nCurrent Sthate : " + currentSthate + " \n Number of Gumballs : " + gumballCount + "\n\n";
	}
	
	/*
	 * Different Transtions
	 */
	
	void insertQuarter ()
	{
		if (currentSthate == HAS_QUARTER)
		{
			printErrorMessage("You have already inserted the coin. Please wait till u get the gumball", "insertQuarter");			
		}
		else if (currentSthate == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball", "insertQuarter");
		}
		else if (currentSthate == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "insertQuarter");
		}
		else if (currentSthate == NO_QUARTER)
		{
System.out.println("Your cent has been accepted");
 			currentSthate = HAS_QUARTER;
		}
	}
	
	void turnCrank () 
	{
		if (currentSthate == NO_QUARTER)
		{
			printErrorMessage("Please insert cent to get the gumball", "turnCrank");			
		}
		else if (currentSthate == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball", "turnCrank");
		}
		else if (currentSthate == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "turnCrank");
		}
		else if (currentSthate == HAS_QUARTER)
		{
System.out.println("Your gumball is on the way");
			currentSthate = SOLD;
			dispense (); 			
		}		
	}
	
	void dispense ()
	{
		if (currentSthate == NO_QUARTER)
		{
			printErrorMessage("Please insert cent to proceed", "dispense");			
		}
		else if (currentSthate == HAS_QUARTER)
		{
			printErrorMessage("Please call turnCrank", "dispense");
		}
		else if (currentSthate == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "dispense");
		}
		else if (currentSthate == SOLD)
		{			
 			if (gumballCount > 0)
 			{
System.out.println("Here comes your gumball."); 				
 				currentSthate = NO_QUARTER;
 				gumballCount -= 1;
 				
 				if (gumballCount == 0)
 				{
 					currentSthate = SOLD_OUT;
 				}
 			}
 			else 
 			{
System.out.println("Sorry not enough gumballs to dispense."); 				
 				currentSthate = SOLD_OUT;
 			}
		}
	}
	
	void ejectQuarter ()
	{
		if (currentSthate == NO_QUARTER)
		{
			printErrorMessage("You did not insert any cent to return", "ejectQuarter");			
		}
		else if (currentSthate == SOLD)
		{
			printErrorMessage("We are on the way of dispensing your gumball. Your money cannot be returned", "ejectQuarter");
		}
		else if (currentSthate == SOLD_OUT)
		{
			printErrorMessage("There are no gumballs to dispense", "ejectQuarter");
		}
		else if (currentSthate == HAS_QUARTER)
		{
System.out.println("Your cent is being returned");
 			currentSthate = NO_QUARTER;
		}
		
	}
	
	private void printErrorMessage (String message, String methodName)
	{
System.out.println("Oops................ Please Wait : " + methodName);
System.out.println(message);
	}
}