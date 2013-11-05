package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * Test print command.
 */
public class PrintCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new PrintCommand();
		
		good = new String[] {
				"Print dictionary;",
				"  print emp   ;",
				"PRINT MYTABLE;",
				"   PRInT     h          ;"
		};
		
		bad = new String[] {
				"Print dictionary",
				"  prit emp   ;",
				"PRINT MYTABLE 1;",
				"       RInT            hello          ;"	
		};
	}

}
