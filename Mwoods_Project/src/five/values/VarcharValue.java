package five.values;

public class VarcharValue extends AbstractValues<VarcharValue>{
	
	private String userValue;
	
	public VarcharValue(String value){
		this.userValue = value;
	}

	@Override
	public int compareTo(VarcharValue o) {
		return userValue.compareTo(o.userValue);
	}

	@Override
	public String toString() {
		return userValue;
	}
}
