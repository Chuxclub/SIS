package Characters;

import Items.Item;
import Location.Room;
import Containers.*;

import java.util.List;

public class NPC extends Actor {

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
		System.out.println(this.speech);
	}

	@Override
	public void isAttacked() {

	}

	@Override
	public void attack(Attackable a) {

	}
}