package Characters;

import Items.HealthStation;
import Items.Item;
import Items.UsableBy;
import Items.UsableOn;
import Location.*;
import Containers.*;

import java.io.Serializable;

public abstract class Actor implements Attackable, Attacker, UsableBy, Serializable
{
	private Room room;
	private Room previousRoom;
	private Inventory inventory;

	private int DEFAULT_ATTACKPOWER = 25;
	private int DEFAULT_HP = 100;
	private int DEFAULT_HP_MAX = 100;
	private int hp;
	private String name;
	private int attackPower;


	public Actor(String name, Room r)
	{
		this.room = r;
		this.previousRoom = r;
		this.inventory = new Inventory();

		this.name = name;
		this.hp = DEFAULT_HP;
		this.attackPower = DEFAULT_ATTACKPOWER;
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

	public Inventory getInventory()
	{
		return this.inventory;
	}

	public String getName()
	{
		return this.name;
	}

	public Room getRoom()
	{
		return this.room;
	}

	public Room getPreviousRoom()
	{
		return this.previousRoom;
	}

	public void give(String tag, Actor a)
	{
		Item item = this.inventory.getItem(tag);
		this.inventory.removeItem(tag);
		a.inventory.addItem(item);
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

	public void showInventory()
	{
		this.inventory.showItems();
	}
	public int getHp()
	{
		return this.hp;
	}


	public int getDEFAULT_HP_MAX()
	{
		return this.DEFAULT_HP_MAX;
	}

	@Override
	public void isUsedBy(UsableOn u)
	{
		if(u instanceof HealthStation)
		{
			HealthStation hs = (HealthStation) u;
			this.isHealed(this.DEFAULT_HP_MAX - this.hp);
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
}