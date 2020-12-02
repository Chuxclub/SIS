package Location;

import Containers.*;
import java.util.*;

import Containers.Inventory;
import Doors.*;
import Characters.*;

public class Room {

	private Ship ship;
	private Inventory inventory;
	private int id;
	private String description;

	private HashMap<Door, Room> doors;
	private int nbDoors;
	private static int MIN_NBDOORS = 0;

	private HashMap<String, Actor> actors;
	private int nbActors;
	private static int MIN_NBACTORS = 0;

	public Room(Ship ship, int id, String description)
	{
		this.ship = ship;
		this.inventory = new Inventory();
		this.id = id;
		this.description = description;

		this.doors = new HashMap<>();
		this.nbDoors = MIN_NBDOORS;

		this.actors = new HashMap<>();
		this.nbActors = MIN_NBACTORS;
	}

	public void addActor(Actor actor)
	{
		this.actors.put(actor.getName(), actor);
	}

	public void addDoor(Door d, Room r)
	{
		this.doors.put(d, r);
	}

	public void describe()
	{
		System.out.println(this.description);
	}

	public Actor getActor(String s)
	{
		return this.actors.get(s);
	}

	public Door getDoor(String s)
	{
		Set<Door> doorSet = this.doors.keySet();
		Door res = null;

		for(Door d : doorSet)
		{
			if(d.getTag() == s)
				res = d;
		}

		return res;
	}

	public Inventory getInventory()
	{
		return this.inventory;
	}

	public boolean hasActor(String name)
	{
		return this.actors.get(name) != null;
	}

	public void removeActor(String name)
	{
	}

	public void scanRoom()
	{
	}

	public void useDoor(int Door)
	{
	}
}