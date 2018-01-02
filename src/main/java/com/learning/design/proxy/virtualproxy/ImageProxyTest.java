package com.learning.design.proxy.virtualproxy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ImageProxyTest
{
	private ImageComponent imageComponent;
	private JFrame frame = new JFrame ("CD Cover VIewer");
	private JMenuBar menuBar;
	private JMenu menu;
	Map<String, String> cds = new HashMap <String, String> ();
	
	public ImageProxyTest ()
		throws Exception
	{
		cds.put("Ambient : Music for Airport", "http://images.amazon.com/images/P/B000003S2K.01.LZZZZZZZ.jpg");
		cds.put("Buddha Bar", "http://images.amazon.com/images/P/B00009XBYK.01.LZZZZZZZ.jpg ");
		cds.put ("Karma", "http://images.amazon.com/images/P/B000005DCB.01.LZZZZZZZ.gif");
		
		URL initialURL = new URL ((String)cds.get("Ambient : Music for Airport"));
		menuBar = new JMenuBar ();
		menu = new JMenu ("My Favourite CDs");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		Iterator<String> it = cds.keySet().iterator();
		while (it.hasNext())
		{
			JMenuItem menuItem = new JMenuItem (it.next());
			menu.add(menuItem);
			menuItem.addActionListener(new ActionListener ()
				{
					public void actionPerformed (ActionEvent actionEvent)
					{
						imageComponent.setIcon(new ImageProxy (getCDURL (actionEvent.getActionCommand())));
						frame.repaint();
					}
				}
			);
		}
		
		Icon icon = new ImageProxy (initialURL);
		imageComponent = new ImageComponent (icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			ImageProxyTest imageProxyTest = new ImageProxyTest ();
		}
		catch (Exception exception)
		{
exception.printStackTrace(System.err);			
		}
	}
	
	private URL getCDURL (String name)
	{
		try
		{
			return new URL (cds.get(name));
		}
		catch (MalformedURLException malformedURLException)
		{
malformedURLException.printStackTrace(System.err);			
		}
		return null;
	}

}
