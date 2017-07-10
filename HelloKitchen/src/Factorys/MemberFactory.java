package Factorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.AccountManager;
import AbstractAncestor.FactorAncestor;
import EntityComponents.Member;
import EntityComponents.Recipe;
import Modules.UpdateSet;

public class MemberFactory extends AccountManager implements java.io.Serializable {

	private HashMap<String, Member> hereDataSet = new HashMap<String, Member>();
	private Connection conn;
    private String JsonString;
	// --------- construct -------
	public MemberFactory() {

	}

	// ---------Override Method------------
	/* 會員資料加入 回寫資料庫 -- */
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * member_id` VARCHAR(100) NOT NULL, `member_name` VARCHAR(50) NOT NULL,
		 * `member_email` VARCHAR(100) NOT NULL, `member_password` VARCHAR(50)
		 * NOT NULL, `member_tel` VARCHAR(15) NULL DEFAULT NULL, `member_fb`
		 * VARCHAR(50) NULL DEFAULT NULL,
		 */

		Member m = (Member) o;
		conn = super.getSQLConnection();
		String sql = "insert into tmember values(?,?,?,?,?,?)";
		PreparedStatement myPS = conn.prepareStatement(sql);
		myPS.setNString(1, m.getMember_id());
		myPS.setNString(2, m.getMember_name());
		myPS.setNString(3, m.getMember_email());
		myPS.setNString(4, m.getMember_password());
		myPS.setNString(5, m.getMember_tel());
		myPS.setNString(6, m.getMember_fb());
		boolean isup = myPS.execute();
		myPS.close();
		super.destorySQLConnection(conn);
		System.out.println("會員新增成功");
		return isup;

	}

	/* 刪除特定會員資料 */
	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		// -- 考慮參數可以給字串? 大小寫敏感問題(case_sensitive)
		try {
			conn = super.getSQLConnection();
			Member m = (Member) o;
			String sql = "delete from tmember where member_id = ?";
			PreparedStatement myPS = conn.prepareStatement(sql);
			myPS.setString(1, m.getMember_id());
			int affectrow = myPS.executeUpdate();
			myPS.close();
			super.destorySQLConnection(conn);
			return affectrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int Update(Object oldDataObj, Object updateDataObj) {
		Member old = (Member) oldDataObj;
		Member update = (Member) updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		if ((upset == null) || (upset.size() <= 0)) {
			return -1;
		}
		try {
			conn = super.getSQLConnection();
			String sql = super.getUpdateString("update tmember Set ", upset) + " where member_id = ?;";
			System.out.println("sql update PreparedStatement : " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			// --------------------------------
			for (int index = 0; index < upset.size(); index++) {
				UpdateSet unit = upset.get(index);
				switch (unit.getDataType()) {
				case "String":
					ps.setNString(count, (String) unit.getUpdateValue());
					break;
				/*
				 * case "boolean": ps.setBoolean(count, (boolean)
				 * unit.getUpdateValue()); break;
				 */
				default: // --- 失敗退回
					return -2;
				}
				count++;
			} // --- loop end
			ps.setNString(count, old.getMember_id());// -- where constraint
			int effectrow = ps.executeUpdate();
			ps.close();
			super.destorySQLConnection(conn);
			return effectrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/* 測定資料是否已在 --PK依據 */
	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		// -- 考慮過後--- 來個instance分流
		String id_pk = "";
		if (o instanceof Member) {
			Member tempm = (Member) o;
			id_pk = tempm.getMember_id().toLowerCase();
		}
		if (o instanceof String) {
			id_pk = ((String) o).toLowerCase();
		}
		// -- 決定了--- 都轉小寫--
		boolean isExsit = this.hereDataSet.containsKey(id_pk);
		return isExsit;
	}

	/* 尋找資料 PK依據 */
	@Override
	public Member sreachByPK(Object o) {
		// TODO Auto-generated method stub
		String id_pk = (String) o;
		if (this.hereDataSet.containsKey(id_pk.toLowerCase())) {
			// ----- 有 -----
			System.out.println("取得該筆資料");
			return this.hereDataSet.get(id_pk.toLowerCase());

		} else {
			System.out.println("沒有該筆資料");
			return null;
		}

	}

	/* 回傳 ArrayList<Member> 資料型態的資料集 */
	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<Member> dataset = new ArrayList<Member>();
		for (String key : this.hereDataSet.keySet()) {
			dataset.add(this.hereDataSet.get(key));
		}
		return dataset;
	}

	/* 會員資料加入資料集-- 沒有回寫進資料庫 */
	@Override
	public boolean add(Object o) {
		Member m = (Member) o;// -- 不保險寫法--
		if (isExistByPK(o)) {
			System.out.println("目前工廠資料集有該筆資料");
			return false;
		} else {
			this.hereDataSet.put(m.getMember_id().toLowerCase(), m);
			System.out.println("寫入目前工廠資料集");
			return true;
		}

	}

	/* 從資料庫更新過來工廠 大招般的存在(工廠資料重洗) */
	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From tmember"; // --偷懶寫法
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereDataSet.clear(); // -- 洗白
			// --- 重新注入資料 從現行資料庫 --
			while (rs.next()) {
				// public Member(String member_id, String member_name, String
				// member_email, String member_password, String member_tel,
				// String member_fb)
				Member tempM = new Member(rs.getNString("member_id"), rs.getNString("member_name"),
						rs.getNString("member_email"), rs.getNString("member_password"), rs.getNString("member_tel"),
						rs.getNString("member_fb"));
				this.hereDataSet.put(tempM.getMember_id().toString(), tempM);
				count++;
			}
			rs.close();
			super.destorySQLConnection(conn);
			System.out.println("會員工廠資料重洗 ==> 筆數 " + count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 會員特定一筆資料更新 沒有回寫進資料庫 */
	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		// ---- 需要一體操作? 不存在就新增 有就更新?
		Member m = (Member) o;
		if (this.isExistByPK(o)) {
			// -- 有 -- 更新 --
			this.hereDataSet.replace(m.getMember_id().toLowerCase(), m);
			System.out.println("會員更新完成");
		} else {
			// --- 沒有 --
			System.out.println("沒有該筆資料更新");
		}
	}
	// ---- other method ------

	/**
	 * @param account
	 *            會員帳號
	 * @param password
	 *            會員密碼
	 * @return 是否成功驗證 (boolean)
	 */
	public boolean memberLoginByPassword(String account, String password) {
		Member loginmember = this.sreachByPK(account);
		if (loginmember != null) {
			if (loginmember.getMember_password().equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 創建前先用 請先用 isExistByPK(Object o) 方法確定是否有重複帳號
	 * 
	 * @param newMember
	 *            新會員資料物件
	 * @throws SQLException
	 */
	public void establishMemberByPassword(Member newMember) throws SQLException {
		this.Insert(newMember);
		this.add(newMember);
	}

	/**
	 * 直接檢查該帳號是否已被使用
	 * 
	 * @param account
	 *            新會員帳號登入資料
	 * @throws JSONException
	 * @return T/F
	 */
	public JSONObject getCheckAccountResult(String account) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonibj = new JSONObject();
		boolean isexit = this.isExistByPK(account);
		// --- Email pattern --
		boolean p = Pattern.matches("[a-zA-Z].*\\@[a-zA-Z]{1,7}\\.[a-zA-Z]{1,4}\\.?([a-zA-Z]{1,4})?", account);
		boolean mix = (isexit || !p);
		try {
			jsonibj.put("myStatus", mix);
			jsonArray.put(jsonibj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonibj;
	}
    /* 帳號是否存在查核  */
	public JSONObject JSONCheckMemberID(String Email){
		JSONObject jsonibj = new JSONObject();
		boolean result = this.isExistByPK(Email);
		try {
			jsonibj.put("checkReslut", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonibj;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			String id_pk = " ";
			if (o instanceof Member) {
				Member tempm = (Member) o;
				id_pk = tempm.getMember_id().toLowerCase();
				Member mb =this.hereDataSet.remove(id_pk);
				System.out.println("會員物件從網站移除byMember=>"+mb);
				return !(mb==null);
			}
			if (o instanceof String) {
				id_pk = ((String) o).toLowerCase();
				Member mb = this.hereDataSet.remove(id_pk);
				System.out.println("會員物件從網站移除byString_Mid=>"+mb);
				return !(mb==null);
			}
			return false;
		} else {
			return false;
		}
	}
	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("Member inject JSON "+jsonString);
		this.JsonString = jsonString;
		
	}
 
	
}// -- class end
