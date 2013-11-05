package five;

import java.io.RandomAccessFile;

import two.datatype.Datatype;

public class MatcherFactory {

	public static IMatcher getMatcher(RandomAccessFile raf, Datatype field, String value, String relop, long rowLength){
		
		if(value == null)
			return new NoMatch();
		else
			return new Match(raf, field, value, relop, rowLength);
	}
}
