package two.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import five.values.AbstractValues;

import one.WoodsException;

/**
 * Creates a data type.
 */
public abstract class Datatype{
	
	protected String name;
	protected String type;
	protected long rowPosition;
	
	/**
	 * Instantiates a new datatype.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public Datatype(String name, String type, long rowPosition){
		this.name = name;
		this.type = type;
		this.rowPosition = rowPosition;
	}
	
	
	/**
	 * Change the data type to a string
	 */
	public String toString(){
		return type + " " + name;
	}
	
	/**
	 * Get the name of the datatype
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the data type
	 */
	public String getType(){
		return type;
	}
	
	public long getPosition(){
		return rowPosition;
	}
	
	/**
	 * Return the field name and type
	 *
	 * @return the string
	 */
	public String toXMLString(){
		return "<fieldname>" + name + "</fieldname>" + "<fieldtype>" + type + "</fieldtype>";
	}

	/**
	 * Write corresponding data type values to binary file
	 *
	 * @param input the input
	 * @param ras the ras
	 * @throws WoodsException the woods exception
	 * @throws IOException 
	 */
	public abstract void binaryWrite(String input, RandomAccessFile raf) throws WoodsException, IOException;
	
	/**
	 * Read corresponding data type values from binary file
	 *
	 * @param ras the ras
	 * @return the binary
	 * @throws WoodsException the woods exception
	 * @throws IOException 
	 */
	public abstract AbstractValues getBinary(RandomAccessFile raf, int row, long rowLength)throws IOException;
	
	public abstract AbstractValues getValue(String value);
	
}
