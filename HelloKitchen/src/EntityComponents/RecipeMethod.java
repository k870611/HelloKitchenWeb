package EntityComponents;

import java.util.ArrayList;

import Modules.UpdateSet;

public class RecipeMethod implements java.io.Serializable,Comparable<RecipeMethod>{

	/*`tmethod` (
  `recipe_id` VARCHAR(50) NOT NULL,
  `method_id` VARCHAR(50) NOT NULL,
  `method_detail` VARCHAR(5000) NOT NULL,
  `method_picture` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`recipe_id`, `method_id`),*/
	private String recipe_id,method_id;//--sk
	private String method_detail,method_picture;
	private ArrayList<UpdateSet> upset = new ArrayList<UpdateSet>();
	//---- construct ----
	public RecipeMethod(){
		
	}
	public RecipeMethod(String recipe_id, String method_id, String method_detail, String method_picture) {
		super();
		this.recipe_id = recipe_id;
		this.method_id = method_id;
		this.method_detail = method_detail;
		this.method_picture = method_picture;
	}
	//---------------------------------
	@Override
	public String toString() {
		return "RecipeMethod [recipe_id=" + recipe_id + ", method_id=" + method_id + ", method_detail=" + method_detail
				+ ", method_picture=" + method_picture + "]\n";
	}
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getMethod_id() {
		return method_id;
	}
	public void setMethod_id(String method_id) {
		this.method_id = method_id;
	}
	public String getMethod_detail() {
		return method_detail;
	}
	public void setMethod_detail(String method_detail) {
		this.method_detail = method_detail;
	}
	public String getMethod_picture() {
		if(method_picture == null){
			return "noData";
		}else{
			return method_picture;
		}
	}
	public void setMethod_picture(String method_picture) {
		this.method_picture = method_picture;
	}
	//---------------------------------------
	/**
	 * @param updateObj RecipeMethod 物件
	 * @return 比較異動集 
	 */
	public ArrayList<UpdateSet> getUpdateSet(RecipeMethod updateobj){
		/*private String recipe_id,method_id;//--sk
		  private String method_detail,method_picture;*/
		RecipeMethod rm = updateobj;
		if(!this.getMethod_id().equals(rm.getMethod_id())){
			this.upset.add(new UpdateSet("method_id",rm.getMethod_id(),"String"));
		}
		if(!this.getMethod_detail().equals(rm.getMethod_detail())){
			this.upset.add(new UpdateSet("method_detail",rm.getMethod_detail(),"String"));
		}
		if(!this.getMethod_picture().equals(rm.getMethod_picture())){
			this.upset.add(new UpdateSet("method_picture",rm.getMethod_picture(),"String"));
		}
		return this.upset;
	}
	@Override
	public int compareTo(RecipeMethod rec) {
		// TODO Auto-generated method stub
		Integer self = Integer.parseInt(this.getMethod_id().substring(1));
		Integer outter = Integer.parseInt(rec.getMethod_id().substring(1));
		int result = self.compareTo(outter);
		return result;
	}
		
}//--- class end 

