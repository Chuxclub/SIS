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

	/**
	 * 
	 * @param num
	 * @param doors
	 */
	public Room(int num, int[] doors) {
		// TODO - implement Room.Room
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param num
	 * @param direction
	 */
	public void linkRooms(int num, int direction) {
		// TODO - implement Room.linkRooms
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param direction
	 */
	public bool islLinked(int direction) {
		// TODO - implement Room.islLinked
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param direction
	 */
	public bool hasDoor(int direction) {
		// TODO - implement Room.hasDoor
		throw new UnsupportedOperationException();
	}
}