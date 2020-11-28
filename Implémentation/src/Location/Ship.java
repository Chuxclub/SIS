package Location;

import Characters.Actor;
import Characters.NPC;
import Characters.Player;
import Doors.Door;

import java.util.*;

public class Ship {

	List<Room> rooms = new ArrayList<>();
	private int NB_ROOMS = 2;

	public Ship(Player p)
	{
		//Création des acteurs:
		Set<Actor> actors = new HashSet<Actor>();
		NPC Kilen = new NPC(false, true, 100, 0, 21);
		actors.add(Kilen);
		actors.add(p);

		//Création de la pièce 21:
		HashMap<Door, Room> doors = new HashMap<Door, Room>();
		Room room21 = new Room(21, doors, actors, "Vous êtes dans une pièce étrange remplie de fioles et d'objets inconnus");
		doors.put(new Door(), null);

		rooms.add(room21);

		//Création de la pièce 22:
	}

	public Room searchRoom(int num)
	{

	}

	public void printShip()
	{

	}
}