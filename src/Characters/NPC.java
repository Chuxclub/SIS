package Characters;

import Items.Item;
import Location.Room;

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

	public String getSpeech()
	{
		return this.speech;
	}

	public void talk()
	{
		if (speech != null && !(this.isDead()) && !(this.isHostile))
			System.out.println(this.speech);

		else if(this.isDead())
			System.out.println("Great, now you are talking to a dead body... You're just getting better and better!");

		else
			System.out.println("This person has nothing to say to you...");
	}

	@Override
	public void isAttacked(Attacker a)
	{
		super.isAttacked(a);

		if(this.isDead())
			System.out.println(this.getName() + " is dead...");

		else
		{
			System.out.println(this.getName() + " gasps with pain, " + this.getName() + " only has " + this.getHp() + "hp left!");

			if (this.isAlly)
			{
				this.isAlly = false;
			}

			else
			{
				if (!(this.isHostile))
					this.isHostile = true;

				if(a instanceof Attackable) {
					this.attack((Attackable) a);
				}
			}
		}
	}

	public boolean getAlly() { return this.isAlly; }
	public boolean getHostile() { return this.isAlly; }
	public void setAlly(boolean b) {this.isAlly = b; }
	public void setHostile(boolean b) {this.isHostile = b; }
}