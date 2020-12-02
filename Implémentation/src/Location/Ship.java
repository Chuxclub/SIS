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

	public Ship()
	{
		rooms = new HashMap<>();

		//Création de la pièce 21 et 22:
		Room room21 = new Room(this, 21, "Pièce 21: Cette pièce est remplie d'objets inconnus. " +
				"Au centre se trouve une sorte de table sur laquelle vous étiez allongé.");

		Room room22 = new Room(this, 22, "Pièce 22: Des lumières étranges émanent des murs, " +
				"non comme des clignotements mais plutôt comme de lentes pulsations.");


		//Enrichissement de la pièce 21:
		LockedDoor door1To22 = new LockedDoor("door1", PassType.A);
		room21.addDoor(door1To22, room22);

		HealthStation hs = new HealthStation("HealthStation1", "C'est une station de soin. Je peux me soigner ici autant que je veux");
		room21.getInventory().addItem(hs);

		List<Item> l = new ArrayList<Item>();
		Pass p = new Pass("pass1", "Ça ressemble à un pass... Il y a une lettre ressemblant à un A écrit dessus.", PassType.A);
		l.add(p);
		NPC Kilen = new NPC("Kilen", false, true, l, room21);
		Kilen.setSpeech("Bonjour humain! Tu es en danger, voici un pass pour t'échapper. Bonne chance! Et surtout: ne tue pas mes amis!");
		room21.addActor(Kilen);

		this.player = new Player(room21);
		room21.addActor(player);


		//Enrichissement de la pièce 22:
		Door door1To21 = new Door("door1");
		room21.addDoor(door1To21, room21);


		//Ajout des pièces au vaisseau:
		rooms.put(21, room21);
		rooms.put(22, room22);
	}

	public Room getRoom(int id)
	{
		return this.rooms.get(id);
	}

	public Player getPlayer()
	{
		return this.player;
	}
}