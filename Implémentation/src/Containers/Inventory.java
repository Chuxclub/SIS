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
	}

	public void getItem(int string)
	{
	}

	public void getSize()
	{
	}

	public void showItems()
	{
	}

	public void describeItem(int Item)
	{
	}

	public void isEmpty()
	{
	}

}