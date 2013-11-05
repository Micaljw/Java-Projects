package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test join command.
 */
public class JoinCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new JoinCommand();
		
		good = new String[] {
				"join emp and student;",
				"JOIN EMP   AND     PROFESSOR  ;",
				"   join xmandew    anD  iMP;",
				" JOin    table1  and    pEAR   ;"
		};
		
		bad = new String[] {
				"joi emp and student;",
				"JOIN EMP   AN     PROFESSOR  ;",
				"   join xmandew    anD  iMP",
				" Oin    table1  and    pEAR   ;"
		};
	}

}
