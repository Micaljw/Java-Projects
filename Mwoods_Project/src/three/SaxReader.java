package three;

import java.io.FileNotFoundException;
import java.io.IOException;

import one.WoodsException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import two.database.Database;
import two.database.Table;

/**
 * Read xml files.
 */
public class SaxReader{
	
	/**
	 * Read .xml file specified.
	 *
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws WoodsException the woods exception
	 */
	public void saxReader() throws SAXException, IOException, WoodsException{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new DatabaseContentHandler());
		
		try{
			reader.parse("database.xml"); 
		} catch(FileNotFoundException e){
			throw new WoodsException("XML file was not found.");
		}
	}
	
	/**
	 * Override the default functions when reading the .xml file
	 * @author mwoods6
	 */
	private class DatabaseContentHandler extends DefaultHandler {

		
		private boolean user = false;
		private boolean table, fieldName, fieldType, size;
		private String tableName, nameOfField, nameOfFieldType, value;
		private Table userTable;

		/**
		 * Find the instance of the starting element
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				if(qName.equalsIgnoreCase("TABLE")) {
					user = true;
					table = true;
				}
				else if(localName.equalsIgnoreCase("FIELDNAME")){	
					user = true;
					fieldName = true;
				}
				else if(localName.equalsIgnoreCase("FIELDTYPE")){	
					user = true;
					fieldType = true;
				}
				else if(localName.equalsIgnoreCase("SIZE")){
					user = true;
					size = true;
				}
				
				for(int i = 0;i< attributes.getLength();i++) {
					System.out.println(attributes.getQName(i) + ": " + attributes.getValue(i));
				}
		}
		
		/**
		 * Find the instance of the end element
		 */
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(user) {
				try {
					if(qName.equalsIgnoreCase("NAME")){
						createNewTable();
					}
					else if(localName.equalsIgnoreCase("SIZE")){
						String[] myTable = {nameOfField, nameOfFieldType+ "(" + value + ")"};
						store(myTable);
					}	
					else if(localName.equalsIgnoreCase("FIELDTYPE")){
						String[] myTable = {nameOfField, nameOfFieldType};
						
						if(!nameOfFieldType.equalsIgnoreCase("char"))
							store(myTable);
					}
				} catch (WoodsException e) {}
				
				user = false;
			}
		}
		
		/**
		 * Restore fields into corresponding table
		 * @param table
		 * @throws WoodsException
		 */
		public void store(String[] table) throws WoodsException{
			userTable.addField(userTable.createDatatypes(table));
		}
		
		/**
		 * Create a new table from previously stored table
		 * @throws WoodsException 
		 */
		public void createNewTable() throws WoodsException{
			userTable = new Table(tableName);
			Database.get().storeTable(tableName, userTable);
		}
	
		/**
		 * Set found string corresponding with the occurred field type
		 */
	 	@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String username = new String(ch, start, length);
			
			if(user)
				if(table){
					tableName = username;
					table = false;
				}
				else if(fieldName){
					nameOfField = username;
					fieldName = false;
				}
				else if(fieldType){
					nameOfFieldType = username;
					fieldType = false;
				}
				else if(size){
					value = username;
					size = false;
				}
		}	
	}
}


