package Commands;

public enum Verb {
	ATTACK("attack"), BACK("back"), DROP("drop"), GO("go"), HELP("help"),
	INFO("info"), INVENTORY("inventory"), LOOK("look"), QUIT("quit"),
	SAVE("save"), TAKE("take"), TALK("talk"), USE("use"), GIVE("give");
	private final String strValue;

	Verb(String s)
	{
		this.strValue = s;
	}

	public String getString()
	{
		return this.strValue;
	}
}