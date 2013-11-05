package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;

/**
 * Display table information.
 */
public class PrintCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*print\\s+(\\S+)\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		Database.get().dictionary(tableName);
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {	
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();
		
		if(doesMatch)
			tableName = input.group(1);		
		
		return doesMatch;
	}
}
