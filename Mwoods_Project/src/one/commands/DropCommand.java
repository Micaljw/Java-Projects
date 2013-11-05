package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * Delete the specified table.
 */
public class DropCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*drop\\s+table\\s+([\\d\\w|$_]+)\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;

	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		try {
			Database.get().dropTable(tableName);
		} catch (WoodsException e) {
			throw new WoodsException("Table does not exist.");
		}     
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {	
		
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();
		
		if(doesMatch){
			tableName = input.group(1);
		}
		
		return doesMatch;
	}

}
