package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;

public class IntValue extends AbstractValues<IntValue>{

	private final String userStr;
	
	public IntValue(String value){
		this.userStr = value;
	}

	@Override
	public int compareTo(IntValue o) {
		return userStr.compareTo(o.userStr);
	}

	@Override
	public String toString() {
		return userStr;
	}
}
