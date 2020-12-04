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

		//Création des pièces du vaisseau:
		Room room21 = new Room(this, 21, "Room 21: This room is filled with unknown objects. " +
				"There's a table in the middle on which you were laying.");

		Room room22 = new Room(this, 22, "Room 22: Eerie lights are flowing out of the walls " +
				"pulsating slowly, quietly.");

		Room room23 = new Room(this, 23, "Room 23: The door closed shut behind you! You try opening it... " +
				"But it is hopeless, you are trapped in this dark room. " +
				"In the obscurity, you see the light of a computer screen left on the table.");

		Room room17 = new Room(this, 17, "Room 17: A low flickering out is emitted by a strange bulb. " +
				"There doesn't seem to be anything interesting here.");

		Room room13 = new Room(this, 13, "Room 13: This looks like a corridor between two parts of the ship. " +
				"You can see the vast emptiness of space through the windows.");

		//Enrichissement de la pièce 21:
		LockedDoor door1To22 = new LockedDoor("doorA", PassType.A);
		room21.addDoor(door1To22, room22);

		HealthStation hs = new HealthStation("HealthStation1", "This is a healthstation. I can heal myself here as much as I want but I can't bring this with me.");
		room21.getInventory().addItem(hs);

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
		Door door1To21 = new Door("doorA");
		room22.addDoor(door1To21, room21);
		Door door2To23 = new Door("doorB");
		room22.addDoor(door2To23, room23);
		Door door3To17 = new Door("doorC");
		room22.addDoor(door3To17, room17);

		//Enrichissement de la pièce 23:
		Door door2To22 = new Door("doorB");
		room23.addDoor(door2To22, room22);

		//Enrichissement de la pièce 17:
		Door door3To22 = new Door("doorC");
		room17.addDoor(door3To22, room22);
		Door door4To13 = new Door("doorD");
		room17.addDoor(door4To13, room13);

		//Enrichissement de la pièce 13:
		Door door4To17 = new Door("doorD");
		room13.addDoor(door4To17, room17);

		//Ajout des pièces au vaisseau:
		rooms.put(21, room21);
		rooms.put(22, room22);
		rooms.put(23, room23);
		rooms.put(17, room17);
		rooms.put(13, room13);
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