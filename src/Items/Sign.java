package Items;

import java.io.Serializable;

public class Sign extends Item implements Serializable {
    private final String content;

    public Sign(String tag, String description, String content)
    {
        super(tag, description);
        this.content = content;
    }

    @Override
    public void describe()
    {
        System.out.println(this.getDescription());
    }

    @Override
    public void isUsed(UsableBy u)
    {
        System.out.println(content);
    }

    @Override
    public void isUsedBy(UsableOn u)
    { }
}
