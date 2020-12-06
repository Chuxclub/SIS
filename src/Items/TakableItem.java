package Items;

import java.io.Serializable;

public abstract class TakableItem extends Item implements Serializable
{
    public TakableItem(String tag, String description) {
        super(tag, description);
    }

    public abstract TakableItem getCopy();
}
