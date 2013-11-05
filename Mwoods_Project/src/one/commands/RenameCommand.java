package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * Change the name of the table.
 */
public class RenameCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*rename\\s+table\\s+([\\d\\w|$_]+)\\s+to\\s+([\\d\\w|$_]+)\\s*;", Pattern.CASE_INSENSITIVE);
	
	private String oldName;
	
	private String newName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		Database.get().renameTable(oldName.toLowerCase(), newName.toLowerCase());
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();	
		
		if(doesMatch){
			oldName = input.group(1);
			newName = input.group(2);
		}

		return doesMatch;	
	}

}
