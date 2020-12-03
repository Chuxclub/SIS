package Commands;

import Characters.*;
import Doors.Door;
import Items.Item;
import Items.TakableItem;
import Items.UsableBy;
import Items.UsableOn;

import java.util.List;

public class Command {

	Player caller;
	Parser parser;
	Converter converter;
	private String verb;
	private List<String> args;
	private Verb v;

	public Command(Player player, String verb, List<String> args) throws UnknownVerb
	{
		this.caller = player;
		this.verb = verb;
		this.args = args;

		try {
			this.parser = new Parser(this.verb);
		}

		catch(UnknownVerb e) {
			throw e;
		}

		this.v = this.parser.getVerb();

		this.converter = new Converter(this.caller);
	}

	public void exec()
	{
		switch(this.v)
		{
			case GO:
				try {
					Door d = this.converter.convertDoor(this.args.get(0));
					this.caller.go(d);
				}

				catch(StringRequestUnmatched e)
				{
					System.out.println("Error :> This isn't a door!");
				}
				break;

			case DROP:
				if(args.size() == 0)
					System.out.println("Error :> Please indicate which item you want to drop");

				else {
					try {
						Item item = this.converter.convertPlayerItem(this.args.get(0));
						this.caller.drop(item);
					} catch (StringRequestUnmatched e) {
						System.out.println("Error :> This item isn't in your inventory");
					}
				}
				break;

			case HELP:
				this.caller.help();
				break;

			case LOOK:
				if(args.size() == 0)
					this.caller.look();

				else
				{
					try {
						Item item = this.converter.convertItem(this.args.get(0));
						this.caller.look(item);
					}

					catch(StringRequestUnmatched e) {
						System.out.println("Error :> This item isn't in your inventory or in this room");
					}
				}
				break;

			case TAKE:
				if(args.size() == 0)
					System.out.println("Error :> Please indicate which item you want to take");

				else {
					try {
						TakableItem item = this.converter.convertTakableItem(this.args.get(0));
						this.caller.take(item);
					} catch (StringRequestUnmatched e) {
						System.out.println("Error :> This item isn't in your inventory or in this room or can't be taken with you");
					}
				}
				break;

			case QUIT:
				caller.quit();
				break;

			case USE:
				if(this.args.size() < 1)
				{
					System.out.println("Error :> I don't know which item you want to use.");
				}

				else if(args.size() == 1)
				{
					try {
						Item item = this.converter.convertItem(this.args.get(0));
						this.caller.use(item);
					}

					catch(StringRequestUnmatched e)
					{
						System.out.println("Error :> I don't know this item");
					}
				}

				else
				{
					try
					{
						UsableOn on = this.converter.convertUsableOn(this.args.get(0));
						UsableBy by = this.converter.convertUsableBy(this.args.get(1));
						this.caller.use(on, by);
					}

					catch(StringRequestUnmatched e)
					{
						System.out.println("Error :> I don't know one of the items");
					}
				}
				break;

			case INVENTORY:
				this.caller.getInventory().showItems();
				break;

			case INFO:
				this.caller.info();
				break;

			case BACK:
				this.caller.back();
				break;
		}
	}

}