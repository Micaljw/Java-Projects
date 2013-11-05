package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RealValue extends AbstractValues<RealValue>{

	private final String userStr;
	
	public RealValue(String value){
		this.userStr = value;
	}

	@Override
	public int compareTo(RealValue o) {
		return userStr.compareTo(o.userStr);
	}

	@Override
	public String toString() {
		return userStr;
	}
}
