package com.learning.design.iteratorandcomposite;

import java.util.ArrayList;
import java.util.List;

public class WithoutIterator
{
	public static void main(String[] args)
	{
		PancakeHouseMenuWithoutIterator pancakeHouseMenu = new PancakeHouseMenuWithoutIterator ();
		DinerMenuWithoutIterator dinerMenu = new DinerMenuWithoutIterator ();
		
		WaitressWithoutIterator waitress = new WaitressWithoutIterator (pancakeHouseMenu, dinerMenu);
		waitress.printMenu();
	}
}

class MenuItem 
{
	String name;
	String description;
	boolean isVegetarian;
	double price;
	
	MenuItem (String name, String description, boolean isVegetarian, double price)
	{
		this.name = name;
		this.description = description;
		this.isVegetarian = isVegetarian;
		this.price = price;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public boolean getIsVegetarian()
	{
		return isVegetarian;
	}

	public void setIsVegetarian(boolean isVegetarian)
	{
		this.isVegetarian = isVegetarian;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String toString()
	{
		return "Menu Name : " + getName () + " , Description : " + getDescription () + " , IsVegetarian : " + getIsVegetarian() + " , Price : " + getPrice ();
	}
}

/*
 * With all breakfast menu items
 */
class PancakeHouseMenuWithoutIterator
{
	List menuItems = new ArrayList ();
	
	PancakeHouseMenuWithoutIterator ()
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
	
	List getMenuItems ()
	{
		return menuItems;
	}
}

/*
 * With dinner menu items
 */
class DinerMenuWithoutIterator
{
	private static final int MAX_DINER_ITEMS = 3;
	private MenuItem[] menuItems = new MenuItem [MAX_DINER_ITEMS];
	private int currentItemNumber = 0; 
	
	DinerMenuWithoutIterator ()
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
	
	MenuItem[] getMenuItems ()
	{
		return menuItems;
	}
}

/*
 * Waitress prints both the breakfast and dinner menu from PancakeHouseMenu and DinerMenu
 */
class WaitressWithoutIterator
{
	private PancakeHouseMenuWithoutIterator pancakeHouseMenu;
	private DinerMenuWithoutIterator dinerMenu;
	
	WaitressWithoutIterator (PancakeHouseMenuWithoutIterator pancakeHouseMenu, DinerMenuWithoutIterator dinerMenu)
	{
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	
	void printMenu ()
	{
System.out.println("Breakfast Menu");
System.out.println("---------------");

		for (int i = 0; i < pancakeHouseMenu.getMenuItems().size(); i ++)
		{
			MenuItem pancakeHouseMenuItem = (MenuItem) pancakeHouseMenu.getMenuItems().get(i);
System.out.println(pancakeHouseMenuItem);			
		}
		
System.out.println("\n\nDiner Menu");
System.out.println("---------------");

		for (int i = 0; i < dinerMenu.getMenuItems().length; i ++)
		{
			MenuItem pancakeHouseMenuItem = (MenuItem) dinerMenu.getMenuItems()[i];
System.out.println(pancakeHouseMenuItem);			
		}		
	}
}