package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test minus command.
 */
public class MinusCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new MinusCommand();
		
		good = new String[] {
				"minus emp and student;",
				"MINUS EMP   AND     PROFESSOR  ;",
				"   minus xmandew    anD  iMP;",
				" MiNuS    table1  and    pEAR   ;"
		};
		
		bad = new String[] {
				"mnus emp and student;",
				"MINUS EMP   AN     PROFESSOR  ;",
				"   minus xmandew    anD  iMP",
				" iNuS    table1  and    pEAR   ;"
		};
	}

}
