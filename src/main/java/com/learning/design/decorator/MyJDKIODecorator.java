package com.learning.design.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyJDKIODecorator
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int c;
		try
		{
			InputStream in = new LowerCaseConvertor (new BufferedInputStream(new FileInputStream ("C:/Rekha/Testing_Workspace/CoreJava/bin/decoratortest.txt")));
			while ((c = in.read()) >= 0)
			{
System.out.print((char)c);				
			}
		}
		catch (IOException ioexception)
		{
ioexception.printStackTrace(System.err);			
		}

	}

}

//Custom class which adds decorator to do conversion from uppercase to lower case
/*
 * FilterInputStream - is the abstract Decorator for all the InputStreams
 */

class LowerCaseConvertor extends FilterInputStream
{
	LowerCaseConvertor (InputStream inputStream)
	{
		super (inputStream);
	}
	
	/*
	 * Adding a new behaviour to the read methods of supertype FilterInputStream
	 * 
	 */
	public int read ()
		throws IOException
	{	
		int c = super.read();
		return ((c == -1) ? c: Character.toLowerCase((char)c)); 
	}
	
	public int read (byte[] b, int offset, int length)
		throws IOException
	{
		int result = super.read(b, offset, length);
		for (int i = offset; i < offset + result; i++)
		{
			b[i] = (byte)Character.toLowerCase((char)b[i]);
		}
		return result;
	}
}
