package Modules;

public class SessionStamp {
	private String loginAccount;
	private boolean loginStatus = false;
	// --------------------------------

	/**
	 * @param loginAccount
	 *            登入帳號
	 */
	public SessionStamp(String loginAccount) {
		super();
		this.setLoginAccount(loginAccount);
	}

	// -----------------------------------
	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount.toLowerCase();
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	
	
}// --- class end
