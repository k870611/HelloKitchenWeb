package EntityComponents;

public class Token implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String PhoneToken;
	
	public Token() {
		super();
	}
	
	public Token(String userId, String phoneToken) {
		super();
		this.userId = userId;
		PhoneToken = phoneToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneToken() {
		return PhoneToken;
	}

	public void setPhoneToken(String phoneToken) {
		PhoneToken = phoneToken;
	}
	
	
	
	
}
