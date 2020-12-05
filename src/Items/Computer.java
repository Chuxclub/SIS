package Items;

import Characters.Actor;
import Containers.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Computer extends Item {

    private final Inventory files;
    private UsableBy u = null;


    public Computer(String description, String tag)
    {
        super(tag, description);
        this.files = new Inventory();
    }

    public Computer(String description, String tag, UsableBy u)
    {
        super(tag, description);
        this.files = new Inventory();
        this.u = u;
    }

    public void addFile(File f){
        this.files.addItem(f);
    }

    public void printFile(String tag, UsableBy a) {
        if(a instanceof Actor)
        {
            Actor player = (Actor) a;

            try
            {
                Item item = this.files.getItem(tag);
                player.getInventory().addItem(item);
            }

            catch(NullPointerException e)
            {
                System.out.println("This file doesn't exist");
            }
        }
    }

    @Override
    public void isUsed(UsableBy player) {
        System.out.println("Welcome to the lab Computer. You can consult lab files, or generate a Pass. Please input a command :");

        boolean quit = false;
        while(!quit) {
            System.out.println("=== AVAILABLE COMMANDS ===");
            System.out.println("\t:> open : show a file");
            System.out.println("\t:> print : print a file");
            System.out.println("\t:> unlock : open a door");
            System.out.println("\t:> quit");

            quit = playerInput(player);
        }
    }

    public boolean playerInput(UsableBy player) {

        try {
            Scanner sc = new Scanner(System.in);
            //int userChoice = scan.nextInt();
            String userChoice = sc.nextLine();

            switch (userChoice) {
                case "open":
                    System.out.println("\nYou chose to open a file.");
                    System.out.println("=== AVAILABLE FILES ===");
                    this.files.showItems();
                    Scanner sc0 = new Scanner(System.in);
                    String choice = sc0.nextLine();

                    try {
                        this.files.getItem(choice).isUsed(this.u);
                        return false;
                    }
                    catch(NullPointerException e)
                    {
                        System.out.println("This file doesn't exist");
                        return false;
                    }

                case "print":
                    System.out.println("\nYou chose to print a file.");
                    System.out.println("=== AVAILABLE FILES ===");
                    this.files.showItems();
                    Scanner sc1 = new Scanner(System.in);
                    String print = sc1.nextLine();
                    printFile(print, player);
                    return false;

                case "unlock":
                    Pass passC = new Pass("passC", "Computer generated pass.", PassType.C);
                    this.u.isUsedBy(passC);
                    return false;

                case "quit":
                    return true;

                default:
                    System.out.println("Please enter a valid input");
                    return false;
            }
        }

        catch(InputMismatchException e)
        {
            System.out.println("Please enter a valid input");
            return false;
        }
    }

    @Override
    public void isUsedBy(UsableOn u) { }
}
