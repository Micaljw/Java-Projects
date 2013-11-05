package one.commands;

import java.util.regex.Pattern;

import one.WoodsException;

/**
 * The Class UnionCommand.
 */
public class UnionCommand implements ICommand {

	/** The pattern. */
	private final Pattern pattern = Pattern.compile("\\s*union\\s+([\\w|$_]+)\\s+and\\s+([\\w|$_]+)\\s*;", Pattern.CASE_INSENSITIVE);
//	private String queryList;
//	private String queryListTwo;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		System.out.println("This is a correct union command.");
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		
//		if(doesMatch){
//			queryList = input.group(1);
//			queryListTwo = input.group(2);
//		}
		
		return pattern.matcher(s).matches();
	}

}
