package Factorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.AccountManager;
import AbstractAncestor.FactorAncestor;
import EntityComponents.Company;
import EntityComponents.Recipe;
import Modules.UpdateSet;
import Utility.GeneralVarName;
import Utility.ProcessDate;

public class CompanyFactory extends AccountManager implements java.io.Serializable {

	// --- field---
	private HashMap<String, Company> hereCompanyData = new HashMap<String, Company>();
	private Connection conn;
	private String JsonString;

	// ---- construct -----
	public CompanyFactory() {

	}

	// ------ Override method -----
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		conn = super.getSQLConnection();
		Company c = (Company) o;
		/*
		 * company_id` VARCHAR(50) NOT NULL, `company_name` VARCHAR(50) NOT
		 * NULL, `company_logo` VARCHAR(100) NULL DEFAULT NULL, `company_cover`
		 * VARCHAR(100) NULL DEFAULT NULL, `company_intro` VARCHAR(8000) NOT
		 * NULL, `company_address` VARCHAR(100) NOT NULL, `company_tel`
		 * VARCHAR(50) NOT NULL, `company_email` VARCHAR(100) NOT NULL,
		 * `company_owner` VARCHAR(50) NOT NULL, `company_password` VARCHAR(45)
		 * NOT NULL, `company_status` BIT(1) NULL DEFAULT 0,
		 */
		String sql = "insert into tcompany values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, c.getCompany_id());
		ps.setNString(2, c.getCompany_name());
		ps.setNString(3, c.getCompany_logo());
		ps.setNString(4, c.getCompany_cover());
		ps.setNString(5, c.getCompany_intro());
		ps.setNString(6, c.getCompany_address());
		ps.setNString(7, c.getCompany_tel());
		ps.setNString(8, c.getCompany_email());
		ps.setNString(9, c.getCompany_owner());
		ps.setNString(10, c.getCompany_password());
		ps.setBoolean(11, c.isCompany_status());
		boolean isexcute = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isexcute;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			Company c = (Company) o;
			String sql = "delete from tcompany where company_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, c.getCompany_id());
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

	@Override
	public int Update(Object oldDataObj, Object updateDataObj) {
		// TODO Auto-generated method stub
		Company old = (Company) oldDataObj;
		Company update = (Company) updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		if ((upset == null) || (upset.size() <= 0)) {
			return -1;
		}
		try {
			conn = super.getSQLConnection();
			String sql = super.getUpdateString("update tcompany Set ", upset) + " where company_id = ?;";
			System.out.println("sql update PreparedStatement : " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			// --------------------------------
			for (int index = 0; index < upset.size(); index++) {
				UpdateSet unit = upset.get(index);
				switch (unit.getDataType()) {
				case "String":
					ps.setNString(count, (String) unit.getUpdateValue());
					break;
				case "boolean":
					ps.setBoolean(count, (boolean) unit.getUpdateValue());
					break;
				default: // --- 失敗退回
					return -2;
				}
				count++;
			} // --- loop end
			ps.setNString(count, old.getCompany_id());// -- where constraint
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

	/* 測定資料是否存在於現在的資料集合 */
	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		String id_pk = "";
		if (o instanceof Company) {
			id_pk = ((Company) o).getCompany_id();
		}
		if (o instanceof String) {
			id_pk = (String) o;
		}
		return this.hereCompanyData.containsKey(id_pk.toLowerCase());
	}

	/* 尋找資料 依據 PK值 String */
	@Override
	public Object sreachByPK(Object o) {
		// TODO Auto-generated method stub
		String id_pk = ((String) o).toLowerCase(); // -- 非保險寫法
		if (this.isExistByPK(o)) {
			// -- 有 --
			System.out.println("搜尋到該筆公司資料");
			return this.hereCompanyData.get(id_pk);
		} else {
			// -- 沒有 --
			System.out.println("沒有該筆公司資料");
			return null;
		}
	}

	/* 回傳 ArrList<Company> dataStructure */
	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<Company> dataStructure = new ArrayList<Company>();
		for (String key : this.hereCompanyData.keySet()) {
			dataStructure.add(this.hereCompanyData.get(key));
		}
		return dataStructure;
	}

	/* 加入公司資料 沒有回寫到資料庫 */
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		Company c = (Company) o;
		if (this.isExistByPK(o)) {
			System.out.println("該公司資料已存在");
			return false;
		} else {
			this.hereCompanyData.put(c.getCompany_id().toLowerCase(), c);
			return true;
		}

	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From tcompany";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereCompanyData.clear();
			// --- 資料重新注入 --
			while (rs.next()) {
				/*
				 * Company(String company_id, String company_name, String
				 * company_logo, String company_cover, String company_intro,
				 * String company_address, String company_tel, String
				 * company_email, String company_owner, String company_password,
				 * boolean company_status)
				 */
				Company c = new Company(rs.getNString("company_id"), rs.getNString("company_name"),
						rs.getNString("company_logo"), rs.getNString("company_cover"), rs.getNString("company_intro"),
						rs.getNString("company_address"), rs.getNString("company_tel"), rs.getNString("company_email"),
						rs.getNString("company_owner"), rs.getNString("company_password"),
						rs.getBoolean("company_status"));
				this.hereCompanyData.put(c.getCompany_id().toLowerCase(), c);
			}
			rs.close();
			super.destorySQLConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 更新公司資料 無回寫資料庫 */
	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			Company c = (Company) o;
			this.hereCompanyData.replace(c.getCompany_id().toLowerCase(), c);
		} else {

		}
	}

	@Override
	public boolean remove(Object o) {
		if (this.isExistByPK(o)) {
			String pk = " ";
			if (o instanceof String) {
				pk = (String) o;
				Company cp = this.hereCompanyData.remove(pk.toLowerCase());
				System.out.println("公司物件從網站移除byString_Cid==>"+cp);
				return !(cp==null);
			}
			if (o instanceof Company) {
				pk = ((Company) o).getCompany_id();
				Company cp = this.hereCompanyData.remove(pk.toLowerCase());
				System.out.println("公司物件從網站移除byCompany==>"+cp);
				return !(cp==null);
			}
			return false;
		} else {
			return false;
		}

	}

	/* 轉換 */
	public String getAllParseToJSON() {
		JSONArray jsonArray = new JSONArray();

		for (String key : this.hereCompanyData.keySet()) {
			Company c = this.hereCompanyData.get(key);
			JSONObject jsobj = new JSONObject();
			try {
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_id, c.getCompany_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_name, c.getCompany_name());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_logo, c.getCompany_logo());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_cover, c.getCompany_cover());
				jsobj.put(GeneralVarName.Android_JSON_Key_Cover_intro, c.getCompany_intro());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_address, c.getCompany_address());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_tel, c.getCompany_tel());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_email, c.getCompany_email());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_owner, c.getCompany_owner());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_password, c.getCompany_password());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_status, c.isCompany_status());
				jsonArray.put(jsobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // --end for
		return jsonArray.toString();
	}
	// --- 取得 資料Size--

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("injection JSON: " + jsonString);
		this.JsonString = jsonString;

	}

	// -- other method ---
	/* 帳號是否存在查核 */
	public JSONObject JSONCheckCompanyID(String CId) {
		JSONObject jsonibj = new JSONObject();
		boolean result = this.isExistByPK(CId);
		try {
			jsonibj.put("checkReslut", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonibj;
	}
	/* 公司帳號登入 */
    public boolean companyLogin(String CId,String pwd){
    	
    	if(this.isExistByPK(CId)){
    		Company c = this.hereCompanyData.get(CId.toLowerCase());
    		if(c.getCompany_password().equals(pwd)){
    			return true;
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
    	
    }
}
