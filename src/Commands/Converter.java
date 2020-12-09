package Commands;

import Characters.*;
import Items.*;
import Doors.*;

public class Converter {

	private final Player caller;

	public Converter(Player player)
	{
		this.caller = player;
	}

	public Attackable convertAttackable(String s) throws StringRequestUnmatched
	{
		Attackable a = this.caller.getRoom().getActor(s);

		if(a != null)
			return a;

		else
			throw new StringRequestUnmatched();
	}

	public Door convertDoor(String s) throws StringRequestUnmatched
	{
		Door d = this.caller.getRoom().getDoor(s);

		if(d != null)
			return d;

		else
			throw new StringRequestUnmatched();
	}

	public Item convertItem(String s) throws StringRequestUnmatched
	{
		Item item1 = this.caller.getInventory().getItem(s);
		Item item2 = this.caller.getRoom().getInventory().getItem(s);

		if(item1 != null)
			return item1;

		else if(item2 != null)
			return item2;

		else
			throw new StringRequestUnmatched();
	}

	public Lookable convertLookable(String s) throws StringRequestUnmatched
	{
		Lookable item = this.caller.getInventory().getItem(s);
		Lookable roomItem = this.caller.getRoom().getInventory().getItem(s);
		Lookable door = this.caller.getRoom().getDoor(s);
		Lookable actor = this.caller.getRoom().getActor(s);

		if(item != null)
			return item;

		else if(roomItem != null)
			return roomItem;

		else if(door != null)
			return door;

		else if(actor != null)
			return actor;

		else
			throw new StringRequestUnmatched();
	}

	public NPC convertNPC(String s) throws StringRequestUnmatched
	{
		Actor npc = this.caller.getRoom().getActor(s);

		if (npc instanceof NPC)
			return (NPC) npc;

		else
			throw new StringRequestUnmatched();
	}

	public Item convertPlayerItem(String s) throws StringRequestUnmatched
	{
		Item item1 = this.caller.getInventory().getItem(s);

		if(item1 != null)
			return item1;

		else
			throw new StringRequestUnmatched();
	}

	public TakableItem convertTakableItem(String s) throws StringRequestUnmatched
	{
		Item item1 = this.caller.getRoom().getInventory().getItem(s);

		if(item1 instanceof TakableItem)
				return (TakableItem) item1;

		else
			throw new StringRequestUnmatched();
	}



	public UsableOn convertUsableOn(String s) throws StringRequestUnmatched
	{
		UsableOn u1 = this.caller.getInventory().getItem(s);
		UsableOn u2 = this.caller.getRoom().getInventory().getItem(s);

		if(u1 != null)
			return u1;

		else if (u2 != null)
			return u2;

		else
			throw new StringRequestUnmatched();
	}

	public UsableBy convertUsableBy(String s) throws StringRequestUnmatched
	{
		UsableBy u1 = this.caller.getInventory().getItem(s);
		UsableBy u2 = this.caller.getRoom().getInventory().getItem(s);
		UsableBy u3 = this.caller.getRoom().getActor(s);
		UsableBy d = this.caller.getRoom().getDoor(s);

		//On renvoie le premier objet qui est non null.
		//L'unicité de l'objet est garantie par l'unicité des labels:
		if(u1 != null)
			return u1;

		else if(u2 != null)
			return u2;

		else if(u3 != null)
			return u3;

		else if(d != null)
			return d;

		else
			throw new StringRequestUnmatched();
	}

}