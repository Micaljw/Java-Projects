package five.values;

import java.io.IOException;
import java.io.RandomAccessFile;

public class CharValue extends AbstractValues<CharValue>{

	private final String value;
	
	public CharValue(String userChar){
		this.value = userChar;
	}
	
	@Override
	public int compareTo(CharValue o) {
		return value.compareTo(o.value);
	}

	@Override
	public String toString() {
		return value;
	}
}
