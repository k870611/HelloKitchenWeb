package EntityComponents;

import java.util.ArrayList;

import Modules.UpdateSet;

public class Member implements java.io.Serializable {
	/*
	 * foods`.`tmember` ( `member_id` VARCHAR(100) NOT NULL, `member_name`
	 * VARCHAR(50) NOT NULL, `member_email` VARCHAR(100) NOT NULL,
	 * `member_password` VARCHAR(50) NOT NULL, `member_tel` VARCHAR(15) NULL
	 * DEFAULT NULL, `member_fb` VARCHAR(50) NULL DEFAULT NULL, PRIMARY KEY
	 * (`member_id`))
	 */
	// --------------------------
	private String member_id; // -- pk
	private String member_name, member_email, member_password;
	private String member_tel, member_fb;
	private ArrayList<UpdateSet> upset = new ArrayList<UpdateSet>();

	// ---- construct -----
	public Member() {

	}

	/**
	 * 創建會員物件
	 * 
	 * @param member_id
	 *            會員帳號
	 * @param member_name
	 *            會員名稱
	 * @param member_email
	 *            會員電子帳號
	 * @param member_password
	 *            會員密碼
	 * @param member_tel
	 *            會員電話
	 * @param member_fb
	 *            會員臉書
	 */
	public Member(String member_id, String member_name, String member_email, String member_password, String member_tel,
			String member_fb) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_password = member_password;
		this.member_tel = member_tel;
		this.member_fb = member_fb;
	}

	// -----------------------
	/* 複寫 toString */

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_name=" + member_name + ", member_email=" + member_email
				+ ", member_password=" + member_password + ", member_tel=" + member_tel + ", member_fb=" + member_fb
				+ "]";
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		if (member_name == null) {
			return "noData";
		} else {
			return member_name;
		}
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_password() {
		return member_password;
	}

	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}

	public String getMember_tel() {
		return member_tel;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public String getMember_fb() {
		return member_fb;
	}

	public void setMember_fb(String member_fb) {
		this.member_fb = member_fb;
	}

	// -----------------
	/**
	 * @param updateObj
	 *            Member 物件
	 * @return 比較異動集
	 */
	public ArrayList<UpdateSet> getUpdateSet(Member updateObj) {
		/*
		 * private String member_id; //-- pk private String
		 * member_name,member_email,member_password; private String
		 * member_tel,member_fb;
		 */
		Member m = updateObj;
		if (!this.getMember_name().equals(m.getMember_name())) {
			this.upset.add(new UpdateSet("member_name", m.getMember_name(), "String"));
		}
		if (!this.getMember_email().equals(m.getMember_email())) {
			this.upset.add(new UpdateSet("member_email", m.getMember_email(), "String"));
		}
		if (!this.getMember_password().equals(m.getMember_password())) {
			this.upset.add(new UpdateSet("member_password", m.getMember_password(), "String"));
		}
		if (!this.getMember_tel().equals(m.getMember_tel())) {
			this.upset.add(new UpdateSet("member_tel", m.getMember_tel(), "String"));
		}
		if (!this.getMember_fb().equals(m.getMember_fb())) {
			this.upset.add(new UpdateSet("member_fb", m.getMember_fb(), "String"));
		}

		return upset;
	}

}// -- class end
