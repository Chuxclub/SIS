package Characters;

import Commands.Lookable;
import Items.*;
import Location.*;
import Containers.*;

import java.io.Serializable;

public abstract class Actor implements Attackable, Attacker, UsableBy, Serializable, Lookable
{
	private final String name;
	private final String description;
	private int hp;
	private int attackPower;
	private Room room;
	private Room previousRoom;
	private final Inventory inventory;

	private static final int DEFAULT_ATTACKPOWER = 25;
	private static final int DEFAULT_HP = 100;
	private static final int DEFAULT_HP_MAX = 100;

	public Actor(String name, String description, Room r)
	{
		this.name = name;
		this.description = description;
		this.hp = DEFAULT_HP;
		this.attackPower = DEFAULT_ATTACKPOWER;
		this.room = r;
		this.previousRoom = r;
		this.inventory = new Inventory();
	}

	@Override
	public void describe()
	{
		System.out.println(this.description);
	}

	public void changeRoom(Room r)
	{
		this.previousRoom = this.getRoom();
		this.room.removeActor(this.name);
		r.addActor(this);
		this.room = r;
		this.room.describe();
	}

	public int getAttackPower()
	{
		return this.attackPower;
	}

	public int getDEFAULT_HP_MAX()
	{
		return DEFAULT_HP_MAX;
	}

	public int getHp()
	{
		return this.hp;
	}

	public Inventory getInventory()
	{
		return this.inventory;
	}

	public String getName()
	{
		return this.name;
	}

	public Room getPreviousRoom()
	{
		return this.previousRoom;
	}

	public Room getRoom()
	{
		return this.room;
	}

	//On donne toujours une copie de l'objet:
	public void give(String tag, Actor a)
	{
		TakableItem item = this.inventory.getTakableItem(tag);

		if(item != null)
		{
			a.inventory.addItem(item.getCopy());
			a.receive(this, item.getTag());
		}

		else {
			System.out.println("Error :> You can't give this item or you misspelled its tag");
		}
	}

	@Override
	public void isAttacked(Attacker a)
	{
		if(a instanceof Actor)
		{
			Actor actor = (Actor) a;

			if(!(this.isDead()))
				this.hp -= actor.getAttackPower();
		}
	}

	public boolean isDead()
	{
		return this.hp <= 0;
	}

	public void isHealed(int healing_points)
	{
		if(this.isDead())
			System.out.println("A dead person can't be healed... You really have a few things to learn about life, don't you?");

		else
		{
			this.hp += healing_points;

			if(this.hp > this.getDEFAULT_HP_MAX())
				this.hp = this.getDEFAULT_HP_MAX();

			if(this instanceof Player)
				System.out.println("You have been healed!");

			else
				System.out.println(this.getName() + " has been healed!");
		}
	}

	@Override
	public void isUsedBy(UsableOn u)
	{
		if(u instanceof HealthStation)
			this.isHealed(DEFAULT_HP_MAX - this.hp);

		else
			System.out.println("This object has no effect here");
	}

	public abstract void receive(Actor a, String tag);
}