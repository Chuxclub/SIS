package Containers;

import java.io.Serializable;
import java.util.*;
import Items.*;

public class Inventory implements Serializable {

	HashMap<String, Item> items;

	public Inventory()
	{
		this.items = new HashMap<>();
	}

	public void addItem(Item item)
	{
		this.items.put(item.getTag(), item);
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
		return this.items.size();
	}

	public boolean isEmpty()
	{
		return this.items.isEmpty();
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