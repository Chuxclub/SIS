package Items;

import Characters.Actor;
import Containers.*;

import java.util.Scanner;

public class Computer extends Item {

    private Inventory files;
    private UsableBy u = null;


    public Computer(String description, String tag)
    {
        super(tag, description);
    }

    public Computer(String description, String tag, UsableBy u)
    {
        super(tag, description);
        this.u = u;
    }

    public void printFile(String tag, UsableBy a) {
        if(a instanceof Actor) {
            Actor player = (Actor) a;
            Item item = this.files.getItem(tag);
            this.files.removeItem(tag);
            player.getInventory().addItem(item);
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

            quit = playerInput(quit, player);
        }
    }

    public boolean playerInput(boolean quit, UsableBy player) {
        Scanner scan = new Scanner(System.in);
        int userChoice = scan.nextInt();

        switch (userChoice){
            case 0 :
                System.out.println("\nYou chose to open a file.");
                System.out.println("=== AVAILABLE FILES ===");
                this.files.showItems();
                String choice = scan.nextLine();
                this.files.getItem(choice).isUsed(this.u);
                break;
            case 1 :
                System.out.println("\nYou chose to print a file.");
                System.out.println("=== AVAILABLE FILES ===");
                this.files.showItems();
                String print = scan.nextLine();
                printFile(print, player);
                return false;
                break;
            case 2 :
                this.u.isUsedBy(this);
                return false;
                break;
            case 3 :
                return true;
                break;
            default :
                System.out.println("Please enter a valid input");
                return false;
                break;
        }
    }

    @Override
    public void isUsedBy(UsableOn u) { }
}
