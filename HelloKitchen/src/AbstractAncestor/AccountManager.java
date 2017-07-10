package AbstractAncestor;

public abstract class AccountManager extends FactorAncestor {

	public String pwdGenerator() {
		int pwd = (int) (Math.random() * 100);
		String pwdStr = String.format("%03d%03d", pwd, pwd);
		return pwdStr;
	}

}
