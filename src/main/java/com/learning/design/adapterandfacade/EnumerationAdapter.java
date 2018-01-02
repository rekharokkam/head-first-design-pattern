package com.learning.design.adapterandfacade;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/*
 * Just kind of Adapter for Enumeration to be used by the new Java versions
 */
public class EnumerationAdapter implements Iterator
{
	private Enumeration enumeration;
	
	public EnumerationAdapter (Enumeration enumeration)
	{
		this.enumeration = enumeration;
	}
	
	public boolean hasNext()
	{
		return enumeration.hasMoreElements();
	}

	public Object next()
	{
		return enumeration.nextElement();
	}

	public void remove()
	{
		throw new UnsupportedOperationException ("This Operation is not supported by the Old Enumeration");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Vector<String> names = new Vector<String>(4);
		names.add("Arman");
		names.add("Brijesh");
		names.add("Carrey");
		
		Iterator adapter = new EnumerationAdapter (names.elements());
		
		while (adapter.hasNext())
		{
//			adapter.remove ();
System.out.println(adapter.next());			
		}
	}
}
