package three.xml;


import static org.junit.Assert.*;
import one.WoodsException;

import org.junit.Before;
import org.junit.Test;

import three.SaxReader;
import two.database.Database;

/**
 * The Class XMLTest.
 */
public class XMLTest {
	
	/** The good. */
	String good;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			new SaxReader().saxReader();
		} catch(WoodsException e){
			System.out.println(e.getMessage());
		}
		
		good = Database.get().testTable().trim();
	}
	
	/**
	 * Test xml.
	 */
	@Test
	public void testXML(){
		assertTrue(good.matches("childofblech blechreturns blech"));
	}

}
