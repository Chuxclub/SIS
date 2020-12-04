package Items;

import Characters.Actor;
import Containers.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Computer extends Item {

    private Inventory files;
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
                this.files.removeItem(tag);
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
            System.out.println("\t[0] open : show a file");
            System.out.println("\t[1] print : print a file");
            System.out.println("\t[2] unlock : open a door");
            System.out.println("\t[3] quit");

            quit = playerInput(player);
        }
    }

    public boolean playerInput(UsableBy player) {

        try {
            Scanner scan = new Scanner(System.in);
            int userChoice = scan.nextInt();

            switch (userChoice) {
                case 0:
                    System.out.println("\nYou chose to open a file.");
                    System.out.println("=== AVAILABLE FILES ===");
                    this.files.showItems();
                    Scanner sc0 = new Scanner(System.in);
                    String choice = sc0.nextLine();
                    this.files.getItem(choice).isUsed(this.u);
                    return false;
                case 1:
                    System.out.println("\nYou chose to print a file.");
                    System.out.println("=== AVAILABLE FILES ===");
                    this.files.showItems();
                    Scanner sc1 = new Scanner(System.in);
                    String print = sc1.nextLine();
                    printFile(print, player);
                    return false;
                case 2:
                    Pass passC = new Pass("passC", "Computer generated pass.", PassType.C);
                    this.u.isUsedBy(passC);
                    return false;
                case 3:
                    return true;
                default:
                    System.out.println("Please enter a valid input");
                    return false;
            }
            }

        catch(InputMismatchException e)
        {
            return false;
        }
    }

    @Override
    public void isUsedBy(UsableOn u) { }
}
