package Commands;

public class Parser {

	Verb verb;

	public Parser(String verb)
	{
		this.verb = this.isValidVerb(verb);
	}

	public Verb isValidVerb(String verb)
	{
		Verb verbs[] = Verb.values();

		for(Verb v : verbs)
		{
			if(verb == v.getString())
				return v;
		}

		System.out.println("This verb doesn't exist");
		return null;
	}

	public Verb getVerb()
	{
		return this.verb;
	}
}