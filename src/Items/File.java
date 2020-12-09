package Items;

import java.io.Serializable;

public class File extends Item implements Serializable {

    private final String content;

    public File(String tag, String description, String content){
        super(tag, description, true, true);
        this.content = content;
    }

    public File getCopy()
    {
        return new File(this.getTag(), this.getDescription(), this.content);
    }

    @Override
    public void isUsed(UsableBy u) {
        System.out.println(this.content);
    }
}
