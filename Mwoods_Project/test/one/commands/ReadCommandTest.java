package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test read command.
 */
public class ReadCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new ReadCommand();
		
		good = new String[] {
				"read 'myFile';",
				"   read   ' empFile'  ;",
				" REad   'piZZA ';",
				"READ              'Japan';"			
		};
		
		bad = new String[] {
				"red 'myFile';",
				"   read   'empFile'  ",
				" REad   piZZA';",
				"READ              'Japan;"	
		};
	}

}
