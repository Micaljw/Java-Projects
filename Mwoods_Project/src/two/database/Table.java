package two.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import five.IMatcher;
import five.MatcherFactory;
import one.WoodsException;
import two.datatype.BooleanType;
import two.datatype.CharType;
import two.datatype.Datatype;
import two.datatype.DateType;
import two.datatype.IntegerType;
import two.datatype.RealType;
import two.datatype.VarcharType;


/**
 * Control functions for the table.
 */
public class Table {
	
	private String tableName;
	private RandomAccessFile ras;
	private ArrayList<Datatype> myArr = new ArrayList<Datatype>();
	
	private final Pattern pattern = Pattern.compile("char\\s*\\(\\s*(\\d+)\\s*\\)", Pattern.CASE_INSENSITIVE);
	private long rowLength = 1;
	private Datatype dataTypeField;
	private ArrayList<Integer> storeArr = new ArrayList<Integer>();
	
	/**
	 * Instantiates a new table.
	 *
	 * @param tableName2 the table name2
	 * @throws WoodsException 
	 * @throws WoodsException 
	 */
	public Table(String tableName2) throws WoodsException{
		this.setTableName(tableName2);
		
		try {
			ras = new RandomAccessFile(tableName, "rw");
		} catch (FileNotFoundException e) {
			throw new WoodsException("Unable to create a new ras file.");
		}
	}
	
	/**
	 * Gets the table.
	 *
	 * @return the table name
	 */
	public String getTable(){
		return tableName;
	}
	
	/**
	 * Parse user fields.
	 *
	 * @param name the name
	 * @throws WoodsException the woods exception
	 */
	public void parseString(String name) throws WoodsException{
		String[] tokens = name.split(",");
		
		for(String t : tokens){
			String[] type = t.trim().split(" ");
			testField(type);
			addField(createDatatypes(type));
		}
	}
	
	/**
	 * Test duplicate field names.
	 *
	 * @param dt the dt
	 * @return true, if successful
	 * @throws WoodsException the woods exception
	 */
	public void testField(String[] name) throws WoodsException{
		for(Datatype d : myArr)
			if(d.getName().equalsIgnoreCase(name[0]))
				throw new WoodsException("Field names cannot be duplicated. Please redefine table.");	
	}
	
	/**
	 * Insert data types into array list.
	 *
	 * @param dt the dt
	 */
	public void addField(Datatype dt){		
		myArr.add(dt);
	}

	/**
	 * Create a new data type 
	 *
	 * @param name the name
	 * @return the datatype
	 * @throws WoodsException the woods exception
	 */
	public Datatype createDatatypes(String[] name) throws WoodsException{
		Matcher input = pattern.matcher(name[1]);
	
		if(name[1].equalsIgnoreCase("Integer")){
			rowLength += Integer.SIZE/8;
			return new IntegerType(name[0], rowLength-4);
		}
		else if(name[1].equalsIgnoreCase("Varchar")){
			rowLength += Long.SIZE/8;
			return new VarcharType(name[0], rowLength-8);
		}
		else if(input.matches()){
			int parseChar = Integer.parseInt(input.group(1));
			rowLength += (Character.SIZE/8) * parseChar;
			return new CharType(name[0], parseChar, rowLength-(Character.SIZE/8) * parseChar);
		}
		else if(name[1].equalsIgnoreCase("Real")){
			rowLength += Double.SIZE/8;
			return new RealType(name[0], rowLength-8);
		}
		else if(name[1].equalsIgnoreCase("Boolean")){
			rowLength += Byte.SIZE/8;
			return new BooleanType(name[0], rowLength-1);
		}
		else if(name[1].equalsIgnoreCase("Date")){
			rowLength += Long.SIZE/8;
			return new DateType(name[0], rowLength-8);
		}
		throw new WoodsException("Incorrect Datatype entered.");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String myStr = " ";	
		myStr += tableName.toString() + "\n== Fields ==\n" + myArr.toString();
		
		return myStr;	
	}
	
	/**
	 * Change stored table information to string.
	 *
	 * @param t the t
	 * @return the string
	 */
	public String toXMLString(Table t){
		String xml = " ";
		xml = "<table>" + "<name>" + tableName + "</name>";
		for(Datatype dt : myArr){
			xml += "<field>" + dt.toXMLString() + "</field>";
		}
		xml += "</table>";
		return xml;
	}
	
	/**
	 * Insert the user values into corresponding tables.
	 *
	 * @param values the values
	 * @throws WoodsException the woods exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void insertValues(String values) throws WoodsException, IOException{
		
		String[] tokens = values.split("\\s*\\,");
		long fileLength = 0;
		
		try{
			fileLength = ras.length();
			ras.seek(fileLength);
			ras.writeBoolean(true);
			
			for(int i = 0; i < myArr.size(); i++){
				String input = tokens[i];	
				testInsertFormat(i, input);
				myArr.get(i).binaryWrite(input.replace('\'', ' ').trim(), ras);
			}
		} catch (ArrayIndexOutOfBoundsException o) {
			resetLength(fileLength);
			throw new WoodsException("Fields do not match. Please re-insert data.");
		} catch (NumberFormatException n){
			resetLength(fileLength);
			throw new WoodsException("Fields types do not match. Please re-insert data.");
		} catch (WoodsException e){
			resetLength(fileLength);
			throw new WoodsException(e.getMessage());
		}
	}
	
	/**
	 * Reset RAF pointer
	 * @param fileLength
	 * @throws IOException
	 */
	private void resetLength(long fileLength) throws IOException {
		ras.setLength(fileLength);
	}
	
	/**
	 * Test proper grammar for inserted values
	 * @param counter
	 * @param input
	 * @throws WoodsException
	 */
	private void testInsertFormat(int counter, String input) throws WoodsException {
		String temp = myArr.get(counter).getType();
		
		if(temp.equalsIgnoreCase("char") || temp.equalsIgnoreCase("date") || temp.equalsIgnoreCase("varchar")){
			if(!input.startsWith("'") && !input.endsWith("'"))
				throw new WoodsException(temp + " type not entered correctly. Must be wrapped in quotes.");
		}
		else if(temp.equalsIgnoreCase("real") || temp.equalsIgnoreCase("boolean") || temp.equalsIgnoreCase("integer"))
			if(input.startsWith("'") || input.endsWith("'"))
				throw new WoodsException(temp + " type not entered correctly. Cannot have quotes.");
	}
	
	/**
	 * Sets the table name.
	 *
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * Print the table values from the binary file.
	 *
	 * @param table the table
	 * @throws WoodsException the woods exception
	 */
	public void printTable(String table) throws WoodsException {
		String fields = "";
		
		try {
			for(int i = 0; i < myArr.size(); i++)
				System.out.print(myArr.get(i).getName() + "\t");
			
			System.out.println();
			for(int i = 0; i < ras.length()/rowLength; i++){
				ras.seek(i*rowLength);
				if(ras.readBoolean()){
					for(int j = 0; j < myArr.size(); j++)
						fields += myArr.get(j).getBinary(ras, i, rowLength).toString() + "\t";
					
					System.out.print(fields);
					System.out.println();
					fields = "";
				}
			}
		} catch (IOException e) {
			throw new WoodsException("There are no values in the table to display.");
		}
	}
	
	/**
	 * Change the RAF name for the new table name
	 * @param newTable
	 * @throws WoodsException
	 */
	public void changeRAFname(String newTable) throws WoodsException {
		File newTableTemp = new File(newTable);
		File oldTableTemp = new File(tableName);
		
		try {
			oldTableTemp.renameTo(newTableTemp);
			oldTableTemp = newTableTemp;
			ras = new RandomAccessFile(oldTableTemp, "rw");
		} catch (IOException e) {
			throw new WoodsException("Unable to close the ras file.");
		}
	}
	
	/**
	 * Remove the RAF 
	 * @throws WoodsException
	 */
	public void removeFile() throws WoodsException {
		File oldTableTemp = new File(tableName);
		
		try {
			ras.close();
			if(!oldTableTemp.delete())
				throw new WoodsException("Unable to delete " + tableName + "'s binary file.");
		} catch (IOException e) {
			throw new WoodsException("Unable to close the ras file.");
		}
	}

	/**
	 * Get the rows that match the user's where clause
	 * @param expression
	 * @throws WoodsException
	 * @throws IOException
	 */
	public void getMatcher(String expression) throws WoodsException, IOException {
		
		String relop = whereRelop(expression);
		String[] tokens = expression.split("\\s*"+relop);
		getFieldType(tokens[0]);
		
		IMatcher match = MatcherFactory.getMatcher(ras, dataTypeField, tokens[1].replace('\'', ' ').trim().toLowerCase(), relop, rowLength);
		
		for(int i = 0; i < ras.length()/rowLength; i++){
			if(match.isMatch(i))
				storeArr.add(1);
			else
				storeArr.add(0);
		}
	}
	
	/**
	 * Print all rows necessary from the select where clause
	 * @throws IOException
	 */
	public void printWhere() throws IOException{
		Object[] myList = storeArr.toArray();
		
		for(int i = 0; i < myArr.size(); i++)
			System.out.print(myArr.get(i).getName() + "\t");
		System.out.println();
		
		for(int i = 0; i < ras.length()/rowLength; i++)
			if(myList[i].equals(1)){
				for(int j = 0; j < myArr.size(); j++){
					System.out.print(myArr.get(j).getBinary(ras, i, rowLength).toString() + "\t");
				}
				System.out.println();
			}
		
		storeArr.clear();
	}
	
	/**
	 * Get user specified field
	 * @param token
	 * @throws WoodsException
	 */
	private void getFieldType(String token) throws WoodsException {
		for(Datatype dt : myArr){
			if(dt.getName().equalsIgnoreCase(token.trim()))
				dataTypeField = dt;
		}
	}
	
	/**Get user's relop expression from where clause
	 * 
	 * @param expression
	 * @return
	 * @throws WoodsException
	 */
	public String whereRelop(String expression) throws WoodsException{
		
		if(expression.contains("<="))
			return "<=";
		else if(expression.contains(">="))
			return ">=";
		else if(expression.contains("!="))
			return "!=";
		else if(expression.contains("="))
			return "=";
		else if(expression.contains("<"))
			return "<";
		else if(expression.contains(">"))
			return ">";
		else
			throw new WoodsException("Incorrect comparison operator entered.");
	}
	
	/**Update every value in the specified field
	 * 
	 * @param fieldName
	 * @param value
	 * @throws WoodsException
	 * @throws IOException
	 */
	public void updateField(String fieldName, String value) throws WoodsException, IOException {
		try{
			getFieldType(fieldName);
			for(int i = 0; i < ras.length()/rowLength; i++)
				for(int j = 0; j < myArr.size(); j++){
					if(myArr.get(j).equals(dataTypeField)){
						ras.seek(i*rowLength+dataTypeField.getPosition());
						testInsertFormat(j, value);
						myArr.get(j).binaryWrite(value.replace('\'', ' ').trim(), ras);
					}
				}
		} catch (NumberFormatException n){
			resetLength(ras.length());
			throw new WoodsException("Fields types do not match. Please re-insert data.");
		} 
	}
	
	/**Update specified rows in the field given
	 * 
	 * @param fieldName
	 * @param value
	 * @throws IOException
	 * @throws WoodsException
	 */
	public void updateSpecificField(String fieldName, String value) throws IOException, WoodsException {
		Object[] myList = storeArr.toArray();
		
		try{
			getFieldType(fieldName);
			for(int i = 0; i < ras.length()/rowLength; i++)
				if(myList[i].equals(1)){
					for(int j = 0; j < myArr.size(); j++){
						if(myArr.get(j).equals(dataTypeField)){
							ras.seek(i*rowLength+dataTypeField.getPosition());
							testInsertFormat(j, value);
							myArr.get(j).binaryWrite(value.replace('\'', ' ').trim(), ras);
						}
					}
				}
			
			storeArr.clear();
		} catch (NumberFormatException n){
			resetLength(ras.length());
			throw new WoodsException("Fields types do not match. Please re-insert data.");
		} 
	}

	/**
	 * Delete the table's information
	 * @throws WoodsException
	 */
	public void deleteAllInformation() throws WoodsException {
		try {
			ras.setLength(0);
		} catch (IOException e) {
			throw new WoodsException("Unable to delete this table's information.");
		}
	}
	
	/**
	 * Delete specified rows from table;
	 * @throws IOException
	 */
	public void deleteSpecifiedRows() throws IOException {
		Object[] myList = storeArr.toArray();
		
		for(int i = 0; i < ras.length()/rowLength; i++)
			if(myList[i].equals(1)){
				for(int j = 0; j < myArr.size(); j++){
					ras.seek(i*rowLength);
					ras.writeBoolean(false);
				}
			}
	}
}
