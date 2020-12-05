package Characters;

import Items.Item;
import Location.Room;

import java.io.Serializable;
import java.util.List;

public class Umhon extends NPC implements Serializable {

    public Umhon(String name, boolean isHostile, boolean isAlly, List<Item> items, Room r) {
        super(name, isHostile, isAlly, items, r);
    }

    @Override
    public void talk()
    {
        String currSpeech = this.getSpeech();
        if(currSpeech != null){
            if(this.getInventory().getItem("doctorLog811.txt") == null){
                System.out.println(currSpeech);
                this.setSpeech("Bring me some evidence of what they are doing to your species, " +
                        "and I'll give you the code to the Captain's laptop!");
            }
            else {
                System.out.println("Thank you so much sweetie! Now, let me read that...\n" +
                        "... Oh. Oh, my.\n" +
                        "Here is your code. Thank you for everything, I'm going to have a talk with my husband later.");
                this.setSpeech("Thank you for everything, I'm going to have a talk with my husband later.");
                //this.give("CaptainCode", Insérez Joueur Ici);
                this.setAlly(true);
            }
        }
        else
            System.out.println("This person has nothing to say to you...");
    }

    public void isAttacked(Attacker a)
    {
        super.isAttacked(a);

        if(this.isDead()) {
            System.out.println(this.getName() + " is dead...");
            System.out.println("They dropped a piece of paper.");
            this.getRoom().getInventory().addItem(this.getInventory().getItem("CaptainCode"));
        }
        else
        {
            System.out.println(this.getName() + " gasps with pain, " + this.getName() + " only has " + this.getHp() + "hp left!");

            if (this.getAlly())
            {
                this.setAlly(false);
            }

            else
            {
                if (!this.getHostile())
                    this.setHostile(true);

                if(a instanceof Attackable) {
                    this.attack((Attackable) a);
                }
            }
        }
    }
}
