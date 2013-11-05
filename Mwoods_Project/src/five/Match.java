package five;

import java.io.IOException;
import java.io.RandomAccessFile;

import two.datatype.Datatype;

public class Match implements IMatcher {
	
	private RandomAccessFile raf;
	private Datatype field;
	private String value;
	private String relop;
	private long rowLength;

	public Match(RandomAccessFile raf, Datatype field, String value, String relop, long rowLength) {
		this.raf = raf;
		this.field = field;
		this.value = value;
		this.relop = relop;
		this.rowLength = rowLength;
	}

	@Override
	public boolean isMatch(int row) throws IOException {
		long myValue = field.getValue(value).compareTo(field.getBinary(raf, row, rowLength));
		
		return relopCheck(myValue);
	}
	
	public boolean relopCheck(long myValue) {
	
		if((myValue == 0 && relop.equals("=")) || (myValue != 0 && relop.equals("!=")) ||
			(myValue > 0 && relop.equals("<")) || (myValue < 0 && relop.equals(">")) ||
			(myValue >= 0 && relop.equals("<=")) || (myValue <= 0 && relop.equals(">="))) 
			return true;
		
		return false;
	}
}
