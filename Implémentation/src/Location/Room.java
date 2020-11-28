package Location;

import java.util.*;
import Characters.*;
import Doors.*;

public class Room {

	Set<Actor> actors;
	HashMap<Door, Room> doors;
	private int Number;
	String description;
	Ship ship;
	private int items;

	public Room(int num, HashMap<Door, Room> doors, Set<Actor> actors, String description)
	{
		this.Number = num;
		this.doors = doors;
		this.actors = actors;
		this.description = description;
	}

	public void linkRooms(int num, int direction)
	{

	}


	public boolean islLinked(int direction)
	{

	}

	public boolean hasDoor(int direction)
	{

	}
}