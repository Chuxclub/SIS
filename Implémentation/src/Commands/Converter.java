package Commands;

import Characters.*;
import Items.*;
import Doors.*;

import java.util.concurrent.locks.Lock;

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
		return this.caller.getRoom().getDoor(s);
	}

	public UsableOn convertUsableOn(String s)
	{
		UsableOn u1 = this.caller.getInventory().getItem(s);
		UsableOn u2 = this.caller.getRoom().getInventory().getItem(s);

		if(u1 != null)
			return u1;

		else
			return u2;
	}

	public UsableBy convertUsableBy(String s)
	{
		UsableBy u1 = this.caller.getInventory().getItem(s);
		UsableBy u2 = this.caller.getRoom().getInventory().getItem(s);
		UsableBy u3 = this.caller.getRoom().getActor(s);

		Door d = this.caller.getRoom().getDoor(s);
		UsableBy ld = null;
		if(d instanceof LockedDoor)
			ld = (UsableBy) d;

		//On renvoie le premier objet qui est non null.
		//L'unicité de l'objet est garantie par l'unicité des labels:
		if(u1 != null)
			return u1;

		else if(u2 != null)
			return u2;

		else if(u3 != null)
			return u3;

		else
			return ld;
	}

}