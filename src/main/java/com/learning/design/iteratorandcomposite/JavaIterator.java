package com.learning.design.iteratorandcomposite;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class JavaIterator
{
	public static void main(String[] args)
	{
		Menu pancakeHouseMenu = new PancakeHouseMenuWithJavaIterator ();
		Menu dinerMenu = new DinerMenuWithJavaIterator ();
		Menu cafeMenu = new CafeMenuWithJavaIterator ();
		
		WaitressWithJavaIterator waitress = new WaitressWithJavaIterator (pancakeHouseMenu, dinerMenu, cafeMenu);
		waitress.printMenu();
	}
}

interface Menu
{
	Iterator createIterator ();
}

class PancakeHouseMenuWithJavaIterator implements Menu
{
	List menuItems = new ArrayList ();
	
	PancakeHouseMenuWithJavaIterator ()
	{
		addItem ("Dosa", "Masala Dosa with aloo curry", true, 40.56);
		addItem ("Chouchoubath", "Combination of sweet and khara bath", true, 25.00);
		addItem ("Idli", "Plain Rice Idli with chutney and sambar", true, 20.00);
		addItem ("Poori", "Poori, aloo curry and chutney", true, 50.54);
	}
	
	private void addItem (String name, String description, boolean isVegetarian, double price)
	{
		MenuItem menuItem = new MenuItem (name, description, isVegetarian, price);
		menuItems.add(menuItem);
	}
	
	/*
	List getMenuItems ()
	{
		return menuItems;
	}
	*/
	
	public Iterator createIterator ()
	{
		return menuItems.iterator();
	}
}

class DinerMenuWithJavaIterator implements Menu
{
	private static final int MAX_DINER_ITEMS = 3;
	private MenuItem[] menuItems = new MenuItem [MAX_DINER_ITEMS];
	private int currentItemNumber = 0; 
	
	DinerMenuWithJavaIterator ()
	{
		addItem ("Bisibelebath", "Rice cooked with lentils and vegetables", true, 80);
		addItem ("Pulkha", "Indian bread made with wheat flour and a side curry", true, 10);
		addItem ("Naan", "Indian Bread with wheat and maida and a side curry", true, 50);
	}
	
	private void addItem (String name, String description, boolean isVegetarian, double price)
	{
		if (currentItemNumber == MAX_DINER_ITEMS)
		{
System.err.println("Cant add any more items");			
		}
		else
		{
			MenuItem menuItem = new MenuItem (name, description, isVegetarian, price);
			menuItems[currentItemNumber ++] = menuItem;
		}
	}	
	
	/*
	MenuItem[] getMenuItems ()
	{
		return menuItems;
	}
	*/
	
	public Iterator createIterator ()
	{
		return new DinerMenuJavaIterator (menuItems);
	}
	
	private static class DinerMenuJavaIterator implements Iterator
	{
		private MenuItem[] menuItems ;
		private int currentCursorPosition = 0;
		
		DinerMenuJavaIterator (MenuItem[] menuItems)
		{
			this.menuItems = menuItems;
		}
		
		public boolean hasNext ()
		{
			if (currentCursorPosition >= menuItems.length)
			{
				return false;
			}
			else if (menuItems[currentCursorPosition] == null)
			{
				return false;
			}
			return true;				
		}
		
		public Object next ()
		{
			return menuItems[currentCursorPosition ++];
		}
		
		public void remove ()
		{
			if (currentCursorPosition <= 0 )
			{
				throw new IllegalStateException ("Cant remove an item until next is called at least once");
			}
			
			if (currentCursorPosition > menuItems.length)
			{
				throw new IllegalStateException ("Cant remove an item beyond the length of aggregate");
			}
			
			if (null != menuItems[currentCursorPosition -1])
			{
				for (int i = (currentCursorPosition-1); i < (menuItems.length - 1); i++)
				{
					menuItems[i] = menuItems[i + 1];
				}
				menuItems[menuItems.length - 1] = null;
				currentCursorPosition -= 1;
			}
		}
	}
}

class CafeMenuWithJavaIterator implements Menu
{
	Hashtable menuItems = new Hashtable ();
	
	CafeMenuWithJavaIterator ()
	{
		addItem ("Coffee", "Indian Fliter Coffee", true, 5.50);
		addItem ("Tea", "Assamee Chai", true, 5.50);
		addItem ("Badam Milk", "Freshly powdered Badam seeds blended with milk and sugar", true, 15.50);
	}
	
	private void addItem (String name, String description, boolean isVegetarian, double price)
	{
		MenuItem menuItem = new MenuItem (name, description, isVegetarian, price);
		menuItems.put(name, menuItem);
	}
	
	public Iterator createIterator ()
	{
		return menuItems.values().iterator();
	}
}

class WaitressWithJavaIterator
{
	private Menu pancakeHouseMenu;	
	private Menu dinerMenu;
	private Menu cafeMenu;
	
	WaitressWithJavaIterator (Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu)
	{
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
		this.cafeMenu = cafeMenu;
	}
	
	void printMenu ()
	{
System.out.println("Breakfast Menu");
System.out.println("---------------");

		Iterator pancakeHouseMenuIterator = pancakeHouseMenu.createIterator();
		printMenuItem (pancakeHouseMenuIterator);

System.out.println("\n\nLunch Menu");
System.out.println("---------------");
		
		Iterator dinerMenuIterator = dinerMenu.createIterator();
		printMenuItem (dinerMenuIterator);
		
System.out.println("\n\nSnacks Menu");
System.out.println("---------------");
 		
		Iterator cafeMenuIterator = cafeMenu.createIterator();
		printMenuItem(cafeMenuIterator);
	}
	
	private void printMenuItem (Iterator iterator)
	{
		while (iterator.hasNext())
		{
			MenuItem menuItem = (MenuItem) iterator.next();
System.out.println(menuItem);
		}
		
	}
}