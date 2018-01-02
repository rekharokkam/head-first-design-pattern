package com.learning.design.proxy.virtualproxy;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * Virtual Proxy acts as a representative for an object tat may be expensive to create. 
 * VP often defers the creation of the object until it is needed.
 * VP also acts as a surrogate for the real object before and while it is being created.
 * After tat VP directly delegates the request to the Real Subject.
 */
public class ImageProxy implements Icon
{
	private ImageIcon imageIcon;
	private URL imageURL;
	private Thread retrievalThread;
	private boolean retrieving = false;
	
	public ImageProxy (URL imageURL)
	{
		this.imageURL = imageURL;
	}
	
	public int getIconHeight()
	{
		if (null == imageIcon)
		{
			return 600;
		}
		return imageIcon.getIconHeight();
	}

	public int getIconWidth()
	{
		if (null == imageIcon)
		{
			return 800;
		}
		return imageIcon.getIconWidth();
	}

	public void paintIcon(final Component c, Graphics g, int x, int y)
	{
		if (null != imageIcon)
		{
			imageIcon.paintIcon(c, g, x, y);
		}
		else
		{
			g.drawString("Still loading image please wait .............", x, y);
			if (!retrieving)
			{
				retrieving = true;
				retrievalThread = new Thread (new Runnable ()
					{
						public void run ()
						{
							try
							{
								imageIcon = new ImageIcon (imageURL, "CD Cover");
								c.repaint();
							}
							catch (Exception exception)
							{
exception.printStackTrace(System.err);								
							}
						}
					}
				);
				retrievalThread.start();
			}
		}
	}
}
