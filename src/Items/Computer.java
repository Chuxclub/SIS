package Items;

import Characters.Actor;
import Characters.Player;
import Containers.*;
import Events.Event;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Computer extends Item implements Serializable {

    private final Inventory files;
    private final Event event;

    public Computer(String description, String tag)
    {
        super(tag, description);
        this.files = new Inventory();
        this.event = null;
    }

    public Computer(String description, String tag, Event event)
    {
        super(tag, description);
        this.files = new Inventory();
        this.event = event;
    }

    public void addFile(File f){
        this.files.addItem(f);
    }

    public void printFile(String tag, UsableBy a) {

        if(a instanceof Actor) {

            Actor player = (Actor) a;

            try {
                File file = (File) this.files.getItem(tag);
                player.getInventory().addItem(file.getCopy());
                System.out.println("Now you have a copy of the " + file.getTag() + " in your inventory");
            }

            catch(NullPointerException | ClassCastException e) {
                System.out.println("Error :> This file doesn't exist");
            }
        }
    }

    @Override
    public void isUsed(UsableBy u) {

        if(u instanceof Player) {
            Player player = (Player) u;
            System.out.println("Welcome to the lab Computer. You can consult lab files, or generate a Pass. Please input a command :");

            boolean quit = false;
            while (!quit) {
                System.out.println("\n=== AVAILABLE COMMANDS ===");
                System.out.println("\t:> open : show a file");
                System.out.println("\t:> print : print a file");

                if(this.event != null)
                    System.out.println("\t:> " + this.event.getTag() + " : " + this.event.getDescription());

                System.out.println("\t:> quit");

                quit = playerInput(player);
            }
        }

        else
            System.out.println("Error :> This object can't use the computer");
    }

    public boolean playerInput(Player player) {

        Scanner sc = new Scanner(System.in);
        String userChoice = sc.nextLine();

        if (this.event != null && userChoice.equals(this.event.getTag())) {
            this.event.getE().raise(player);
            return false;
        }

        else {
            try {
                switch (userChoice) {
                    case "open":
                        System.out.println("\nYou chose to open a file.");
                        System.out.println("\n=== AVAILABLE FILES ===");
                        this.files.showItems();
                        Scanner sc0 = new Scanner(System.in);
                        String choice = sc0.nextLine();

                        try {
                            this.files.getItem(choice).isUsed(player);
                            return false;
                        } catch (NullPointerException e) {
                            System.out.println("\nThis file doesn't exist");
                            return false;
                        }

                    case "print":
                        System.out.println("\nYou chose to print a file.");
                        System.out.println("\n=== AVAILABLE FILES ===");
                        this.files.showItems();
                        Scanner sc1 = new Scanner(System.in);
                        String print = sc1.nextLine();
                        printFile(print, player);
                        return false;

                    case "quit":
                        return true;

                    default:
                        System.out.println("\nPlease enter a valid input");
                        return false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a valid input");
                return false;
            }
        }
    }


}
