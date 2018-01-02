package com.learning.design.template;

import java.applet.Applet;
import java.awt.Graphics;

public class MyApplet extends Applet
{
	private static final long	serialVersionUID	= 1L;
	
	String message;
	
	public void init ()
	{
		message = "Inside the init method";
		repaint();
	}
	
	public void start ()
	{
		message = "now we are starting";
		repaint ();
	}
	
	public void stop ()
	{
		message = "now we are stopping";
		repaint();
	}
	
	public void destroy ()
	{
		
	}
	
	public void paint (Graphics graphics)
	{
		graphics.drawString(message, 5, 15);
	}
}
