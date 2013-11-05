package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test rename command.
 */
public class RenameCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new RenameCommand();
		
		good = new String[] {
				"rename table emp3 to fired;",
				"RENAME TABLE   EMP   TO   FIRED   ;",
				"    rename    TABLE fired    to    suicide1;",
				"rename   table   suicIDE To grievingFamily;"
		};
		
		bad = new String[] {
				"reame table emp to fired;",
				"RENAME TABLE   EMP   O   FIRED   ;",
				"    rename    TABLE fired    to    suicide...",
				"rename   tabl   suicIDE... To grievingFamily:(;"
		};
	}

}
