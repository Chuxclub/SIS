package Game;


import Location.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;

public class SIS implements Serializable {

	Ship ship;

	public SIS()
	{
		this.initGame();
		this.play();
		this.endGame();
	}

	public void initGame()
	{
		this.printGameIntro();
		this.printScenario();

		Scanner scan = new Scanner(System.in);
		System.out.println("Load an existing game? (Type \"yes\" if you have a save file. Press Enter for a new game.)");
		System.out.print("\nCommand :> ");
		String userChoice = scan.nextLine();

		if (userChoice.equals("yes")) {
			try {
				FileInputStream fileIn = new FileInputStream("saveData.txt");
				ObjectInputStream ois = new ObjectInputStream(fileIn);
				this.ship = new Ship((Ship) ois.readObject());
				ois.close();
				System.out.println("You successfully loaded the game!");
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("No save data was found! You need to save at least one time before being able to load a save.");
				System.out.println("\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n\n");

				System.out.println("You wake up feeling dizzy. Something is talking to you. Something not human.");
				this.ship = new Ship();
				this.ship.getNPC("Kilen").talk();
				this.ship.getNPC("Kilen").give("passT", this.ship.getPlayer());
				this.ship.getNPC("Kilen").setSpeech("You should hurry! I've managed to deal with the guards in the lab but it won't be long before they come back!");
			}
		} else {
			System.out.println("\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n\n");
			System.out.println("You wake up feeling dizzy. Something is talking to you. Something not human.");
			this.ship = new Ship();
			this.ship.getNPC("Kilen").talk();
			this.ship.getNPC("Kilen").give("passT", this.ship.getPlayer());
			this.ship.getNPC("Kilen").setSpeech("You should hurry! I've managed to deal with the guards in the lab but it won't be long before they come back!");
		}
	}

	public boolean isEndGame()
	{
		return (
				(this.ship.getRoom(13).hasActor("me")
						&& this.ship.getNPC("Umhon").getInventory().getItem("doctorLog.txt") != null)
						|| this.ship.getPlayer().isDead()
		);
	}

	public void endGame()
	{
		System.out.println("\nThanks for playing Silent In Space!");
	}

	public void play()
	{
		while(!this.isEndGame())
		{
			this.playTurn();
		}
	}

	public void playTurn()
	{
		this.ship.getPlayer().call();
	}

	public void printGameIntro()
	{
		System.out.println("\t\t\t ========================================= ");
		System.out.println("\t\t\t ============ SILENT IN SPACE ============ ");
		System.out.println("\t\t\t ========================================= ");

		System.out.println("\nWELCOME to Silent In Space! This game was developed by Florian Legendre, Alexis Louail and" +
				" Vincent Tourenne as a universitary project.\nThis is a demo, hence all the features intended to be in the final " +
				"version aren't there.\nThis game is meant to be played by textual commands. Meaning that you must input valid commands with your " +
				"keyboards and the game will\nreact accordingly. For a thorough listing of commands, their syntaxes and effects, type help! Enjoy!\n");
	}

	public void printScenario()
	{
		System.out.println("SCENARIO: You wake up in an alien ship. You understand that you've been abducted and you must escape. Yet, before you escape you" +
				" have\nto bring to UMHON, an important alien person, the proof of the abominable experiments being conducted on humans. This proof is what\nwill" +
				" end the abductions and possibly the end of humanity. The escape room is ROOM 13. Good luck human!\n");
	}







}