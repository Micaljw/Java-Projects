package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test define table command.
 */
public class DefineTableCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new DefineTableCommand();
		
		good = new String[] {
				"define table student1 having fields ( mike, 1);",
				"  DEFINE   TABLE   faculty having FIELDS  (prof, 0 )   ;",
				"DefiNE tablE   student    having    fields   ( mike, '08/04/1987' );",
				"define table   facu3lty    having   FIElds (prof, x = 2);"
		};
		
		bad = new String[] {
				"defie table student having fields (mike, 1);",
				"  DEFINE   TABLE   faculty having FIELDS  (prof, 0  ;",
				"DefiNE tablE   student    having    fields   (mike, '08/04/1987')",
				"define table   faculty    having   FIlds (prof, x = 2);"
		};
	}

}
