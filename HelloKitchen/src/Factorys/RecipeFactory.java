package Factorys;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import AbstractAncestor.FactorAncestor;
import EntityComponents.Recipe;
import Modules.UpdateSet;
import Utility.GeneralVarName;
import Utility.ProcessDate;

public class RecipeFactory extends FactorAncestor implements java.io.Serializable, Runnable {
	// --- field ----
	private Connection conn;
	private HashMap<String, Recipe> hereDataSet = new HashMap<String, Recipe>();
	private JSONArray jsonArray;
	private boolean getAllFlag = false;
	private String JsonString;

	// ---- construct -----------
	public RecipeFactory() {

	}

	// ------- Override Method --------
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		conn = super.getSQLConnection();
		/*
		 * `recipe_id` VARCHAR(50) NOT NULL, `recipe_name` VARCHAR(50) NOT NULL,
		 * `member_id` VARCHAR(100) NOT NULL, `upload_date` DATE NOT NULL,
		 * `recipe_status` BIT(1) NULL DEFAULT b'1', `recipe_amount` VARCHAR(15)
		 * NOT NULL, `recipe_cooktime` VARCHAR(15) NOT NULL, `recipe_picture`
		 * VARCHAR(100) NULL DEFAULT NULL, recipe_detail` varchar(5000)
		 */
		String sql = "insert into trecipe values(?,?,?,?,?,?,?,?,?)";
		Recipe r = (Recipe) o;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, r.getRecipe_id());
		ps.setNString(2, r.getRecipe_name());
		ps.setNString(3, r.getMember_id());
		ps.setDate(4, r.getUpload_date());
		ps.setBoolean(5, r.isRecipe_status());
		ps.setNString(6, r.getRecipe_amount());
		ps.setNString(7, r.getRecipe_cooktime());
		ps.setNString(8, r.getRecipe_picture());
		ps.setNString(9, r.getRecipe_detail());
		boolean isinsert = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isinsert;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			Recipe r = (Recipe) o;
			String sql = "Delete From trecipe where recipe_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getRecipe_id());
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
		Recipe old = (Recipe) oldDataObj;
		Recipe update = (Recipe) updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		// ------------ 資料無變化 -----
		if ((upset == null) || (upset.size() <= 0)) {
			return -1;
		}
		// ------------------------
		 try {
		 conn = super.getSQLConnection();
		String sql = super.getUpdateString("update trecipe Set ", upset) + " where recipe_id = ?;";
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
				case "date":
					ps.setDate(count, (Date) unit.getUpdateValue());
					break;
				default: // --- 失敗退回 
					return -2;
				}
				count++;
			} // --- loop end
			ps.setNString(count, old.getRecipe_id());// -- where constraint
		
			int effectrow = ps.executeUpdate();
			ps.close();
			super.destorySQLConnection(conn);
			return effectrow;
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		String id_pk = "";
		if (o instanceof Recipe) {
			Recipe r = (Recipe) o;
			id_pk = r.getRecipe_id();
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
	public synchronized Object getAll() {
		// TODO Auto-generated method stub
		// ---- 同步化區塊 ------
		while (this.isGetAllFlag()) {
			try {
				new Thread().sleep(1000);
				System.out.println("等待更新");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Recipe> allData = new ArrayList<Recipe>();
		for (String key : this.hereDataSet.keySet()) {
			allData.add(this.hereDataSet.get(key));
		}

		return allData;
	}

	/* 工廠加入資料 沒有回寫資料庫 */
	@Override
	public synchronized boolean add(Object o) {
		// TODO Auto-generated method stub
		// -- 同步旗標--
		this.setGetAllFlag(true);
		// -------------------------
		if (this.isExistByPK(o)) {
			System.out.println("該筆食譜資料已存在");
			return false;
		} else {
			System.out.println("before add count==> " + hereDataSet.size());
			Recipe r = (Recipe) o;
			this.hereDataSet.put(r.getRecipe_id().toLowerCase(), r);
			System.out.println("該筆食譜資料加入工廠 ss");
			System.out.println("RecipeFactory add==>" + r);
			System.out.println("After add count==> " + hereDataSet.size());
			this.setGetAllFlag(false);
			return true;
		}
	}

	/* 食譜工廠資料重洗 */
	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From trecipe";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereDataSet.clear();
			/*
			 * public Recipe(String recipe_id, String recipe_name, String
			 * member_id, String recipe_amount, String recipe_cooktime, String
			 * recipe_picture, boolean recipe_status, Date upload_date)
			 * recipe_detail` varchar(5000) NOT NULL,
			 */
			while (rs.next()) {
				Recipe r = new Recipe(rs.getNString("recipe_id"), rs.getNString("recipe_name"),
						rs.getNString("member_id"), rs.getNString("recipe_amount"), rs.getNString("recipe_cooktime"),
						rs.getNString("recipe_picture"), rs.getBoolean("recipe_status"), rs.getDate("upload_date"),
						rs.getString("recipe_detail"));
				this.hereDataSet.put(r.getRecipe_id().toLowerCase(), r);
			}
			rs.close();
			super.destorySQLConnection(conn);
			System.out.println("從資料庫重新更新注入資料到食譜工廠");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 工廠更新資料 沒有回寫資料庫 */
	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		Recipe r = (Recipe) o;
		if (this.isExistByPK(r)) {
			this.hereDataSet.replace(r.getRecipe_id().toLowerCase(), r);
			System.out.print("食譜工廠資料更新");
		}

	}

	// ----------------------------------------
	public String getAllParseToJSON(String memberid) {
		jsonArray = new JSONArray();
		for (String key : this.hereDataSet.keySet()) {
			Recipe r = this.hereDataSet.get(key);
			if (r.getMember_id().equalsIgnoreCase(memberid)) {
				JSONObject jsobj = new JSONObject();
				try {
					jsobj.put(GeneralVarName.Android_JSON_Key_Member_id, r.getMember_id());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_amount, r.getRecipe_amount());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_cooktime, r.getRecipe_cooktime());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_detail, r.getRecipe_detail());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_id, r.getRecipe_id());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_name, r.getRecipe_name());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_picture, r.getRecipe_picture());
					jsobj.put(GeneralVarName.Android_JSON_Key_Upload_date,
							ProcessDate.DateParseToString(r.getUpload_date()));
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_status, r.isRecipe_status());
					jsonArray.put(jsobj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} // --end for
		return jsonArray.toString();
	}

	/* 取得有開放的食譜 */
	public String getAllParseToJSON() {
		jsonArray = new JSONArray();
		for (String key : this.hereDataSet.keySet()) {
			Recipe r = this.hereDataSet.get(key);
			if (r.isRecipe_status()) {
				JSONObject jsobj = new JSONObject();
				try {
					jsobj.put(GeneralVarName.Android_JSON_Key_Member_id, r.getMember_id());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_amount, r.getRecipe_amount());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_cooktime, r.getRecipe_cooktime());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_detail, r.getRecipe_detail());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_id, r.getRecipe_id());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_name, r.getRecipe_name());
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_picture, r.getRecipe_picture());
					jsobj.put(GeneralVarName.Android_JSON_Key_Upload_date,
							ProcessDate.DateParseToString(r.getUpload_date()));
					jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_status, r.isRecipe_status());
					jsonArray.put(jsobj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} // --end for
		return jsonArray.toString();
	}

	// -------取得目前資料筆數 ----
	public int getCurrentSize() {
		return this.hereDataSet.size();
	}

	// ------------ 更新旗標 ----------------
	public boolean isGetAllFlag() {
		return getAllFlag;
	}

	public void setGetAllFlag(boolean getAllFlag) {
		this.getAllFlag = getAllFlag;
	}

	// -------------------------------------------------
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			String id_pk = " ";
			if (o instanceof Recipe) {
				Recipe tempm = (Recipe) o;
				id_pk = tempm.getRecipe_id().toLowerCase();
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
	public void run() {
		// TODO Auto-generated method stub

	}

	// Search Recipe by MemberId-----------------------------------------
	public ArrayList SearchPKbyMemberId(String PK) {
		// TODO Auto-generated method stub

		ArrayList<Recipe> SearchPKbyMemberId = new ArrayList<Recipe>();
		for (String key : this.hereDataSet.keySet()) {
			Recipe tempR = hereDataSet.get(key);
			if (tempR.getMember_id().equalsIgnoreCase(PK)) {
				SearchPKbyMemberId.add(tempR);
			}
		}
		return SearchPKbyMemberId;
	}

	// Search Recipe by MemberId-----------------------------------------

	public ArrayList SearchResult(String Search) {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From trecipe where recipe_id in "
					+ "(Select recipe_id From trecipe_material where material_name like '%" + Search + "%') "
					+ "or recipe_id in (Select recipe_id From tmethod where method_detail like '%" + Search + "%') "
					+ "or member_id in(Select member_id From tmember where member_name like '%" + Search + "%') "
					+ "or recipe_detail like '%" + Search + "%' or recipe_name like '%" + Search + "%'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			/*
			 * public Recipe(String recipe_id, String recipe_name, String
			 * member_id, String recipe_amount, String recipe_cooktime, String
			 * recipe_picture, boolean recipe_status, Date upload_date)
			 * recipe_detail` varchar(5000) NOT NULL,
			 */
			ArrayList<Recipe> SearchResult = new ArrayList<Recipe>();
			while (rs.next()) {
				Recipe r = new Recipe(rs.getNString("recipe_id"), rs.getNString("recipe_name"),
						rs.getNString("member_id"), rs.getNString("recipe_amount"), rs.getNString("recipe_cooktime"),
						rs.getNString("recipe_picture"), rs.getBoolean("recipe_status"), rs.getDate("upload_date"),
						rs.getString("recipe_detail"));

				SearchResult.add(r);
			}
			rs.close();
			super.destorySQLConnection(conn);
			System.out.println("從資料庫重新更新注入資料到食譜工廠");
			return SearchResult;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ArrayList<Recipe> SearchResult = new ArrayList<Recipe>();
			return SearchResult;
		}

	}

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		this.JsonString = jsonString;
	}
	
	/*食材名稱搜尋食譜*/
	public String materialSearchRecipe(String jsonString) {
		JSONArray searchJsonArray = new JSONArray();
		try {
			conn = super.getSQLConnection();
			String sql = "select * from trecipe where recipe_id in "
					+ "(select distinct recipe_id from trecipe_material where ";
			JSONArray jsonArray = new JSONArray(jsonString);
			for (int i = 0; i < jsonArray.length(); i++) {
				if (i == (jsonArray.length() - 1)) {
					sql += "material_name like N'%" + jsonArray.getString(i) + "%')";
					break;
				}
				sql += "material_name like N'%" + jsonArray.getString(i) + "%' or ";
				
			}
			System.out.println("materialSearchRecipe SQL--->" + sql);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put(GeneralVarName.Android_JSON_Key_Member_id, rs.getNString("member_id"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_amount, rs.getNString("recipe_amount"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_cooktime, rs.getNString("recipe_cooktime"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_detail, rs.getString("recipe_detail"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_id, rs.getNString("recipe_id"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_name, rs.getNString("recipe_name"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_picture, rs.getNString("recipe_picture"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Upload_date,
						ProcessDate.DateParseToString(rs.getDate("upload_date")));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Recipe_status, rs.getBoolean("recipe_status"));
				searchJsonArray.put(jsonObject);
			}
			rs.close();
			super.destorySQLConnection(conn);
			
			return searchJsonArray.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchJsonArray.toString();
	}
	
	/*手機食譜上傳新增*/
	public boolean androidRecipeInsert(String jsonString, String recipeId) {
		try {
			JSONObject recipeJsonObject = new JSONObject(jsonString);
			Recipe recipe = new Recipe();
			recipe .setRecipe_id(recipeId);
			recipe.setMember_id(recipeJsonObject.getString(GeneralVarName.Android_JSON_Key_Member_id));
			recipe.setRecipe_detail(recipeJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_detail));
			recipe.setUpload_date(ProcessDate.getCurrentLocalDate());
			recipe.setRecipe_name(recipeJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_name));
			recipe.setRecipe_amount(recipeJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_amount));
			recipe.setRecipe_cooktime(recipeJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_cooktime));
			recipe.setRecipe_status(recipeJsonObject.getBoolean(GeneralVarName.Android_JSON_Key_Recipe_status));
			recipe.setRecipe_picture("recipepic/" + recipeId + ".jpg");
			Insert(recipe);
			add(recipe); 
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}/// -- class end
