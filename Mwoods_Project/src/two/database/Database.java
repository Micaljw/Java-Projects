package two.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import one.WoodsException;
import three.XMLWriter;

/**
 * Create the database.
 */
public class Database {
	
	private HashMap<String, Table> map = new HashMap<String, Table>();
	private static Database db;
	
	/**
	 * Instantiates a new database.
	 */
	private Database(){}
	
	/**
	 * Assures only one copy of database,
	 * along with universal access.
	 *
	 * @return the database
	 */
	public static Database get(){
		if(db == null)
			db = new Database();
		return db;
	}
	
	/**
	 * Store user's table
	 *
	 * @param tableName the table name
	 * @param obj the obj
	 * @throws WoodsException the woods exception
	 */
	public void storeTable(String tableName, Table obj){ 
		if(map.containsKey(tableName.toLowerCase()))
			System.out.println("A table with this name already exists.");
		else
			map.put(tableName, obj);
	}
	
	/**
	 * Rename specified table.
	 *
	 * @param oldTable the old table
	 * @param newTable the new table
	 * @throws WoodsException the woods exception
	 */
	public void renameTable(String oldTable, String newTable) throws WoodsException{
		Table temp = map.get(oldTable);
		
		if(temp == null)
			throw new WoodsException("The table " + oldTable + " does not exist to rename.");
		else if(oldTable.equalsIgnoreCase(newTable))
			throw new WoodsException("This table name already exists.");
		
		temp.changeRAFname(newTable);
		temp.setTableName(newTable);
		map.put(newTable, temp);
		map.remove(oldTable);
	}
	
	/**
	 * Delete specified table.
	 *
	 * @param tableName the table name
	 * @throws WoodsException the woods exception
	 */
	public void dropTable(String tableName) throws WoodsException{
		Table temp = map.get(tableName);
		
		try{
			temp.removeFile();
		} catch (NullPointerException e){
			throw new WoodsException("This table does not exist.");
		}
			
		map.remove(tableName);
	}		
	
	/**
	 * Print all stored tables.
	 *
	 * @param tableName the table name
	 * @throws WoodsException the woods exception
	 */
	public void dictionary(String tableName) throws WoodsException{
		if(tableName.trim().equalsIgnoreCase("dictionary"))
			for(Table t : map.values())
				System.out.println("== Table ==\n" + t.toString() + "\n");
		else
			checkTable(tableName);
	}
	
	/**
	 * Test table names from xml file.
	 *
	 * @return the string
	 */
	public String testTable(){
		String table = " ";
		
		for(Table t : map.values()){
			table += " " + t.getTable().toString();
		}
		return table;
	}
	
	/**
	 * Print specified table.
	 *
	 * @param tableName the table name
	 * @throws WoodsException the woods exception
	 */
	public void checkTable(String tableName) throws WoodsException {
		Table temp = map.get(tableName.toLowerCase());
		try{
			temp.printTable(tableName);
		} catch(NullPointerException e){
			throw new WoodsException("This table does not exist.");
		}
	}

	/**
	 * Create XML file.
	 *
	 * @param out the out
	 * @throws WoodsException the woods exception
	 */
	public void createXML(PrintWriter out) throws WoodsException{
		for(Table t : map.values())
			out.println(t.toXMLString(t));
		new XMLWriter().createDTD("database.dtd", out);
	}
	
	/**
	 * Check table names from insert statement.
	 *
	 * @param value the value
	 * @param tableName the table name
	 * @throws WoodsException the woods exception
	 */
	public void insertHandler(String value, String tableName) throws WoodsException{		
		Table temp = map.get(tableName.toLowerCase());
		
		try {
			temp.insertValues(value);
		} catch (IOException e) {
			throw new WoodsException("Unable to insert values.");
		} catch (NullPointerException e){
			throw new WoodsException("This table does not exist.");
		}
	}

	/**
	 * Check for a where clause from select statement
	 * @param tableName
	 * @param expression
	 * @throws WoodsException
	 */
	public void selectTableValues(String tableName, String expression) throws WoodsException {
		try{
			if(!expression.isEmpty())
				selectWhereHandler(tableName, expression);
		} catch(NullPointerException e){
			dictionary(tableName);
		}
	}

	/**
	 * Handle where clause from select statement
	 * @param tableName
	 * @param expression
	 * @throws WoodsException
	 */
	public void selectWhereHandler(String tableName, String expression) throws WoodsException {
		Table temp = map.get(tableName);
		
		try {
			temp.getMatcher(expression);
			temp.printWhere();
		} catch (IOException e) {
			throw new WoodsException("Unable to print the where clause.");
		}
	}
	
	/**
	 * Check for where clause in update statement
	 * @param tableName
	 * @param fieldName
	 * @param value
	 * @param expression
	 * @throws IOException
	 * @throws WoodsException
	 */
	public void updateHandler(String tableName, String fieldName, String value, String expression) throws IOException, WoodsException {
		Table temp = map.get(tableName);
		try{
			if(!expression.isEmpty())
				updateWhereHandler(fieldName, value, expression, temp);
		} catch(NullPointerException e){
			temp.updateField(fieldName, value);
		}
	}
	
	/**
	 * Handle where clause from update statement
	 * @param fieldName
	 * @param value
	 * @param expression
	 * @param temp
	 * @throws WoodsException
	 */
	private void updateWhereHandler(String fieldName, String value, String expression, Table temp) throws WoodsException {
		try{
			temp.getMatcher(expression);
			temp.updateSpecificField(fieldName, value);
		} catch (IOException e){
			throw new WoodsException("Unable to update specified field.");
		}
		
	}

	/**
	 * Check for a where clause in delete statement
	 * @param tableName
	 * @param expression
	 * @throws WoodsException
	 * @throws IOException
	 */
	public void deleteTableInformation(String tableName, String expression) throws WoodsException, IOException {
		Table temp = map.get(tableName);
		
		try{
			if(!expression.isEmpty()){
				temp.getMatcher(expression);
				temp.deleteSpecifiedRows();
			}
		} catch(NullPointerException e){
			temp.deleteAllInformation();
		}	
	}
}