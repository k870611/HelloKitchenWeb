package Factorys;

import java.sql.Connection;
import java.sql.Date;
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

import AbstractAncestor.FactorAncestor;
import EntityComponents.CompanyPictures;
import EntityComponents.RecipeMaterial;
import Modules.CompositeKey;
import Modules.UpdateSet;
import Utility.GeneralVarName;

public class RecipeMaterialFactory extends FactorAncestor implements java.io.Serializable {
	/* PRIMARY KEY (`recipe_id`, `material_id`), */
	// --- field ---
    private Connection conn;
    /*CompositeKey (recipe_id,material_id) tolowercase */
    private HashMap<CompositeKey,RecipeMaterial> hereDataSet = new HashMap<CompositeKey,RecipeMaterial>();
	private String JsonString;
    // --- construct ---
	public RecipeMaterialFactory() {

	}
    //--- Override Method ----
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		RecipeMaterial rm = (RecipeMaterial)o;
		conn = super.getSQLConnection();
		/*`recipe_id` VARCHAR(50) NOT NULL,
          `material_id` VARCHAR(50) NOT NULL,
          `material_name` VARCHAR(50) NOT NULL,
          `material_amount` VARCHAR(50) NOT NULL,
          `material_picture` VARCHAR(100) NULL DEFAULT NULL,*/
		String sql ="insert into trecipe_material values(?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, rm.getRecipe_id());
		ps.setNString(2, rm.getMaterial_id());
		ps.setNString(3, rm.getMaterial_name());
		ps.setNString(4, rm.getMaterial_amount());
		ps.setNString(5, rm.getMaterial_picture());
		boolean isinsert = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isinsert;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		RecipeMaterial rm = (RecipeMaterial)o;
		try {
			conn = super.getSQLConnection();
			String sql = "delete from trecipe_material where recipe_id = ? and material_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, rm.getRecipe_id());
			ps.setNString(2, rm.getMaterial_id());
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
		ArrayList<RecipeMaterial> oldSet = (ArrayList<RecipeMaterial>) this.sreachByPK(Cid);
		ArrayList<RecipeMaterial> newSet = this.parseNewUpdateValue();
	    int oldSize = oldSet.size();
	    int newSize = newSet.size();
	    // "m%03d"
	    int currentCount = 1;
	  //------- Marge ---------------
	    for(int index=0;index < oldSize;index++){
	    	RecipeMaterial oldObj = oldSet.get(index);
	    	RecipeMaterial newObj = newSet.get(index);
	    	if(newObj.getMaterial_name().equalsIgnoreCase("delete")){
	    		//--- 刪除區 -----
	    		this.Delete(oldObj);// database
	    		this.remove(oldObj);// WebSite
	    	}else{
	    		//--- 更新區 -----
	    		newObj.setMaterial_id(String.format("m%03d", currentCount));
	    		this.Update(oldObj, newObj);//--database
	    		this.remove(oldObj);
	    		this.add(newObj);
	    		currentCount++;
	    	}
	  } //--- 舊範圍結束----
	  //------剩下都新增 ---
	   while(oldSize < newSize){
		   RecipeMaterial newObj = newSet.get(oldSize);
		   newObj.setMaterial_id(String.format("m%03d", currentCount));
		   this.Insert(newObj);
		   this.add(newObj);
		   oldSize++;
		   currentCount++;
	   }
	    
	}
	/*網站端 資料群更新*/
	public void UpdateForWeb(String Cid,ArrayList<RecipeMaterial> updateArray) throws SQLException{
		ArrayList<RecipeMaterial> oldSet = (ArrayList<RecipeMaterial>) this.sreachByPK(Cid);
		ArrayList<RecipeMaterial> newSet = updateArray;
	    int oldSize = oldSet.size();
	    int newSize = newSet.size();
	    int counter = 0;
	    //------- 資料量相同 --------
	    if(oldSize == newSize){
	    	for(int index=0;index < oldSize;index++){
	    		RecipeMaterial oldObj = oldSet.get(index);
		    	RecipeMaterial newObj = newSet.get(index);
		    	this.Update(oldObj, newObj);
		    	this.refresh(newObj);
	    	}
	    	return ; //--斷點
	    }
	    //------ 資料量不同 ---------
	    //------ 舊少新多 (新增) -----
	    while(newSize > oldSize){
	       	RecipeMaterial newObj = newSet.get(counter);
	    	if(counter >= oldSize){
	    		//--溢出部分(新增)
	    		this.Insert(newObj);
	    		this.add(newObj);
	    	}else{
	    		//--舊範圍內(更新)
	    		RecipeMaterial oldObj = oldSet.get(counter);
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
	    	RecipeMaterial oldObj = oldSet.get(counter);
	    	if(counter >= newSize){
	    		//--delete 
	    		this.Delete(oldObj);
	    		this.remove(oldObj);
	    	}else{
	    		//-- 更新 ----
	    		RecipeMaterial newObj = newSet.get(counter);
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
	public int Update(Object oldDataObj, Object updateDataObj) {
		// TODO Auto-generated method stub
		RecipeMaterial old = (RecipeMaterial)oldDataObj;
		RecipeMaterial update = (RecipeMaterial)updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		//------------ 資料無變化 ----- 
		if( (upset == null) || (upset.size() <=0)){
			return -1;
		}
		//------------------------
		try {
			conn = super.getSQLConnection();
			String sql = super.getUpdateString("update trecipe_material Set ", upset) + " where recipe_id = ? and material_id = ?;";
			System.out.println("sql update PreparedStatement : "+ sql);
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
					ps.setDate(count,(Date) unit.getUpdateValue());
					break;
				default: // --- 失敗退回
					return -2;
				}
				count++;
			} // --- loop end
			ps.setNString(count, old.getRecipe_id());// -- where constraint
			ps.setNString(++count, old.getMaterial_id());// -- where constraint
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
    /* 模糊搜尋 */
	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		String keyValus = "";
		if(o instanceof String){
			keyValus = (String) o;
			for(CompositeKey key : this.hereDataSet.keySet()){
				if(key.keyPartiallyMatch(keyValus.toLowerCase())){
					return true;
				}
			}
		}
		if(o instanceof CompositeKey){
			return this.hereDataSet.containsKey(o);
		}
		if(o instanceof RecipeMaterial){
			RecipeMaterial rm = (RecipeMaterial)o;
			CompositeKey temp = new CompositeKey(rm.getRecipe_id().toLowerCase(),rm.getMaterial_id().toLowerCase());
		    return this.hereDataSet.containsKey(temp);
		}
		return true;
	}

	@Override
	public Object sreachByPK(Object o) {
		// ----字串模式
		if(this.isExistByPK(o)){
		//-----------------------------------	
			if(o instanceof String){
				String keyValus = (String) o;
				ArrayList<RecipeMaterial> sreachresult =  new ArrayList<RecipeMaterial>();
				for(CompositeKey key : this.hereDataSet.keySet()){
					if(key.mainKeyMatch(keyValus.toLowerCase())){
						sreachresult.add(this.hereDataSet.get(key));
						}
				}//-- end forloop
				Collections.sort(sreachresult);
				return sreachresult;
			} 	
		//----------------------------------
			
		}
	    return null;//-- 沒有模式結果 
	}

	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<RecipeMaterial> arraydata = new ArrayList<RecipeMaterial>();
		 for(CompositeKey key:this.hereDataSet.keySet()){
			 arraydata.add(this.hereDataSet.get(key));
		 }
		 return arraydata;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		RecipeMaterial rm = (RecipeMaterial)o;
		CompositeKey sk = new CompositeKey(rm.getRecipe_id().toLowerCase(),
				                           rm.getMaterial_id().toLowerCase());
		if(this.hereDataSet.containsKey(sk)){
			System.out.println("該筆資料已存在食譜材料工廠");
			return false;
		}else{
			this.hereDataSet.put(sk,rm);
			System.out.println("食譜材料資料加入食譜材料工廠");
			return true;
		}
	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		try {
			conn  = super.getSQLConnection();
			String sql ="Select * From trecipe_material";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//-------------------------
			this.hereDataSet.clear();
			while(rs.next()){
				/*RecipeMaterial(String recipe_id, 
				 * String material_id, 
				 * String material_name, 
				 * String material_amount,
			       String material_picture*/
				RecipeMaterial rm = new RecipeMaterial(
						rs.getNString("recipe_id"),
						rs.getNString("material_id"),
						rs.getNString("material_name"),
						rs.getNString("material_amount"),
						rs.getNString("material_picture"));
				
			   CompositeKey key = new CompositeKey(rm.getRecipe_id().toLowerCase(),
					                               rm.getMaterial_id().toLowerCase());
			   this.hereDataSet.put(key, rm);
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
		RecipeMaterial rm = (RecipeMaterial)o;
		if(this.isExistByPK(rm)){
			CompositeKey sk = new CompositeKey(rm.getRecipe_id().toLowerCase(),
                    rm.getMaterial_id().toLowerCase());
         this.hereDataSet.replace(sk, rm);
         System.out.println("食譜材料工廠更新一筆資料");
		}
	}
	
	public JSONArray JSONsreachByPK(String recipe_id){
		ArrayList<RecipeMaterial> sreachresult =  (ArrayList<RecipeMaterial>) this.sreachByPK(recipe_id);
	    JSONArray jsonarray = new JSONArray();
	    for(RecipeMaterial se:sreachresult){
	    	JSONObject jsobj = new JSONObject();
	    	try {
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_id, se.getRecipe_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Material_id, se.getMaterial_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Material_name, se.getMaterial_name());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Material_amount, se.getMaterial_amount());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Material_picture, se.getMaterial_picture());
				jsonarray.put(jsobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	    return jsonarray;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if (this.isExistByPK(o)) {
			/*if (o instanceof String) {
				for (CompositeKey ck : this.hereDataSet.keySet()) {
					if (ck.keyPartiallyMatch(((String) o).toLowerCase())) {
						RecipeMaterial cp = this.hereDataSet.remove(ck);
						System.out.println("憌???敺雯蝡宏??=>" + cp);
					}
				}
				return true;
			}*/
			// -------------------
			if (o instanceof RecipeMaterial) {
				CompositeKey ck = new CompositeKey(((RecipeMaterial) o).getRecipe_id().toLowerCase(),
						((RecipeMaterial) o).getMaterial_id().toLowerCase());
				RecipeMaterial cp = this.hereDataSet.remove(ck);
				System.out.println("網站移除食譜材料ByRecipeMaterial==>" + cp);
				return !(cp==null);
			}
			if(o instanceof CompositeKey){
				CompositeKey ck = (CompositeKey) o;
				RecipeMaterial cp = this.hereDataSet.remove(ck);
				System.out.println("網站移除食譜材料ByCompositeKey==>" + cp);
				return !(cp==null);
			}
			return false;
		} else {
			return false;
		}
	}

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		System.out.println("RM inject JSON:"+jsonString);
		this.JsonString = jsonString;
	}
	/* 解析JSON 轉為 ArrayList<RecipeMaterial> */
	private ArrayList<RecipeMaterial> parseNewUpdateValue() {
		ArrayList<RecipeMaterial> values = new ArrayList<RecipeMaterial>();
		try {
			JSONArray jsa = new JSONArray(this.JsonString);
			for (int index = 0; index < jsa.length(); index++) {
				JSONObject jso = new JSONObject(jsa.get(index).toString());
				/*
				 * public RecipeMaterial(String recipe_id, String material_id,
				 * String material_name, String material_amount, String
				 * material_picture)
				 */
				RecipeMaterial temp = new RecipeMaterial(jso.getString(GeneralVarName.Android_JSON_Key_Recipe_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_name),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_amount),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_picture));
				values.add(temp);
				System.out.println("Json ParseTo RMObj" + temp);
			}
			return values;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return values;
		}
	}
    
    /*手機食譜食材上傳新增*/
	public boolean androidRecipeMaterialInsert(String jsonString, String recipeId) {
		try {
			JSONArray myJsonArray = new JSONArray(jsonString);
			ArrayList<RecipeMaterial> myArrayList = new ArrayList<>(); 
			for (int i = 0; i < myJsonArray.length(); i++) {
				JSONObject recipeMaterialJsonObject = new JSONObject(myJsonArray.getString(i));
				RecipeMaterial recipeMaterial = new RecipeMaterial();
				recipeMaterial.setRecipe_id(recipeId);
				recipeMaterial.setMaterial_id(recipeMaterialJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_id));
				recipeMaterial.setMaterial_name(recipeMaterialJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_name));
				recipeMaterial.setMaterial_amount(recipeMaterialJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_Material_amount));
				recipeMaterial.setMaterial_picture("noData");
				myArrayList.add(recipeMaterial);
			}
			return androidRecipMaterialInsertSql(myArrayList);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean androidRecipMaterialInsertSql(ArrayList<RecipeMaterial> myArrayList) {
		try {
			for (RecipeMaterial rm : myArrayList) {
				Insert(rm);
				add(rm);
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}// -- class end
