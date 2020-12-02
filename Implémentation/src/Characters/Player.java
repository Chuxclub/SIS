package Characters;

import Commands.Command;
import Doors.Door;
import Items.Item;
import Location.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Actor {

	private static String NAME = "player";
	private Room previous_room;

	public Player(Room r)
	{
		super(NAME, r);
		this.previous_room = r;
	}

	public void back()
	{
		this.changeRoom(this.previous_room);
	}

	public void call()
	{
		System.out.print("Command :> ");
		Scanner sc = new Scanner(System.in);

		String verb = sc.next();
		List<String> args = new ArrayList<>();
		System.out.println("Inputting: " + verb);

		while(sc.hasNext())
		{
			String arg = sc.next();
			args.add(arg);
			System.out.println("Inputting: " + arg);
		}

		Command cmd = new Command(this, verb, args);
		cmd.exec();
	}

	public void go(Door door)
	{
		this.getRoom().useDoor(this, door);
	}

	public void help()
	{
		System.out.println("Vous pouvez interagir avec le jeu à l'aide de commandes textuelles. " +
				"Voici la liste exhaustive de ces commandes, de leurs syntaxes et de leurs effets (entre crochets les paramètres optionnels): ");

		System.out.println("\t- go <nom d'une porte> : aller à une pièce voisine en traversant une porte");
		System.out.println("\t- help : afficher ce menu d'aide");
		System.out.println("\t- look [<nom d'un objet>] : afficher la description de ses environs ou de l'objet indiqué");
		System.out.println("\t- take <nom d'un objet> : prendre l'objet indiqué");

		System.out.println("\t- quit : quitter la partie");
		System.out.println("\t- use <nom d'un objet> [<nom d'un objet>] : utiliser un objet éventuellement sur un autre objet");
		System.out.println("\t- inventory : afficher le contenu de votre inventaire");
		System.out.println("\t- back : retour rapide à la pièce précédente");
	}

	public void look()
	{
		this.getRoom().describe();
	}

	public void look(Item item)
	{
		item.describe();
	}

	public void take(Item item)
	{
		//ATTENTION DUPLICATION!!!
		this.getInventory().addItem(item);
	}

	public void quit()
	{
		System.exit(0);
	}

	public void use(int Item)
	{
	}

	public void use(int UsableOn, int UsableBy)
	{
	}

	@Override
	public void isAttacked() {

	}

	@Override
	public void attack(Attackable a) {

	}
}