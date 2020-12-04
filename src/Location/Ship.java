package Location;

import Characters.Actor;
import Characters.NPC;
import Characters.Player;
import Doors.Door;
import Doors.LockedDoor;
import Items.*;

import java.util.*;

public class Ship {

	private HashMap<Integer, Room> rooms;
	private Player player;
	private HashMap<String, NPC> npcs = new HashMap<>();

	public Ship()
	{
		rooms = new HashMap<>();

		// =================================================================================================== //
		// ================================= Création des pièces du vaisseau ================================= //
		// =================================================================================================== //

		Room room13 = new Room(this, 13, "Room 13: This looks like a corridor between two parts of the ship. " +
				"You can see the vast emptiness of space through the windows.");

		Room room17 = new Room(this, 17, "Room 17: A low flickering out is emitted by a strange bulb. " +
				"There doesn't seem to be anything interesting here.");

		Room room18 = new Room(this, 18, "Room 18: There's nobody here and yet you feel like " +
				"there's a lot of activity near this room.");

		Room room21 = new Room(this, 21, "Room 21: This room is filled with unknown objects. " +
				"There's a table in the middle on which you were laying.");

		Room room22 = new Room(this, 22, "Room 22: Eerie lights are flowing out of the walls " +
				"pulsating slowly, quietly.");

		Room room23 = new Room(this, 23, "Room 23: The door closed shut behind you! You try opening it... " +
				"But it is hopeless, you are trapped in this dark room. " +
				"In the obscurity, you see the light of a computer screen left on the table.");

		Room room27 = new Room(this, 27, "Room 27: Boxes and vials are jamming this room. It looks as if you were getting" +
				" closer to a warehouse...");

		Room room28 = new Room(this, 28, "Room 28: There's nothing of interest here.");

		Room room30 = new Room(this, 30, "Room 30: You are in warehouse. You can see what looks like food, drinks, tools and various" +
				" materials whether they are meant for experiments or the everyday life.");


		// =================================================================================================== //
		// ============================== Enrichissement des pièces du vaisseau ============================== //
		// =================================================================================================== //

		//Enrichissement de la pièce 13:
		Door door13To17 = new Door("door17");
		room13.addDoor(door13To17, room17);

		//Enrichissement de la pièce 17:
		Door door17To22 = new Door("door22");
		room17.addDoor(door17To22, room22);
		Door door17To13 = new Door("door13");
		room17.addDoor(door17To13, room13);
		Door door17To18 = new Door("door18");
		room17.addDoor(door17To18, room18);

		//Enrichissement de la pièce 18:
		Door door18To17 = new Door("door17");
		room18.addDoor(door18To17, room17);
		File clueToPassA = new File("wanted_poster", "It's a notice. Somebody posted it in a room hoping " +
				"that somebody would bring back his pass to the residential quarters", "Hi! It's Ghainkix. I lost my pass" +
				" yesterday after a hard day of work. I think it might be in the warehouse but I'm not sure. If somebody ever happen " +
				"to go there... Could you please bring it back to me? My wife's already complaining she has to open the door for me everytime" +
				" I get in or out... Thanks so much!");
		room18.getInventory().addItem(clueToPassA);

		//Enrichissement de la pièce 21:
		LockedDoor door21To22 = new LockedDoor("doorT", PassType.T);
		room21.addDoor(door21To22, room22);

		HealthStation hs = new HealthStation("HealthStation1", "This is a healthstation. I can heal myself here as much as I want but I can't bring this with me.");
		room21.getInventory().addItem(hs);
		Artefact statue = new Artefact("statue", "This is a statue showing an alien like Kilen... " +
				"Maybe to prove that this wasn't all a dream I should take it with me.");
		room21.getInventory().addItem(statue);

		//Construction de Kilen:
		List<Item> l = new ArrayList<Item>();
		Pass p = new Pass("passT", "It looks like a pass... There's some kind of letter looking like a T written on it.", PassType.T);
		l.add(p);
		NPC Kilen = new NPC("Kilen", false, true, l, room21);
		Kilen.setSpeech("Hi human! I'm Kilen. You are in danger, here's a pass to escape. Good luck! And please, I'm begging you... Don't kill my friends!");
		room21.addActor(Kilen);
		this.npcs.put(Kilen.getName(), Kilen);

		//Construction du joueur:
		this.player = new Player(room21, this);
		room21.addActor(player);


		//Enrichissement de la pièce 22:
		Door door22To21 = new Door("doorT");
		room22.addDoor(door22To21, room21);
		Door door22To23 = new Door("door23");
		room22.addDoor(door22To23, room23);
		Door door22To17 = new Door("door17");
		room22.addDoor(door22To17, room17);
		Door door22To27 = new Door("door27");
		room22.addDoor(door22To27, room27);

		//Enrichissement de la pièce 23:
		LockedDoor door23To22 = new LockedDoor("door22", PassType.C);
		room23.addDoor(door23To22, room22);

		Computer comp = new Computer("The lab computer", "computer", door23To22);

		File file1 = new File("doctorLog811.txt", "Evidence of lab experiments on humans.",
				"We have been abducting humans for the past few years now.\nWe have been conducting all sorts of " +
						"experiments on these primates. We were tasked to understand how their immune system works, but" +
						" the Commander refuses to tell us more.\nI hope he's not planning anything to bad, it would be a" +
						" shame to lose such efficient guinea pigs.");
		File file2 = new File("importantMessage.txt", "A message adressed to all Scientists by Commander Gelgax",
				"Attention to all scientists. A new human has arrived yesterday. \nReady him as soon as possible for the next few lab tests." +
						"Insubordination will not be tolerated.");
		File file3 = new File("recipe.txt", "A recipe from the lab computer",
				"Step 1\n" +
				"\tPreheat oven to 350 degrees F (180 degrees C).\n" +
				"Step 2\n" +
				"\tCream butter and sugar until fluffy. Stir in vanilla; add flour and mix well.\n" +
				"Step 3\n" +
				"\tPut through cookie press and form cookies onto baking sheets. Bake for 10 - 12 minutes.\n");

		comp.addFile(file1);
		comp.addFile(file2);
		comp.addFile(file3);

		room23.getInventory().addItem(comp);

		//Enrichissement de la pièce 27:
		Door door27To22 = new Door("door22");
		room27.addDoor(door27To22, room22);
		Door door27To28 = new Door("door28");
		room27.addDoor(door27To28, room28);
		Door door27To30 = new Door("door30");
		room27.addDoor(door27To30, room30);

		//Enrichissement de la pièce 28:
		Door door28To27 = new Door("door27");
		room28.addDoor(door28To27, room27);

		//Enrichissement de la pièce 30:
		Door door30To27 = new Door("door27");
		room30.addDoor(door30To27, room27);
		Pass passA = new Pass("passA", "This is a pass... A letter 'A' is written on it.", PassType.A);
		room30.getInventory().addItem(passA);

		// =================================================================================================== //
		// =================================== Ajout des pièces du vaisseau ================================== //
		// =================================================================================================== //
		rooms.put(13, room13);
		rooms.put(17, room17);
		rooms.put(21, room21);
		rooms.put(22, room22);
		rooms.put(23, room23);
		rooms.put(27, room23);
		rooms.put(28, room23);
		rooms.put(30, room23);
	}

	public Ship(Ship ship){
		this.rooms = ship.rooms;
		this.player = ship.player;
		this.npcs = ship.npcs;
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