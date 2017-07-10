package Modules;

public class CompositeKey implements java.io.Serializable{
//--- field-----
	private String mainKey;
	private String supportKey;
	
	
//-- construct ----	
	public CompositeKey(String mainKey, String supportKey) {
		super();
		this.setMainKey(mainKey);
		this.setSupportKey(supportKey);
	}
	public String getMainKey() {
		return mainKey;
	}
	public void setMainKey(String mainKey) {
		this.mainKey = mainKey.toLowerCase();
	}
	public String getSupportKey() {
		return supportKey;
	}
	public void setSupportKey(String supportKey) {
		this.supportKey = supportKey.toLowerCase();
	}
	@Override
	public String toString() {
		return "CompositeKey [mainKey=" + mainKey + ", supportKey=" + supportKey + "]";
	}
	
	public boolean keyFullMatch(String mainkey,String supportkey){
		if(this.mainKey.equals(mainkey) && this.supportKey.equals(supportkey)){
			return true;
		}
		return false;
	}
	public boolean keyPartiallyMatch(String key){
		if(this.mainKey.equals(key) || this.supportKey.equals(key)){
			return true;
		}
		return false;
	}
	public boolean mainKeyMatch(String mainkey){
		if(this.mainKey.equals(mainkey)){
			return true;
		}
		return false;
	}
	public boolean supKeyMatch(String supkey){
		if(this.supportKey.equals(supkey)){
			return true;
		}
		return false;
	}
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof CompositeKey){
			CompositeKey compareObj = (CompositeKey)anObject;
			boolean main = this.getMainKey().equalsIgnoreCase(compareObj.getMainKey());
			boolean sup = this.getSupportKey().equalsIgnoreCase(compareObj.getSupportKey());
		return (main && sup);
		}
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int m = this.mainKey.length();
		int s = this.supportKey.length();
		return m+s;
	}
	
}//-- class end
