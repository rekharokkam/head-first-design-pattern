package com.learning.design.adapterandfacade;

public class FacadePattern
{
	public static void main(String[] args)
	{
		/*
		 * Playing the movie without Facade
		 */
//		Amplifier amplifier = new Amplifier ();
//		PopcornPopper popcornPopper = new PopcornPopper ();
//		TheatreLight theatreLight = new TheatreLight ();
//		Projector projector = new Projector ();
//		DVDPlayer dvdplayer = new DVDPlayer ();
//		
//		popcornPopper.on();
//		popcornPopper.pop();
//		
//		theatreLight.on();
//		theatreLight.dim(10);
//		
//		projector.on();
//		projector.wideScreenMode();
//		
//		amplifier.on();
//		amplifier.setDvd();
//		amplifier.setSurroundSound();
//		amplifier.setVolume(5);
//		
//		dvdplayer.on();
//		dvdplayer.play("Sarkar");
		
		/*
		 * Ending the movie without Facade
		 */
//		popcornPopper.off();
//		
//		theatreLight.off();
//		
//		projector.off();
//		
//		amplifier.off();
//		
//		dvdplayer.stop();
//		dvdplayer.eject();
//		dvdplayer.off();		

		
		/*
		 * Facade makes the complex subsytem easy to be used providing simple interface. 
		 * Complex subsystem is still available for use directly for any of the advanced functionality (Code snippet above is the direct use of the complex subsystem).
		 * 
		 * Facade decouples the client from the subsystem. 
		 * 
		 * 
		 * Facade and Least Knowledge Principle - Client talks only to One class, Facade. So having only One friend is a GOOD thing in OO programming.
		 * The Subsystem can be upgraded or modified without the client having to make any changes because of Facade. Hence Facade decouples the client from subsystem.
		 */
		HomeThreaterFacade facade = new HomeThreaterFacade ();
		facade.watchMovie();
System.out.println("\n\n");
 		facade.endMovie();
	}
}

class Amplifier
{
	void on ()
	{
System.out.println("Amplifier is Turned On");		
	}
	
	void setDvd ()
	{
System.out.println("Amplifier is set to DVD Mode");		
	}
	
	void setSurroundSound ()
	{
System.out.println("Amplifier is set to SurroundSound mode");
	}
	
	void setVolume (int volume)
	{
System.out.println("Amplifier Volume is set to " + volume);		
	}
	
	void off ()
	{
System.out.println("Amplifier is Turned Off");		
	}
}

class PopcornPopper
{
	void on ()
	{
System.out.println("PopcornPopper is Turned On");
	}
	
	void pop ()
	{
System.out.println("PopcornPopper is Popping the corn seeds");
	}
	
	void off ()
	{
System.out.println("PopcornPopper is Turned Off");
	}
}

class TheatreLight
{
	void on ()
	{
System.out.println("ThreatreLight is Turned On");
	}
	
	void dim (int percentage)
	{
System.out.println("ThreatreLight is dimmed to " + percentage + " %");
	}
	
	void off ()
	{
System.out.println("ThreatreLight is Turned Off");
	}	
}

class Projector
{
	void on ()
	{
System.out.println("Projector is Turned On");
	}
	
	void wideScreenMode ()
	{
System.out.println("Projector is set to Wide Screen Mode");
	}
	
	void off ()
	{
System.out.println("Projector is Turned Off");
	}
}

class DVDPlayer
{
	void on ()
	{
System.out.println("DVDPlayer is Turned On");
	}
	
	void play (String movieName)
	{
System.out.println("DVDPlayer is playing the movie " + movieName);
	}
	
	void stop ()
	{
System.out.println("DVDPlayer is stopped");
	}
	
	void eject ()
	{
System.out.println("DVDPlayer is ejected");
	}
	
	void off ()
	{
System.out.println("DVDPlayer is Turned Off");
	}
}

/*
 * Facade doesnt Encapsulate the subsystem classes. They just provide a simplified interface for ease of use.
 * 
 * Facade requires composing with subsystem classes and delegating the request to these components to perform the task.
 */
class HomeThreaterFacade
{
	private Amplifier amplifier = new Amplifier ();
	private PopcornPopper popcornPopper = new PopcornPopper ();
	private TheatreLight theatreLight = new TheatreLight ();
	private Projector projector = new Projector ();
	private DVDPlayer dvdplayer = new DVDPlayer ();
	
	/*
	 * Responsibilities are in turn delegated to the underlying subsystem components.
	 */
	void watchMovie ()
	{
System.out.println("YOUR MOVIE STARTS NOW ..........");
		popcornPopper.on();
		popcornPopper.pop();
		
		theatreLight.on();
		theatreLight.dim(10);
		
		projector.on();
		projector.wideScreenMode();
		
		amplifier.on();
		amplifier.setDvd();
		amplifier.setSurroundSound();
		amplifier.setVolume(5);
		
		dvdplayer.on();
		dvdplayer.play("Sarkar");
	}
	
	void endMovie ()
	{
		popcornPopper.off();
		
		theatreLight.off();
		
		projector.off();
		
		amplifier.off();
		
		dvdplayer.stop();
		dvdplayer.eject();
		dvdplayer.off();		
		
System.out.println("YOUR MOVIE ENDED BYE ..........");
	}

}