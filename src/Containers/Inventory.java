package Containers;

import java.util.*;
import Items.*;

public class Inventory {

	HashMap<String, Item> items;
	private int size;
	private static int MIN_SIZE = 0;

	public Inventory()
	{
		this.items = new HashMap<>();
		this.size = MIN_SIZE;
	}

	public void addItem(Item item)
	{
		this.items.put(item.getTag(), item);
		this.size++;
	}

	public Item getItem(String s)
	{
			return this.items.get(s);
	}

	public int getSize()
	{
		return this.size;
	}

	public void showItems()
	{
		for (Item i : this.items.values())
		{
			System.out.println("\t- " + i.getTag() + " : " + i.getDescription());
		}
	}

	public void describeItem(Item item)
	{
		item.describe();
	}

	public boolean isEmpty()
	{
		return this.items.isEmpty();
	}

	public void removeItem(String tag)
	{
		this.items.remove(tag);
	}

}