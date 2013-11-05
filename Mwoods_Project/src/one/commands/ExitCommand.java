package one.commands;

import java.util.regex.Pattern;

import one.WoodsException;

import three.XMLWriter;

/**
 * Terminate the program.
 */
public class ExitCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*exit\\s*;", Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		new XMLWriter().write("database.xml");
		System.exit(0);
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {		
		return pattern.matcher(s).matches();
	}

}
