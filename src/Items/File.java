package Items;

public class File extends Item {

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
    public void isUsedBy(UsableOn u) { }
}
