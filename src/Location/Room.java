package Location;

import java.io.Serializable;
import java.util.*;

import Containers.Inventory;
import Doors.*;
import Characters.*;

public class Room implements Serializable {

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
		this.scanRoom();
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
			if(d.getTag().equals(s))
				res = d;
		}

		return res;
	}

	public Door getDoor(Room r) throws NullPointerException
	{
		Door res = null;

		for(Map.Entry<Door, Room> e : this.doors.entrySet())
		{
			if(e.getValue().equals(r)) {
				res = e.getKey();
				break;
			}
		}

		if(res == null)
			throw new NullPointerException();

		else
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
		this.actors.remove(name);
	}

	public void scanRoom()
	{
		//Printing items:
		System.out.println("\n\tObjects in the room:");
		this.getInventory().showItems();

		//Printing doors:
		System.out.println("\n\tDoors in the room:");
		Set<Door> doorSet = this.doors.keySet();
		Door res = null;
		for(Door d : doorSet)
			System.out.println("\t- " + d.getTag());

		//Printing actors:
		System.out.println("\n\tBeings in the room:");
		for(Actor a : this.actors.values())
		{
			if(a.getName().equals("me"))
				System.out.println("\t- You are in the Room");

			else if(!(a.isDead()))
				System.out.println("\t- " + a.getName() + " is in the Room");

			else
				System.out.println("\t- " + a.getName() + "'s body is in the Room");
		}
	}

	public void useDoor(Actor a, Door d)
	{
		if(d.isOpen())
			a.changeRoom(this.doors.get(d));

		else
			System.out.println("You can't use this door.");
	}
}