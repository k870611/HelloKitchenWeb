package Factorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.AccountManager;
import AbstractAncestor.FactorAncestor;
import EntityComponents.CompanyPictures;
import EntityComponents.RecipeMaterial;
import EntityComponents.RecipeMethod;
import Modules.CompositeKey;
import Modules.UpdateSet;
import Utility.GeneralVarName;

public class CompanyPictureFactory extends AccountManager implements java.io.Serializable {
	/* PRIMARY KEY (`picture_id`, `company_id`), */
	// --- field ---
	private Connection conn;
	/* CompositeKey (company_id,picture_id) tolowercase */
	private HashMap<CompositeKey, CompanyPictures> hereDataSet = new HashMap<CompositeKey, CompanyPictures>();
    private String JsonString;
	// -------- construct -------
	public CompanyPictureFactory() {

	}

	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		CompanyPictures cp = (CompanyPictures) o;
		conn = super.getSQLConnection();
		/*
		 * `picture_id` VARCHAR(50) NOT NULL, `company_id` VARCHAR(50) NOT NULL,
		 * `picture_path` VARCHAR(100) NOT NULL, `picture_name` VARCHAR(20) NOT
		 * NULL, `picture_description` VARCHAR(5000) NULL
		 */
		String sql = "insert into tcomapany_pictures values(?,?,?,?,?);";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, cp.getPicture_id());
		ps.setNString(2, cp.getCompany_id());
		ps.setNString(3, cp.getPicture_path());
		ps.setNString(4, cp.getPicture_name());
		ps.setNString(5, cp.getPicture_description());
		boolean isinsert = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isinsert;

	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		CompanyPictures cp = (CompanyPictures) o;
		try {
			conn = super.getSQLConnection();
			String sql = "delete from tcomapany_pictures where picture_id = ? and company_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, cp.getPicture_id());
			ps.setNString(2, cp.getCompany_id());
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
		CompanyPictures old = (CompanyPictures) oldDataObj;
		CompanyPictures update = (CompanyPictures) updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		// ------------ 資料無變化 -----
		if ((upset == null) || (upset.size() <= 0)) {
			return -1;
		}
		try {
			conn = super.getSQLConnection();
			String sql = super.getUpdateString("update tcomapany_pictures Set ", upset)
					+ " where picture_id = ? and company_id = ?;";
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
			ps.setNString(count, old.getPicture_id());// -- where constraint
			ps.setNString(++count, old.getCompany_id());// -- where constraint
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
	 /*手機端 資料群更新*/
		public void UpdateForMobile(String Cid) throws SQLException{
			ArrayList<CompanyPictures> oldSet = (ArrayList<CompanyPictures>) this.sreachByPK(Cid);
			ArrayList<CompanyPictures> newSet = this.JSONParseToArray();
		    int oldSize = oldSet.size();
		    int newSize = newSet.size();
		    // "p%03d"
		    int currentCount = 1;
		  //------- Marge ---------------
		    for(int index=0;index < oldSize;index++){
		    	CompanyPictures oldObj = oldSet.get(index);
		    	CompanyPictures newObj = newSet.get(index);
		    	if(newObj.getPicture_name().equalsIgnoreCase("delete")){
		    		//--- 刪除區 -----
		    		this.Delete(oldObj);// database
		    		this.remove(oldObj);// WebSite
		    	}else{
		    		//--- 更新區 -----
		    		newObj.setPicture_id(String.format("p%03d", currentCount));
		    		this.Update(oldObj, newObj);//--database
		    		this.remove(oldObj);
		    		this.add(newObj);
		    		currentCount++;
		    	}
		  } //--- 舊範圍結束----
		  //------剩下都新增 ---
		   while(oldSize < newSize){
			   CompanyPictures newObj = newSet.get(oldSize);
			   newObj.setPicture_id(String.format("p%03d", currentCount));
			   this.Insert(newObj);
			   this.add(newObj);
			   oldSize++;
			   currentCount++;
		   }
		    
		}
		/*網站端 資料群更新*/
		public void UpdateForWeb(String Cid,ArrayList<CompanyPictures> updateArray) throws SQLException{
			ArrayList<CompanyPictures> oldSet = (ArrayList<CompanyPictures>) this.sreachByPK(Cid);
			ArrayList<CompanyPictures> newSet = updateArray;
		    int oldSize = oldSet.size();
		    int newSize = newSet.size();
		    
		    //------- 資料量相同 --------
		    if(oldSize == newSize){
		    	for(int index=0;index < oldSize;index++){
		    		CompanyPictures oldObj = oldSet.get(index);
		    		CompanyPictures newObj = newSet.get(index);
			    	this.Update(oldObj, newObj);
			    	this.refresh(newObj);
		    	}
		    	return ; //--斷點
		    }
		    //------ 資料量不同 ---------
		    int counter = 0;
		    //------ 舊少新多 (新增) -----
		    while(newSize > oldSize){
		    	CompanyPictures newObj = newSet.get(counter);
		    	if(counter >= oldSize){
		    		//--溢出部分(新增)
		    		this.Insert(newObj);
		    		this.add(newObj);
		    	}else{
		    		//--舊範圍內(更新)
		    		CompanyPictures oldObj = oldSet.get(counter);
		    		this.Update(oldObj, newObj);
		    		this.refresh(newObj);
		    	}
		    	counter++;
		    	if(counter == newSize){
		    		return;//--斷點
		    	}
		    }
		    //------ 舊多新少 (刪除)------
		    while(oldSize > newSize){
		    	CompanyPictures oldObj = oldSet.get(counter);
		    	if(counter >= newSize){
		    		//--delete 
		    		this.Delete(oldObj);
		    		this.remove(oldObj);
		    	}else{
		    		//-- 更新 ----
		    		CompanyPictures newObj = newSet.get(counter);
		    		this.Update(oldObj, newObj);
		    		this.refresh(newObj);
		    	}
		    	counter++;
		    	if(counter == oldSize){
		    		return;//--斷點
		    	}
		    }
		}
	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		String keyValus = "";
		if (o instanceof String) {
			keyValus = (String) o;
			for (CompositeKey key : this.hereDataSet.keySet()) {
				if (key.keyPartiallyMatch(keyValus.toLowerCase())) {
					return true;
				}
			}
		}
		if (o instanceof CompositeKey) {
			return this.hereDataSet.containsKey(o);
		}
		if (o instanceof CompanyPictures) {
			CompanyPictures cp = (CompanyPictures) o;
			CompositeKey temp = new CompositeKey(cp.getCompany_id().toLowerCase(), cp.getPicture_id().toLowerCase());
			return this.hereDataSet.containsKey(temp);
		}
		return false;

	}

	@Override
	public Object sreachByPK(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			// -----------------------------------
			if (o instanceof String) {
				String keyValus = (String) o;
				ArrayList<CompanyPictures> sreachresult = new ArrayList<CompanyPictures>();
				for (CompositeKey key : this.hereDataSet.keySet()) {
					if (key.mainKeyMatch(keyValus.toLowerCase())) {
						sreachresult.add(this.hereDataSet.get(key));
						System.out.println("key ==> "+key);
						//System.out.println(sreachresult.toString()+":the search set is");
					}
				}
				
				return sreachresult;
			}
			// ----------------------------------

		}
		return null;
	}

	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<CompanyPictures> arraydata = new ArrayList<CompanyPictures>();
		for (CompositeKey key : this.hereDataSet.keySet()) {
			arraydata.add(this.hereDataSet.get(key));
		}
		return arraydata;

	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		CompanyPictures cp = (CompanyPictures) o;
		CompositeKey sk = new CompositeKey(cp.getCompany_id().toLowerCase(), cp.getPicture_id().toLowerCase());
		if (this.hereDataSet.containsKey(sk)) {
			System.out.println("該筆資料已存在公司照片工廠");
			return false;
		} else {
			this.hereDataSet.put(sk, cp);
			System.out.println("公司照片資料加入公司照片工廠");
			return true;
		}

	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From tcomapany_pictures";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			// -------------------------
			this.hereDataSet.clear();
			while (rs.next()) {
				/*
				 * CompanyPictures(String picture_id, String company_id, String
				 * picture_path, String picture_name, String
				 * picture_description)
				 */
				CompanyPictures cp = new CompanyPictures(rs.getNString("picture_id"), rs.getNString("company_id"),
						rs.getNString("picture_path"), rs.getNString("picture_name"),
						rs.getNString("picture_description"));

				CompositeKey key = new CompositeKey(cp.getCompany_id().toLowerCase(), cp.getPicture_id().toLowerCase());
				this.hereDataSet.put(key, cp);
			}
			rs.close();
			super.destorySQLConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		CompanyPictures cp = (CompanyPictures) o;
		if (this.isExistByPK(cp)) {
			CompositeKey sk = new CompositeKey(cp.getCompany_id().toLowerCase(), cp.getPicture_id().toLowerCase());
			this.hereDataSet.replace(sk, cp);
			System.out.println("公司照片工廠更新一筆資料");
		}

	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			/*if (o instanceof String) {
				for (CompositeKey ck : this.hereDataSet.keySet()) {
					if (ck.keyPartiallyMatch(((String) o).toLowerCase())) {
						CompanyPictures cp = this.hereDataSet.remove(ck);
						System.out.println("公司照片從網站資料移除==>" + cp);
					}
				}
				return true;
			}*/
			// -------------------
			if (o instanceof CompanyPictures) {
				CompositeKey ck = new CompositeKey(((CompanyPictures) o).getCompany_id().toLowerCase(),
						((CompanyPictures) o).getPicture_id().toLowerCase());
				CompanyPictures cp = this.hereDataSet.remove(ck);
				System.out.println("公司照片從網站資料移除byCompanyPictures==>" + cp);
				return !(cp==null);
			}
             if(o instanceof CompositeKey){
            	 CompositeKey ck = (CompositeKey) o;
            	 CompanyPictures cp = this.hereDataSet.remove(ck);
            	 System.out.println("公司照片從網站資料移除byCompositeKey==>" + cp);
            	 return !(cp==null);
             }
			return false;
		} else {
			return false;
		}
	}
	
	public JSONArray JSONsreachByPK(String company_id){
		ArrayList<CompanyPictures> sreachresult =  (ArrayList<CompanyPictures>) this.sreachByPK(company_id);
	    JSONArray jsonarray = new JSONArray();
	    for(CompanyPictures cp : sreachresult){
	    	JSONObject jsobj = new JSONObject();
	    	try {
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_id, cp.getCompany_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_Picture_id, cp.getPicture_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_Picture_description, cp.getPicture_description());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_Picture_name, cp.getPicture_name());
				jsobj.put(GeneralVarName.Android_JSON_Key_Company_Picture_path, cp.getPicture_path());
				jsonarray.put(jsobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	    return jsonarray;
	}

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("injectJSON : "+jsonString);
		this.JsonString = jsonString;
	}
	/*JSON反解析變成 ArrayList<CompanyPictures>*/
	public ArrayList<CompanyPictures> JSONParseToArray(){
		 ArrayList<CompanyPictures> CompanyPicturesSet = new ArrayList<CompanyPictures>();
		
		try {
			JSONArray jsa = new JSONArray(this.JsonString);
			for(int index=0;index<jsa.length();index++){
				JSONObject jso = jsa.getJSONObject(index);
				/*
				 * CompanyPictures(String picture_id, 
				 *                 String company_id, 
				 *                 String picture_path, 
				 *                 String picture_name, 
				 *                 String picture_description)
				 */
				CompanyPictures cp = new CompanyPictures(
						jso.getString(GeneralVarName.Android_JSON_Key_Company_Picture_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Company_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Company_Picture_path),
						jso.getString(GeneralVarName.Android_JSON_Key_Company_Picture_name),
						jso.getString(GeneralVarName.Android_JSON_Key_Company_Picture_description));
				CompanyPicturesSet.add(cp);
				System.out.println("JSON add CompanyPictures=>"+cp);
			 }
			 return CompanyPicturesSet;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return CompanyPicturesSet;
		}
		
	}

} // -- class end
