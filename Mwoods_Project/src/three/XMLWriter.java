package three;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import one.WoodsException;

import two.database.Database;

/**
 * Create xml files.
 */
public class XMLWriter {
	
	private PrintWriter out;

	/**
	 * Instantiates a new xml writer.
	 */
	public XMLWriter() {
	}

	/**
	 * Write.
	 *
	 * @param file the file
	 * @throws WoodsException the woods exception
	 */
	public void write(String file) throws WoodsException {
		String ENCODING = "ISO-8859-1";
		out = createWriter(file);
		out.println("<?xml version=\"1.0\" encoding=\""+ENCODING+"\"?>");
		out.println("<!DOCTYPE database SYSTEM \"database.dtd\">");
		out.println("<database>");		
		Database.get().createXML(out);
		out.println("</database>");
		out.close();
	}
	
	/**
	 * Creates the dtd.
	 *
	 * @param file the file
	 * @param out the out
	 * @throws WoodsException the woods exception
	 */
	public void createDTD(String file, PrintWriter out) throws WoodsException{
		out = createWriter(file);
		out.println("<!ELEMENT database (table, field, fieldName, fieldType, size)>");
		out.println("<!ELEMENT table (#PCDATA)>");
		out.println("<!ELEMENT field (#PCDATA)>");
		out.println("<!ELEMENT fieldName (#PCDATA)>");
		out.println("<!ELEMENT fieldType (#PCDATA)>");
		out.println("<!ELEMENT size (#PCDATA)>");
		out.close();
	}
	
	/**
	 * Creates the writer.
	 *
	 * @param file the file
	 * @return the prints the writer
	 * @throws WoodsException the woods exception
	 */
	public PrintWriter createWriter(String file) throws WoodsException{	
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new WoodsException("Unable to create the file" + file);
		}
		return out;
	}
	
}
