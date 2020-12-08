package Items;

public interface UsableBy
{
	default void isUsedBy(UsableOn u)
	{
		System.out.println("This object has no effect here");
	}
}