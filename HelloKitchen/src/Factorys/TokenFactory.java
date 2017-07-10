package Factorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import AbstractAncestor.AccountManager;
import EntityComponents.Token;

public class TokenFactory extends AccountManager implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private HashMap<String, Token> hereDataSet = new HashMap<String, Token>();
	
	public TokenFactory() {

	}
	
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		conn = super.getSQLConnection();
		String sql = "insert into ttoken values(? ,?)";
		Token t = (Token) o;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, t.getUserId());
		ps.setNString(2, t.getPhoneToken());
		boolean isinsert = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isinsert;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		// -- 考慮參數可以給字串? 大小寫敏感問題(case_sensitive)
		try {
			conn = super.getSQLConnection();
			Token t = (Token) o;
			String sql = "delete from ttoken where userId = ?";
			PreparedStatement myPS = conn.prepareStatement(sql);
			myPS.setString(1, t.getUserId());
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
		// TODO Auto-generated method stub
		Token old = (Token) oldDataObj;
		Token update = (Token) updateDataObj;
		
		if (old.getPhoneToken().equalsIgnoreCase(update.getPhoneToken())) {
			return -1;
		}
		
		try {
			conn = super.getSQLConnection();
			String sql = "update ttoken SET PhoneToken = ?  where userId = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, update.getPhoneToken());
			ps.setNString(2, update.getUserId());
			
			int effectrow = ps.executeUpdate();
			ps.close();
			super.destorySQLConnection(conn);
			remove(old);
			add(update);
			return effectrow;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		String id_pk = "";
		if (o instanceof Token){
			Token t = (Token) o;
			id_pk = t.getUserId();
		}
		if (o instanceof String) {
			id_pk = (String) o;
		}
		return this.hereDataSet.containsKey(id_pk.toLowerCase());
	}

	@Override
	public Object sreachByPK(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			String key = (String) o;
			return this.hereDataSet.get(key.toLowerCase());
		} else {
			return null;
		}
	}

	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<Token> dataset = new ArrayList<Token>();
		for (String key : this.hereDataSet.keySet()) {
			dataset.add(this.hereDataSet.get(key));
		}
		return dataset;
	}

	@Override
	public boolean add(Object o) {
		Token t = (Token) o;// -- 不保險寫法--
		if (isExistByPK(o)) {
			System.out.println("目前工廠資料集有該筆資料");
			return false;
		} else {
			this.hereDataSet.put(t.getUserId().toLowerCase(), t);
			System.out.println("寫入目前工廠資料集");
			return true;
		}

	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From ttoken"; // --偷懶寫法
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereDataSet.clear(); // -- 洗白
			// --- 重新注入資料 從現行資料庫 --
			while (rs.next()) {
				// public Member(String member_id, String member_name, String
				// member_email, String member_password, String member_tel,
				// String member_fb)
				Token tempT = new Token(rs.getNString("userId"), rs.getNString("PhoneToken"));
				this.hereDataSet.put(tempT.getUserId().toString(), tempT);
				count++;
			}
			rs.close();
			super.destorySQLConnection(conn);
			System.out.println("使用者Token工廠資料重洗 ==> 筆數 " + count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		// ---- 需要一體操作? 不存在就新增 有就更新?
		Token t = (Token) o;
		if (this.isExistByPK(o)) {
			// -- 有 -- 更新 --
			this.hereDataSet.replace(t.getUserId().toLowerCase(), t);
			System.out.println("使用者Token更新完成");
		} else {
			// --- 沒有 --
			System.out.println("沒有該筆資料更新");
		}
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			String id_pk = " ";
			if (o instanceof Token) {
				Token tempt = (Token) o;
				id_pk = tempt.getUserId().toLowerCase();
				this.hereDataSet.remove(id_pk);
				return true;
			}
			if (o instanceof String) {
				id_pk = ((String) o).toLowerCase();
				this.hereDataSet.remove(id_pk);
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		
	}

}
