package one.commands;

import one.WoodsException;

import org.junit.Test;

import three.SaxReader;

/**
 * The Class DatatypeTest.
 */
public class XMLReaderTest {

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void setUp() throws Exception {
		try{
			new SaxReader().saxReader();
		} catch(WoodsException e){
			System.out.println(e.getMessage());
		}
		
	}

}
