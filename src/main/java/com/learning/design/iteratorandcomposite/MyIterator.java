package com.learning.design.iteratorandcomposite;

import java.util.ArrayList;
import java.util.List;

public class MyIterator
{
	public static void main(String[] args)
	{
		PancakeHouseMenuWithMyIterator pancakeHouseMenu = new PancakeHouseMenuWithMyIterator ();
		DinerMenuWithMyIterator dinerMenu = new DinerMenuWithMyIterator ();
		
		WaitressWithMyIterator waitress = new WaitressWithMyIterator (pancakeHouseMenu, dinerMenu);
		waitress.printMenu();
	}
}

/*
 * Iterator Pattern relies on the interface Iterator
 * 
 * Iterator Pattern allows traversal of elements of an aggregate without exposing the underlying implementhation.
 * It also places the task of traversal on the iterator and not on the aggregate there by simplifying the aggregate interface. 
 */
interface Iterator
{
	Object next ();
	boolean hasNext ();
}

class PancakeHouseMenuWithMyIterator
{
	List menuItems = new ArrayList ();
	
	PancakeHouseMenuWithMyIterator ()
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
	
	Iterator createIterator ()
	{
		return new PancakeHouseMenuIterator (menuItems);
	}
	
	private static class PancakeHouseMenuIterator implements Iterator
	{
		private List items;
		private int currentCursorPosition = 0;
		
		PancakeHouseMenuIterator (List items)
		{
			this.items = items;
		}
		
		public boolean hasNext ()
		{
			if (currentCursorPosition >= items.size())
			{
				return false;
			}
			else if (items.get(currentCursorPosition) == null)
			{
				return false;
			}
			return true;			
		}
		
		public Object next ()
		{
			return items.get(currentCursorPosition ++);
		}
	}
}

/*
 * Each Concrete Aggregate is responsible for instantiating a concrete iterator that can traverse through the aggregate without exposing the underlying implementhation
 */
class DinerMenuWithMyIterator
{
	private static final int MAX_DINER_ITEMS = 3;
	private MenuItem[] menuItems = new MenuItem [MAX_DINER_ITEMS];
	private int currentItemNumber = 0; 
	
	DinerMenuWithMyIterator ()
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
	 * We should not expose the underlying implementhation to the client. So this method is replaced with createIterator method.
	MenuItem[] getMenuItems ()
	{
		return menuItems;
	}
	*/
	
	Iterator createIterator ()
	{
		return new DinerMenuMyIterator (menuItems);
	}
	
	private static class DinerMenuMyIterator implements Iterator
	{
		private MenuItem[] menuItems ;
		private int currentCursorPosition = 0;
		
		DinerMenuMyIterator (MenuItem[] menuItems)
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
	}
}
class WaitressWithMyIterator
{
	private PancakeHouseMenuWithMyIterator pancakeHouseMenu;	
	private DinerMenuWithMyIterator dinerMenu;
	
	WaitressWithMyIterator (PancakeHouseMenuWithMyIterator pancakeHouseMenu, DinerMenuWithMyIterator dinerMenu)
	{
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	
	void printMenu ()
	{
System.out.println("Breakfast Menu");
System.out.println("---------------");

		Iterator pancakeHouseMenuIterator = pancakeHouseMenu.createIterator();
		printMenuItem (pancakeHouseMenuIterator);

System.out.println("\n\nDinner Menu");
System.out.println("---------------");
		
		Iterator dinerMenuIterator = dinerMenu.createIterator();
		printMenuItem (dinerMenuIterator);
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