package one.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * Handle behavior of all test methods.
 */
public abstract class AbstractCommandTest {

	/** The good. */
	protected String[] good;

	/**
	 * Initialize.
	 *
	 * @throws Exception the exception
	 */
	public abstract void setUp() throws Exception;

	/** The bad. */
	protected String[] bad;
	
	/** The command. */
	protected ICommand command;

	/**
	 * Instantiates a new abstract command test.
	 */
	public AbstractCommandTest() {
		super();
	}

	/**
	 * Check for valid or invalid command.
	 */
	@Test
	public void testMatches() {
		for(String s : good){
			assertTrue(command.matches(s));
		}
		
		for(String s : bad){
			assertFalse(command.matches(s));
		}
	}

}