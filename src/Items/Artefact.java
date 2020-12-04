package Items;

public class Artefact extends TakableItem
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
