package one.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.WoodsException;

import two.database.Database;
import two.database.Table;

/**
 * Create a table with the user's specifications.
 */
public class DefineTableCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*define\\s+table\\s+([\\d\\w|$_]+)\\s+having\\s+fields\\s*\\(\\s*(.*?)\\s*\\)\\s*;", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String fieldList;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		tableName = tableName.toLowerCase();
		Table myTable = new Table(tableName);
		myTable.parseString(fieldList);
		
		try {
			Database.get().storeTable(tableName, myTable);
		} catch (NullPointerException e) {
			throw new WoodsException("Was not able to define the table.");
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
			fieldList = input.group(2);
		}
		
		return doesMatch;		
	}

}
