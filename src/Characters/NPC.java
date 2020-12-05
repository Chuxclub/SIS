package Characters;

import Items.Item;
import Items.UsableOn;
import Location.Room;
import Containers.*;

import java.io.Serializable;
import java.util.List;

public class NPC extends Actor implements Serializable
{
	private boolean isHostile;
	private boolean isAlly;
	private String speech;

	public NPC(String name, boolean isHostile, boolean isAlly, List<Item> items, Room r)
	{
		super(name, r);
		this.isHostile = isHostile;
		this.isAlly = isAlly;

		for(Item i : items)
		{
			this.getInventory().addItem(i);
		}
	}

	public void setSpeech(String s)
	{
		this.speech = s;
	}

	public void talk()
	{
		if(speech != null)
			System.out.println(this.speech);

		else
			System.out.println("This person has nothing to say to you...");
	}

	@Override
	public void isAttacked(Attacker a)
	{
		super.isAttacked(a);

		if(this.isAlly && !(this.isDead()))
			System.out.println("Ouch! Stop it! I only have " + this.getHp() + "hp left you brute!");
	}
}