package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test restore command.
 */
public class RestoreCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new RestoreCommand();
		
		good = new String[] {
				"restore from 'myFile';",
				"   restore from    'empFile'  ;",
				" RestoRE    froM   'piZZA';",
				"RESTORE FROM 'Japan';"			
		};
		
		bad = new String[] {
				"restor from 'myFile';",
				"   restore from    empFile'  ;",
				" RestoRE    froM   'piZZA'",
				"RESTORE FRM 'Japan';"	
		};
	}

}
