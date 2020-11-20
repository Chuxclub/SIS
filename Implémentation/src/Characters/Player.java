package Characters;

import Items.*;
import Doors.*;
import Location.*;

import java.util.List;
import java.util.Scanner;

public class Player extends Actor {

	public Player(Room room, List<Item> inventory)
	{
		super(room, inventory);
	}

	public void inputCommand(){
		Scanner sc = new Scanner(System.in);
		String cmd = sc.next();

		if(!(isValidCommand(cmd))) {

		}
	}

	public boolean isValidCommand(String cmd){
		boolean tmp = true;

		if()

		return tmp;
	}

	public void go(String door) {

	}

	public void help() {

	}

	public void look() {

	}

	public void look(Item item)
	{

	}

	public void take(Item item)
	{

	}

	public void quit()
	{

	}

	public void use(Item item)
	{

	}

	public void use(int item, Door door)
	{

	}

	public void save()
	{

	}

	@Override
	public void attack()
	{

	}

	@Override
	public void isAttacked()
	{

	}
}