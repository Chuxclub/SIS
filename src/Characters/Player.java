package Characters;

import Commands.Command;
import Commands.UnknownVerb;
import Doors.Door;
import Items.Item;
import Items.TakableItem;
import Items.UsableBy;
import Items.UsableOn;
import Location.Room;
import Location.Ship;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Player extends Actor implements Serializable
{
	private Ship ship;

	private static final String NAME = "me";

	public Player(Room r, Ship s)
	{
		super(NAME, r);
		this.ship = s;
	}

	public void back()
	{
		this.changeRoom(this.getPreviousRoom());
	}

	public void call()
	{
		System.out.print("\nCommand :> ");
		Scanner sc = new Scanner(System.in);
		String buffer = sc.nextLine();
		System.out.println();
		String[] words = buffer.split(" ");

		String verb = words[0];
		List<String> args = new ArrayList<>();

		if(words.length > 0)
		{
			for (int i = 1; i < words.length; i++) {
				args.add(words[i]);
			}
		}

		try {
			Command cmd = new Command(this, verb, args);
			cmd.exec();
		}

		catch(UnknownVerb e)
		{
			System.out.println("Enter help for valid verbs");
		}
	}

	public void drop(Item item)
	{
		this.getInventory().removeItem(item.getTag());
		this.getRoom().getInventory().addItem(item);
	}

	public void go(Door door)
	{
		door.open();
		this.getRoom().useDoor(this, door);
	}

	public void help()
	{
		System.out.println("You can interact with the game using textual commands. " +
				"\nHere's an exhaustive list of these commands, their syntaxes and of their effects (optional arguments are into brackets): ");

		System.out.println("\t- attack <attackable> : quick return to the previous room");
		System.out.println("\t- back : quick return to the previous room");
		System.out.println("\t- drop <item> : drop the designated item on the floor");
		System.out.println("\t- go <door name> : go to a neighbour room using the indicated door");
		System.out.println("\t- help : display this help menu");

		System.out.println("\t- info : display the stats of your character");
		System.out.println("\t- back : quick return to the previous room");
		System.out.println("\t- save : save the current state of the game");
		System.out.println("\t- inventory : display the content of your inventory");
		System.out.println("\t- look [<object's name>] : display the description of your surroundings or of the indicated object (the object must be in your inventory)");
		System.out.println("\t- quit : leave the game");
		System.out.println("\t- take <object's name> : take the indicated object");

		System.out.println("\t- talk <npc> : talk to the designated npc");
		System.out.println("\t- use <object's name> [<object's name>] : use an object possibly on another indicated object");

	}

	public void info()
	{
		System.out.println("You have " + this.getHp() + "hp");
		System.out.println("You have " + this.getAttackPower() + " attack power");
	}

	@Override
	public void isAttacked(Attacker a)
	{
		super.isAttacked(a);

		if(this.isDead())
			System.out.println("You are now dead...");

		else {
			if (a instanceof Actor) {
				Actor actor = (Actor) a;

				if(actor.getName().equals(this.getName()))
					System.out.println("You hit yourself! You've just lost " + actor.getAttackPower() + "hp! So much for your mental health...");

				else
					System.out.println(actor.getName() + " hit you! You've just lost " + actor.getAttackPower() + "hp!");
			}
		}
	}

	public void look()
	{
		this.getRoom().describe();
	}

	public void look(Item item)
	{
		item.describe();
	}

	public void look(Door d)
	{
		d.describe();
	}

	public void take(TakableItem item)
	{
		this.getRoom().getInventory().removeItem(item.getTag());
		this.getInventory().addItem(item);
	}

	public void talk(NPC npc)
	{
		npc.talk();
	}

	public void quit()
	{
		System.out.println("Thanks for playing Silent In Space!");
		System.exit(0);
	}

	public void use(Item item)
	{
		item.isUsed(this);
	}

	public void use(UsableOn on, UsableBy by)
	{
		by.isUsedBy(on);
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("saveData.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(this.ship);
			oos.close();
			System.out.println("You successfully saved the game!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}