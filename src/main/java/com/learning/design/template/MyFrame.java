package com.learning.design.template;

import java.awt.Graphics;

import javax.swing.JFrame;

/*
 * JFrame contains a method "update" which controls the algorithm for updating the screen.
 */
public class MyFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public MyFrame (String title)
	{
		super (title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setVisible(true);
	}
	
	/*
	 * JFrame's update algorithm calls paint which is by default a hook and does nothing.
	 */
	public void paint (Graphics graphics)
	{
		super.paint(graphics);
		graphics.drawString("This is a very good example of Template Method Pattern", 25, 100);
	}
	
	public static void main(String[] args)
	{
		new MyFrame ("Template Method Pattern");
	}
}
