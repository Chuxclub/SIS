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
	}

	public Door convertDoor(String s)
	{
		return caller.getRoom().getDoor(s);
	}

	public UsableOn convertUsableOn(String s)
	{
	}

	public UsableBy convertUsableBy(String s)
	{
	}

}