package Containers;

import java.io.Serializable;
import java.util.*;
import Items.*;

public class Inventory implements Serializable {

	HashMap<String, Item> items;
	private int size;
	private static final int MIN_SIZE = 0;

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

	public TakableItem getTakableItem(String s)
	{
		Item item = this.items.get(s);

		if(item instanceof TakableItem)
			return (TakableItem) item;

		else
			return null;
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

	public void removeItem(String tag)
	{
		this.items.remove(tag);
	}
}