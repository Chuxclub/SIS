package Game;


import Location.*;

public class SIS {

	Ship ship;
	private static final int NB_PLAYERS = 1;

	public SIS()
	{
		this.initGame();
		this.play();
		this.endGame();
	}

	public void printGameIntro()
	{
		System.out.println("\t\t\t ========================================= ");
		System.out.println("\t\t\t ============ SILENT IN SPACE ============ ");
		System.out.println("\t\t\t ========================================= ");

		System.out.println("\nWelcome to Silen In Space! This game was developed by Florian Legendre, Alexis Louail and" +
				" Vincent Tourenne as a universitary project.\nThis is a demo, hence all the features intended to be in the final " +
				"version aren't there.\nThis game is meant to be played by textual commands. Meaning that you must input valid commands with your " +
				"keyboards and the game will\nreact accordingly. For a thorough listing of commands, their syntaxes and effects, type help! Enjoy!\n");

		System.out.println("\t\t\t ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ \n\n");
	}

	public void initGame()
	{
		this.printGameIntro();
		System.out.println("You wake up feeling dizzy. Something is talking to you. Something not human.");
		this.ship = new Ship();
		this.ship.getNPC("Kilen").talk();
		this.ship.getNPC("Kilen").give("passT", this.ship.getPlayer());
		this.ship.getNPC("Kilen").setSpeech("You should hurry! I've managed to deal with the guards in the lab but it won't be long before they come back!");
	}

	public boolean isEndGame()
	{
		return this.ship.getRoom(22).hasActor("me");
	}

	public void endGame()
	{
		System.out.println("\nThanks for playing Silent In Space!");
	}

	public void playTurn()
	{
		this.ship.getPlayer().call();
	}

	public void play()
	{
		while(true || !this.isEndGame())
		{
			this.playTurn();
		}
	}

}