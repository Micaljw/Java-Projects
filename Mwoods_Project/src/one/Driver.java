package one;

import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.SAXException;

import one.commands.BackupCommand;
import one.commands.DefineIndexCommand;
import one.commands.DefineTableCommand;
import one.commands.DeleteCommand;
import one.commands.DropCommand;
import one.commands.ExitCommand;
import one.commands.ICommand;
import one.commands.InsertCommand;
import one.commands.IntersectionCommand;
import one.commands.JoinCommand;
import one.commands.MinusCommand;
import one.commands.PrintCommand;
import one.commands.ProjectionCommand;
import one.commands.ReadCommand;
import one.commands.RenameCommand;
import one.commands.RestoreCommand;
import one.commands.SelectCommand;
import one.commands.UnionCommand;
import one.commands.UpdateCommand;
import three.SaxReader;

/**
 * Main class to initialize the user command line.
 */
public class Driver {
	
	/** The commands. */
	private final ICommand[] commands = new ICommand[]{
			new InsertCommand(),
			new SelectCommand(),
			new ExitCommand(),
			new BackupCommand(),
			new ReadCommand(),
			new RestoreCommand(),
			new DefineTableCommand(),
			new RenameCommand(),
			new DropCommand(),
			new DefineIndexCommand(),
			new DeleteCommand(),
			new UpdateCommand(),
			new PrintCommand(),
			new ProjectionCommand(),
			new JoinCommand(),
			new UnionCommand(),
			new MinusCommand(),
			new IntersectionCommand()};
	
	/**
	 * Run command line.
	 *
	 * @param sc the sc
	 * @throws WoodsException the woods exception
	 */
	public void run(Scanner sc) throws WoodsException{
	
		String userCommand = "";
		
		while(!userCommand.endsWith(";")){
			System.out.print(">");			
			userCommand += " " + sc.nextLine().trim();		
		}
		
		testCommand(userCommand, sc);
		System.out.println("This command is not valid.");
		run(sc);
	}
	
	/**
	 * Check validity of user command.
	 *
	 * @param myString the my string
	 * @param sc the sc
	 * @throws WoodsException the woods exception
	 */
	public void testCommand(String myString, Scanner sc) throws WoodsException{
		
		for(ICommand command : commands){
			if(command.matches(myString)){
				try {
					command.execute();
				} catch (WoodsException e) {
					System.out.println(e.getMessage());
				}
				run(sc);
			}
		}
	}

	/**
	 * Begin user interface.
	 *
	 * @param args the arguments
	 * @throws WoodsException the woods exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws WoodsException, SAXException, IOException {
		try{
			new SaxReader().saxReader();
		} catch(WoodsException e){
			System.out.println(e.getMessage());
		}
		new Driver().run(new Scanner(System.in));
	}

}
