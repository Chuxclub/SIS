package Location;

import Containers.*;
import java.util.*;
import Doors.*;
import Characters.*;

public class Room {

	Ship ship;
	Inventory inventory;
	Collection<Door> doors;
	Collection<Actor> actors;
	private int id;
	private String description;
	private int nbDoors;


	public Room(int integer, int string)
	{
	}

	public void addActor(int Actor)
	{
	}

	public void addDoor()
	{
	}

	public void describe()
	{
	}

	public Actor getActor(int string)
	{
	}

	public Door getDoor(int string)
	{
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void removeActor()
	{
	}

	public void scanRoom()
	{
	}

	public void useDoor(int Door)
	{
	}
}