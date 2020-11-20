package Location;

import java.util.*;
import Characters.*;
import Doors.*;

public class Room {

	Set<Actor> actors;
	HashMap<Door, Room> doors;
	private int Number;
	String description;

	public Room(int num, HashMap<Door, Room> doors, Set<Actor> actors, String description)
	{
		this.Number = num;
		this.doors = doors;
		this.actors = actors;
		this.description = description;
	}
}