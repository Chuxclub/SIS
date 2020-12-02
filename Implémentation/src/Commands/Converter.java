package Commands;

import Characters.*;
import Items.*;
import Doors.*;

public class Converter {

	private Player caller;

	public Converter(Player player)
	{
		this.caller = player;
	}

	public Item convertItem(String s)
	{
		return this.caller.getInventory().getItem(s);
	}

	public Door convertDoor(String s)
	{
		return caller.getRoom().getDoor(s);
	}

	public UsableOn convertUsableOn(String s)
	{
		return null;
	}

	public UsableBy convertUsableBy(String s)
	{
		return null;
	}

}