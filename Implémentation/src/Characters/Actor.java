package Characters;

import java.util.*;
import Items.*;
import Location.*;

public abstract class Actor implements Attackable {

	private List<Item> inventory;
	private Room room;
	private int hp;
	private final int DEFAULT_ATTACK = 25;
	private final int DEFAULT_HP = 100;

	public Actor(Room room, List<Item> inventory)
	{
		this.room = room;
		this.inventory = inventory;
		this.hp = DEFAULT_HP;
	}

	public Actor(Room room, List<Item> inventory, int hp)
	{
		this.room = room;
		this.inventory = inventory;
		this.hp = hp;
	}

	public Room getRoom()
	{
		return this.room;
	}
}