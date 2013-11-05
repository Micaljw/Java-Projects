package one.commands;

import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * TTest define index command.
 */
public class DefineIndexCommandTest extends AbstractCommandTest {

	/* (non-Javadoc)
	 * @see one.commands.AbstractCommandTest#setUp()
	 */
	@Override
	@Before //annotation
	public void setUp() throws Exception {
		command = new DefineIndexCommand();
		
		good = new String[] {
				"define index on emp2( ABCD);",
				"DEFINE INDEX ON emp   (ZeBrA )  ;",
				"   DeFiNe indeX   on   45767apple(green);",
				"  define     index   on jungle   (lioN)  ;"
		};
		
		bad = new String[] {
				"define index o emp(ABCD);",
				"DEFINE INDX ON emp   (ZeBrA)  ;",
				"   DeFiNe indeX   on   apple green);",
				"  define     index   on jungle   (lioN)  "
		};
	}

}
