package Location;

import Characters.Actor;
import Characters.NPC;
import Characters.Player;
import Doors.Door;
import Doors.LockedDoor;
import Items.HealthStation;
import Items.Item;
import Items.Pass;
import Items.PassType;

import java.util.*;

public class Ship {

	private HashMap<Integer, Room> rooms;
	private static final int NB_ROOMS = 2;
	private Player player;
	private HashMap<String, NPC> npcs = new HashMap<>();

	public Ship()
	{
		rooms = new HashMap<>();

		//Création de la pièce 21 et 22:
		Room room21 = new Room(this, 21, "Room 21: This room is filled with unknown objects. " +
				"There's a table in the middle on which you were laying.");

		Room room22 = new Room(this, 22, "Room 22: Eerie lights are flowing out of the walls " +
				"pulsating slowly, quietly.");


		//Enrichissement de la pièce 21:
		LockedDoor door1To22 = new LockedDoor("doorA", PassType.A);
		room21.addDoor(door1To22, room22);

		HealthStation hs = new HealthStation("HealthStation1", "This is a healthstation. I can heal myself here as much as I want but I can't bring this with me.");
		Pass statue = new Pass("statue", "It looks like a statue... Perhaps I could take it with me to prove that this wasn't a dream?", PassType.B);
		room21.getInventory().addItem(hs);
		room21.getInventory().addItem(statue);

		//Construction de Kilen:
		List<Item> l = new ArrayList<Item>();
		Pass p = new Pass("passA", "It looks like a pass... There's some kind of letter looking like an A written on it.", PassType.A);
		l.add(p);
		NPC Kilen = new NPC("Kilen", false, true, l, room21);
		Kilen.setSpeech("Hi human! I'm Kilen. You are in danger, here's a pass to escape. Good luck! And please, I'm begging you... Don't kill my friends!");
		room21.addActor(Kilen);
		this.npcs.put(Kilen.getName(), Kilen);

		//Construction du joueur:
		this.player = new Player(room21);
		room21.addActor(player);


		//Enrichissement de la pièce 22:
		Door door1To21 = new Door("door1");
		room22.addDoor(door1To21, room21);


		//Ajout des pièces au vaisseau:
		rooms.put(21, room21);
		rooms.put(22, room22);
	}

	public NPC getNPC(String s)
	{
		return this.npcs.get(s);
	}

	public Player getPlayer()
	{
		return this.player;
	}

	public Room getRoom(int id)
	{
		return this.rooms.get(id);
	}


}