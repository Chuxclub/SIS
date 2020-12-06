package Characters;

import Commands.Lookable;
import Items.Item;
import Items.TakableItem;
import Location.Room;

import java.io.Serializable;
import java.util.List;

public class NPC extends Actor implements Serializable, Lookable
{
	private boolean isHostile;
	private boolean isAlly;
	private String speech;

	public NPC(String name, String description, boolean isHostile, boolean isAlly, List<TakableItem> items, Room r)
	{
		super(name, description, r);
		this.isHostile = isHostile;
		this.isAlly = isAlly;

		for(TakableItem i : items)
		{
			this.getInventory().addItem(i);
		}
	}

	public boolean getHostile() { return this.isAlly; }

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

	public void setHostile(boolean b) {this.isHostile = b; }
	public void setSpeech(String s)
	{
		this.speech = s;
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

	public void setAlly(boolean b) { this.isAlly = b; };
}