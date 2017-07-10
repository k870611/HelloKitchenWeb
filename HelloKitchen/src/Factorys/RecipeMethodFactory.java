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
import EntityComponents.RecipeMethod;
import Modules.CompositeKey;
import Modules.UpdateSet;
import Utility.GeneralVarName;

public class RecipeMethodFactory extends FactorAncestor implements java.io.Serializable{
	/* PRIMARY KEY (`recipe_id`, `method_id`), */
	// --- field ---
    private Connection conn;
    /*CompositeKey (recipe_id,method_id) tolowercase */
    private HashMap<CompositeKey,RecipeMethod> hereDataSet = new HashMap<CompositeKey,RecipeMethod>();
	private String JsonString;
	//--- construct ---
    public RecipeMethodFactory(){
    	
    }
   //-----------------------------------
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		RecipeMethod rm = (RecipeMethod)o;
		conn = super.getSQLConnection();
		/*`recipe_id` VARCHAR(50) NOT NULL,
          `method_id` VARCHAR(50) NOT NULL,
          `method_detail` VARCHAR(5000) NOT NULL,
          `method_picture` VARCHAR(100) NULL DEFAULT NULL,*/
		String sql ="insert into tmethod values(?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setNString(1, rm.getRecipe_id());
		ps.setNString(2, rm.getMethod_id());
		ps.setNString(3, rm.getMethod_detail());
		ps.setNString(4, rm.getMethod_picture());
		boolean isinsert = ps.execute();
		ps.close();
		super.destorySQLConnection(conn);
		return isinsert;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		RecipeMethod rm = (RecipeMethod)o;
				try {
				conn = super.getSQLConnection();
				String sql = "delete from tmethod where recipe_id = ? and method_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setNString(1, rm.getRecipe_id());
				ps.setNString(2, rm.getMethod_id());
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
		RecipeMethod old = (RecipeMethod)oldDataObj;
		RecipeMethod update = (RecipeMethod)updateDataObj;
		int count = 1;
		ArrayList<UpdateSet> upset = old.getUpdateSet(update);
		//------------ 資料無變化 ----- 
		if( (upset == null) || (upset.size() <= 0)){
			return -1;
		}
		//------------------------
		try {
			conn = super.getSQLConnection();
			String sql = super.getUpdateString("update tmethod Set ", upset) + "where  recipe_id = ? and method_id = ?;";
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
			ps.setNString(++count, old.getMethod_id());// -- where constraint
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
			ArrayList<RecipeMethod> oldSet = (ArrayList<RecipeMethod>) this.sreachByPK(Cid);
			ArrayList<RecipeMethod> newSet = this.JSONParseToArray();
		    int oldSize = oldSet.size();
		    int newSize = newSet.size();
		    // "m%03d"
		    int currentCount = 1;
		  //------- Marge ---------------
		    for(int index=0;index < oldSize;index++){
		    	RecipeMethod oldObj = oldSet.get(index);
		    	RecipeMethod newObj = newSet.get(index);
		    	if(newObj.getMethod_detail().equalsIgnoreCase("delete")){
		    		//--- 刪除區 -----
		    		this.Delete(oldObj);// database
		    		this.remove(oldObj);// WebSite
		    	}else{
		    		//--- 更新區 -----
		    		newObj.setMethod_id(String.format("m%03d", currentCount));
		    		this.Update(oldObj, newObj);//--database
		    		this.remove(oldObj);
		    		this.add(newObj);
		    		currentCount++;
		    	}
		  } //--- 舊範圍結束----
		  //------剩下都新增 ---
		   while(oldSize < newSize){
			   RecipeMethod newObj = newSet.get(oldSize);
			   newObj.setMethod_id(String.format("m%03d", currentCount));
			   this.Insert(newObj);
			   this.add(newObj);
			   oldSize++;
			   currentCount++;
		   }
		    
		}
		/*網站端 資料群更新*/
		public void UpdateForWeb(String Cid,ArrayList<RecipeMethod> updateArray) throws SQLException{
			ArrayList<RecipeMethod> oldSet = (ArrayList<RecipeMethod>) this.sreachByPK(Cid);
			ArrayList<RecipeMethod> newSet = updateArray;
		    int oldSize = oldSet.size();
		    int newSize = newSet.size();
		    int counter = 0;
		    //------- 資料量相同 --------
		    if(oldSize == newSize){
		    	for(int index=0;index < oldSize;index++){
		    		RecipeMethod oldObj = oldSet.get(index);
		    		RecipeMethod newObj = newSet.get(index);
			    	this.Update(oldObj, newObj);
			    	this.refresh(newObj);
		    	}
		    	return ; //--斷點
		    }
		    //------ 資料量不同 ---------
		    //------ 舊少新多 (新增) -----
		    while(newSize > oldSize){
		    	RecipeMethod newObj = newSet.get(counter);
		    	if(counter >= oldSize){
		    		//--溢出部分(新增)
		    		this.Insert(newObj);
		    		this.add(newObj);
		    	}else{
		    		//--舊範圍內(更新)
		    		RecipeMethod oldObj = oldSet.get(counter);
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
		    	RecipeMethod oldObj = oldSet.get(counter);
		    	if(counter >= newSize){
		    		//--delete 
		    		this.Delete(oldObj);
		    		this.remove(oldObj);
		    	}else{
		    		//-- 更新 ----
		    		RecipeMethod newObj = newSet.get(counter);
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
		if(o instanceof RecipeMethod){
			RecipeMethod rm = (RecipeMethod)o;
			CompositeKey temp = new CompositeKey(rm.getRecipe_id().toLowerCase(),rm.getMethod_id().toLowerCase());
		    return this.hereDataSet.containsKey(temp);
		}
		return true;
		
	}

	@Override
	public Object sreachByPK(Object o) {
		// TODO Auto-generated method stub
		
				if(this.isExistByPK(o)){
				//------- 有資料 ---------------	
					if(o instanceof String){
						String keyValus = (String) o;
						ArrayList<RecipeMethod> sreachresult =  new ArrayList<RecipeMethod>();
						for(CompositeKey key : this.hereDataSet.keySet()){
							if(key.mainKeyMatch(keyValus.toLowerCase())){
								sreachresult.add(this.hereDataSet.get(key));
							}
						}
						Collections.sort(sreachresult);
						return sreachresult;
					} 	
				//----------------------------------
					
				}
			    return null;
		
	}

	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<RecipeMethod> dataset = new ArrayList<RecipeMethod>(); 
		for(CompositeKey key : this.hereDataSet.keySet()){
			dataset.add(this.hereDataSet.get(key));
		}
		return dataset;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		RecipeMethod rm = (RecipeMethod)o;
		CompositeKey sk = new CompositeKey(rm.getRecipe_id().toLowerCase(),
				                           rm.getMethod_id().toLowerCase());
		if(this.hereDataSet.containsKey(sk)){
			System.out.println("該筆資料已存在食譜方法工廠");
			return false;
		}else{
			this.hereDataSet.put(sk,rm);
			System.out.println("食譜方法資料加入食譜方法工廠");
			return true;
		}
		
	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		try {
			conn  = super.getSQLConnection();
			String sql ="Select * From tmethod";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereDataSet.clear();
			//-------------------------
			/* public RecipeMethod(String recipe_id, 
			 * String method_id, 
			 * String method_detail, 
			 * String method_picture) */
			while(rs.next()){
			RecipeMethod rm = new  RecipeMethod(
					rs.getNString("recipe_id"),
					rs.getNString("method_id"),
					rs.getNString("method_detail"),
					rs.getNString("method_picture"));
			 CompositeKey key = new CompositeKey(rm.getRecipe_id().toLowerCase(),
                                                 rm.getMethod_id().toLowerCase());
             this.hereDataSet.put(key, rm);
			}
			//---------------
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
		RecipeMethod rm = (RecipeMethod)o;
		if(this.isExistByPK(rm)){
			CompositeKey key = new CompositeKey(rm.getRecipe_id().toLowerCase(),
                    rm.getMethod_id().toLowerCase());
         this.hereDataSet.replace(key, rm);
         System.out.println("食譜方法工廠更新一筆資料");
		}
		
	}
	
	public JSONArray JSONsreachByPK(String recipe_id){
		ArrayList<RecipeMethod> sreachresult =  (ArrayList<RecipeMethod>) this.sreachByPK(recipe_id);
	    JSONArray jsonarray = new JSONArray();
	    for(RecipeMethod se:sreachresult){
	    	JSONObject jsobj = new JSONObject();
	    	try {
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_id, se.getRecipe_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Method_id, se.getMethod_id());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Method_detail, se.getMethod_detail());
				jsobj.put(GeneralVarName.Android_JSON_Key_Recipe_Method_picture, se.getMethod_picture());
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
						RecipeMethod cp = this.hereDataSet.remove(ck);
						System.out.println("憌??寞?敺雯蝡宏??=>" + cp);
					}
				}
				return true;
			}*/
			// -------------------
			if (o instanceof RecipeMethod) {
				CompositeKey key = new CompositeKey(((RecipeMethod) o).getRecipe_id().toLowerCase(),
	                    ((RecipeMethod) o).getMethod_id().toLowerCase());
				RecipeMethod cp = this.hereDataSet.remove(key);
				System.out.println("網站移除食譜方法ByRecipeMethod==>" + cp);
				return !(cp==null);
			}
           if(o instanceof CompositeKey){
        	    CompositeKey key = (CompositeKey) o;
        		RecipeMethod cp = this.hereDataSet.remove(key);
				System.out.println("網站移除食譜方法ByCompositeKey==>" + cp);
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
		System.out.println("RMethod inject JSON"+jsonString);
		this.JsonString = jsonString;
	}
	/* JSON字串轉換 ArrayList<RecipeMethod> */
	private ArrayList<RecipeMethod> JSONParseToArray() {
		ArrayList<RecipeMethod> parseSet = new ArrayList<RecipeMethod>();
		try {
			JSONArray jsa = new JSONArray(this.JsonString);
			for (int index = 0; index < jsa.length(); index++) {
				JSONObject jso = new JSONObject(jsa.get(index).toString());
				/*
				 * RecipeMethod(String recipe_id, String method_id, String
				 * method_detail, String method_picture)
				 */
				RecipeMethod temp = new RecipeMethod(jso.getString(GeneralVarName.Android_JSON_Key_Recipe_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Method_id),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Method_detail),
						jso.getString(GeneralVarName.Android_JSON_Key_Recipe_Method_picture));
				parseSet.add(temp);
				System.out.println("json to RMethodObj " + jso);
			}
			return parseSet;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return parseSet;
		}
	}
	
	/*手機食譜食材上傳新增*/
	public boolean androidRecipeMethodInsert(String jsonString, String recipeId) {
		try {
			JSONArray myJsonArray = new JSONArray(jsonString);
			ArrayList<RecipeMethod> recipeMethodList = new ArrayList<>();
			for (int i = 0; i < myJsonArray.length(); i++) {
				RecipeMethod recipeMethod = new RecipeMethod();
				JSONObject recipeMethodJsonObject = new JSONObject(myJsonArray.getString(i));
				recipeMethod.setRecipe_id(recipeId);
				recipeMethod.setMethod_id(recipeMethodJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_Method_id));
				recipeMethod.setMethod_detail(recipeMethodJsonObject.getString(GeneralVarName.Android_JSON_Key_Recipe_Method_detail));
				recipeMethod.setMethod_picture("noData");
				recipeMethodList.add(recipeMethod); 
			}
			return androidRecipMaterialInsertSql(recipeMethodList);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean androidRecipMaterialInsertSql(ArrayList<RecipeMethod> myArrayList) {
		try {
			for (RecipeMethod recipeMethod : myArrayList) {
				Insert(recipeMethod);
				add(recipeMethod);
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
	}
}//-- class end 
