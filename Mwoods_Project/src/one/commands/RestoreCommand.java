package one.commands;

import java.util.regex.Pattern;

/**
 * The Class RestoreCommand.
 */
public class RestoreCommand implements ICommand {

	private final Pattern pattern = Pattern.compile("\\s*restore\\s+from\\s+'([\\w.*/]+)'\\s*;", Pattern.CASE_INSENSITIVE);
//	private String fileName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println("This is a correct restore command.");
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		
//		if(doesMatch)
//			fileName = input.group(1);		
		
		return pattern.matcher(s).matches();
	}

}
