package Items;

import Characters.Player;

public abstract class TakableItem extends Item
{
    public TakableItem(String tag, String description) {
        super(tag, description);
    }

    public void isTaken(Player p)
    {
        p.take(this);
    }
}
