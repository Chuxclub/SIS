package Items;

import java.io.Serializable;

public class File extends TakableItem implements Serializable {

    private final String content;

    public File(String tag, String description, String content){
        super(tag, description);
        this.content = content;
    }

    @Override
    public void isUsed(UsableBy u) {
        System.out.println(this.content);
    }

    @Override
    public TakableItem getCopy()
    {
        return  new File(this.getTag(), this.getDescription(), this.content);
    }
}
