package com.learning.design.iteratorandcomposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CompositePattern
{
	public static void main(String[] args)
	{
		MenuComponent panHousecakeMenu = new CompositeMenu ("Brekfast Menu", "Breakfast Menu Items");
		MenuComponent dinerMenu = new CompositeMenu ("Lunch Menu", "Lunch Menu Items");
		MenuComponent cafeMenu = new CompositeMenu ("Snacks Menu", "Evening snacks menu items");
		MenuComponent dessertMenu = new CompositeMenu ("Dessert Menu", "Sweets and Icecream menu items");
		
		MenuComponent allMenu = new CompositeMenu ("All Menu", "All Menus combined");
		
		allMenu.addItem(panHousecakeMenu);
		allMenu.addItem (dinerMenu);
		allMenu.addItem (cafeMenu);
		
		panHousecakeMenu.addItem (new CompositeMenuItem ("Dosa", "Masala Dosa with aloo curry", true, 40.56));
		panHousecakeMenu.addItem (new CompositeMenuItem ("Chouchoubath", "Combination of sweet and khara bath", true, 25.00));
		panHousecakeMenu.addItem (new CompositeMenuItem ("Idli", "Plain Rice Idli with chutney and sambar", true, 20.00));
		panHousecakeMenu.addItem (new CompositeMenuItem ("Poori", "Poori, aloo curry and chutney", true, 50.54));
		
		dinerMenu.addItem (new CompositeMenuItem ("Bisibelebath", "Rice cooked with lentils and vegetables", true, 80));
		dinerMenu.addItem (new CompositeMenuItem ("Pulkha", "Indian bread made with wheat flour and a side curry", true, 10));
		dinerMenu.addItem (new CompositeMenuItem ("Naan", "Indian Bread with wheat and maida and a side curry", true, 50));
		
		dinerMenu.addItem(dessertMenu);
		
		cafeMenu.addItem (new CompositeMenuItem ("Coffee", "Indian Fliter Coffee", true, 5.50));
		cafeMenu.addItem (new CompositeMenuItem ("Tea", "Assamee Chai", true, 5.50));
		cafeMenu.addItem (new CompositeMenuItem ("Badam Milk", "Freshly powdered Badam seeds blended with milk and sugar", true, 15.50));

		dessertMenu.addItem (new CompositeMenuItem ("ButterScotch", "Ice Cream made with Butterscotch fruit", true, 55.50));
		dessertMenu.addItem (new CompositeMenuItem ("Vanilla", "Made from Vanilla plant", true, 75.50));
		dessertMenu.addItem (new CompositeMenuItem ("TriSundae", "Combination of 3 different scoops", true, 105.50));
		
//		CompositeWaitress waitress = new CompositeWaitress (allMenu);
//		waitress.printMenu();
		
		Iterator it = allMenu.createIterator();
		while (it.hasNext())
		{
			MenuComponent menuComponent = (MenuComponent)it.next();
			try
			{
				if (menuComponent.getIsVegetarian())
				{
					menuComponent.print();					
				}
			}
			catch (UnsupportedOperationException e) {}
		}
	}
}

/*
 * Composite Pattern we can apply the same operations over both composites and individual objects. In most cases we can ignore the difference between composites and individual objects.
 * By putting both menu and menuitem in the same structure we create a part-whole hierarchy, that can be treated as a whole.
 * 
 * Elements with children are called Nodes/Composites. Elements without children are called leaves / Individual Objects.
 * Components that contain other components are called Composites. Components that dont contain other components are called Leaf nodes.
 * 
 * 
 * Component interface defines a common interface for all the objects in the composition - both composites and leaf nodes and allows the client to treat both uniformly.
 * 
 * 
 * This pattern is to be used when we want to treat both composites and individual objects alike.
 * Treating them alike is being able to execute same operations on both.
 * The greatest strength of Composite pattern is that it simplifies the life of the client.
 * Clients dont have to worry about differentiating between composites and individual objects same operation can be executed on both of them.
 */
abstract class MenuComponent
{
	void addItem (MenuComponent menuComponent)
	{
		throw new UnsupportedOperationException ();		
	}
	
	void remove (MenuComponent menuComponent)
	{
		throw new UnsupportedOperationException ();		
	}
	
	MenuComponent getChild (int i)
	{
		throw new UnsupportedOperationException ();		
	}
	
	String getName ()
	{
		throw new UnsupportedOperationException ();		
	}
	
	String getDescription ()
	{
		throw new UnsupportedOperationException ();		
	}
	
	boolean getIsVegetarian ()
	{
		throw new UnsupportedOperationException ();		
	}
	
	double getPrice ()
	{
		throw new UnsupportedOperationException ();		
	}
	
	void print ()
	{
		throw new UnsupportedOperationException ();		
	}
	
	Iterator createIterator ()
	{
		throw new UnsupportedOperationException ();
	}
}

/*
 * Defines leaf - a leaf has no children.
 * Leaf defines the behaviour for the elements in the composition.
 */
class CompositeMenuItem extends MenuComponent
{
	String name;
	String description;
	boolean isVegetarian;
	double price;
	
	CompositeMenuItem (String name, String description, boolean isVegetarian, double price)
	{
		this.name = name;
		this.description = description;
		this.isVegetarian = isVegetarian;
		this.price = price;
	}
	
	String getName ()
	{
		return name;		
	}
	
	String getDescription ()
	{
		return description;		
	}
	
	boolean getIsVegetarian ()
	{
		return isVegetarian;		
	}
	
	double getPrice ()
	{
		return price;		
	}
	
	void print ()
	{
System.out.println("\n");		
System.out.println("\tName : " + name);
System.out.println("\tDescription : " + description);
System.out.println("\tIs it Vegetarian : " + isVegetarian);
System.out.println("\tPrice : $" + price);
	}
	
	Iterator createIterator ()
	{
		return new NullIterator ();
	}
}

/*
 * Composites role is to define the behaviour of the components having children and to store child components.
 */
class CompositeMenu extends MenuComponent
{
	String name;
	String description;
	List menuComponents = new ArrayList ();
	
	CompositeMenu (String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	String getName ()
	{
		return name;		
	}
	
	String getDescription ()
	{
		return description;		
	}
	
	void addItem (MenuComponent menuComponent)
	{
		menuComponents.add(menuComponent);		
	}
	
	void remove (MenuComponent menuComponent)
	{
		menuComponents.remove(menuComponent);		
	}
	
	MenuComponent getChild (int i)
	{
		return (MenuComponent) menuComponents.get(i);		
	}
	
	void print ()
	{
System.out.println("\nMenu - " + name);
System.out.println("Description - " + description);

		Iterator it = menuComponents.iterator();
		while (it.hasNext())
		{
			MenuComponent menuComponent = (MenuComponent)it.next();
			menuComponent.print();
		}
	}
	
	private List getAllChildren ()
	{
		return menuComponents;
	}
	
	Iterator createIterator ()
	{
		return new CompositeIterator (menuComponents.iterator());
	}
	
	private static class CompositeIterator implements Iterator
	{
		Iterator allElementsIterator;
		List allElements = new ArrayList ();
		
		public CompositeIterator(Iterator it)
		{
			buildIterator (it);
			allElementsIterator = allElements.iterator();
		}
		
		private void buildIterator (Iterator it)
		{
			while (it.hasNext())
			{
				MenuComponent menuComponent = (MenuComponent)it.next();
				allElements.add (menuComponent);
				if (menuComponent instanceof CompositeMenu)
				{
					CompositeMenu menu = (CompositeMenu)menuComponent;
					Iterator compositeIterator = menu.getAllChildren().iterator();
					buildIterator(compositeIterator);
				}
				
			}
			allElementsIterator = allElements.iterator();
		}
		
		public void remove ()
		{
			throw new UnsupportedOperationException ();
		}
		
		public Object next ()
		{
			return allElementsIterator.next();
		}
		
		public boolean hasNext ()
		{
			return allElementsIterator.hasNext();
		}
	}
}

/*
 * Client Uses the Component interface t manipulate the objects in the composition.
 */
class CompositeWaitress
{
	MenuComponent allMenus;
	
	CompositeWaitress (MenuComponent allMenus)
	{
		this.allMenus = allMenus;
	}
	
	void printMenu ()
	{
		allMenus.print();
	}
}

/*
class CompositeIterator implements Iterator
{
	private Stack stack = new Stack ();
	
	CompositeIterator (Iterator it)
	{
		stack.push(it);
	}
	
	public Object next ()
	{
//		if (hasNext())
//		{
			Iterator it = (Iterator)stack.peek();
			MenuComponent menuComponent = (MenuComponent) it.next();
			
			if (menuComponent instanceof CompositeMenu)
			{
System.out.println("1111111111111111111111 :: " + menuComponent.getName());
				stack.push(menuComponent.createIterator ());		
			}
			
			return menuComponent;
//		}
//		return null;
	}
	
	public boolean hasNext ()
	{
		if (stack.isEmpty())
		{
			return false;
		}
		
		Iterator it = (Iterator)stack.peek();
		if (!it.hasNext())
		{
			stack.pop();
			return hasNext ();
		}
		else
		{
			return true;
		}
	}
	
	public void remove ()
	{
		throw new UnsupportedOperationException ();
	}
}
*/

class NullIterator implements Iterator
{
	public void remove ()
	{
		throw new UnsupportedOperationException ();
	}
	
	public Object next ()
	{
		return null;
	}
	
	public boolean hasNext ()
	{
		return false;
	}
}
