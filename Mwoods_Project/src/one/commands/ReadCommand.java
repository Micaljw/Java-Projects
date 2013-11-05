package one.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import one.Driver;
import one.WoodsException;

/**
 * Process the information within a file.
 */
public class ReadCommand implements ICommand {
	
	private final Pattern pattern = Pattern.compile("\\s*read\\s+'\\s*([\\w.*/]+)\\s*'\\s*;", Pattern.CASE_INSENSITIVE);
	private String fileName;
	
	/* (non-Javadoc)
	 * @see one.commands.ICommand#execute()
	 */
	@Override
	public void execute() throws WoodsException {
		
		Scanner inFile;
		try {
			inFile = new Scanner(new FileReader(fileName));
			new Driver().run(inFile);
		} catch (FileNotFoundException e) {
			throw new WoodsException("Could not find the file.", e);
		} catch (NoSuchElementException n) {
			throw new WoodsException("Reached the end of " + fileName + ".", n);
		}
	}

	/* (non-Javadoc)
	 * @see one.commands.ICommand#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String s) {
		Matcher input = pattern.matcher(s);
		boolean doesMatch = input.matches();	
		
		if(doesMatch)
			fileName = input.group(1);		
		
		return doesMatch;		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		try {
			new ReadCommand().execute();
		} catch (WoodsException e) {
			System.out.println(e.getMessage());
		}
	}
}
