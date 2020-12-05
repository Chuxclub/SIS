package Items;

import java.io.Serializable;

public class Artefact extends TakableItem implements Serializable
{
    public Artefact(String tag, String description) {
        super(tag, description);
    }

    @Override
    public void isUsed(UsableBy u)
    {
        this.describe();
    }

    @Override
    public void isUsedBy(UsableOn u) { }
}
