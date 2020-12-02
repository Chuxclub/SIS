package Characters;

import Location.*;
import Containers.*;

public abstract class Actor implements Attackable, Attacker {

	private Room room;
	private Inventory inventory;

	private int DEFAULT_ATTACKPOWER;
	private int DEFAULT_HP;
	private int hp;
	private String name;
	private int attackPower;


	public Actor(String name, Room r)
	{
		this.room = r;
		this.inventory = new Inventory();

		this.name = name;
		this.hp = DEFAULT_HP;
		this.attackPower = DEFAULT_ATTACKPOWER;
	}

	public void changeRoom(Room r)
	{
		this.room.removeActor(this.name);
		r.addActor(this);
		this.room = r;
		this.room.describe();
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

	public void showInventory()
	{
	}

}