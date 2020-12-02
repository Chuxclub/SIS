package Characters;

import Location.*;
import Containers.*;

public abstract class Actor implements Attackable, Attacker {

	Room room;
	Inventory inventory;
	private int DEFAULT_ATTACKPOWER;
	private int DEFAULT_HP;
	private int hp;
	private String name;
	private int attackPower;


	public Actor(int string)
	{
	}

	public void changeRoom()
	{
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void getRoom()
	{
	}

	public void showInventory()
	{
	}

}