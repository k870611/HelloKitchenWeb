package EntityComponents;

import java.sql.Date;
import java.util.ArrayList;

import Modules.UpdateSet;

public class Recipe implements java.io.Serializable{

	/*`foods`.`trecipe` (
  `recipe_id` VARCHAR(50) NOT NULL,
  `recipe_name` VARCHAR(50) NOT NULL,
  `member_id` VARCHAR(100) NOT NULL,
  `upload_date` DATE NOT NULL,
  `recipe_status` BIT(1) NULL DEFAULT b'1',
  `recipe_amount` VARCHAR(15) NOT NULL,
  `recipe_cooktime` VARCHAR(15) NOT NULL,
  `recipe_picture` VARCHAR(100) NULL DEFAULT NULL,
  `recipe_detail` varchar(5000) NOT NULL,
  PRIMARY KEY (`recipe_id`),*/
	
	
	private String recipe_id; //--pk
	private String recipe_name,member_id,recipe_amount,recipe_cooktime;
	private String recipe_picture,recipe_detail;
	private boolean recipe_status;
	private Date upload_date;
	private ArrayList<UpdateSet> upset = new  ArrayList<UpdateSet>();
	//--- construct ---
	public Recipe(){
		
	}
	
	
	/**
	 * 食譜全用建構式
	 * @param recipe_id 食譜編號
	 * @param recipe_name 食譜名稱
	 * @param member_id 會員帳號
	 * @param recipe_amount 食譜材料總和
	 * @param recipe_cooktime 食譜烹調的時間
	 * @param recipe_picture 食譜完成照片的路徑
	 * @param recipe_status 食譜分享狀態
	 * @param upload_date 食譜最後一次更改日期
	 * @param recipe_detail 食譜介紹==>新增欄位
	 */
	public Recipe(String recipe_id, String recipe_name, String member_id, String recipe_amount, String recipe_cooktime,
			String recipe_picture, boolean recipe_status, Date upload_date,String recipe_detail) {
		super();
		this.recipe_id = recipe_id;
		this.recipe_name = recipe_name;
		this.member_id = member_id;
		this.recipe_amount = recipe_amount;
		this.recipe_cooktime = recipe_cooktime;
		this.recipe_picture = recipe_picture;
		this.recipe_status = recipe_status;
		this.upload_date = upload_date;
		this.setRecipe_detail(recipe_detail);
	}


	//--------------------------------
	@Override
	public String toString() {
		return "Recipe [recipe_id=" + recipe_id + ", recipe_name=" + recipe_name + ", member_id=" + member_id
				+ ", recipe_amount=" + recipe_amount + ", recipe_cooktime=" + recipe_cooktime + ", recipe_picture="
				+ recipe_picture + ", recipe_status=" + recipe_status 
				+ ", upload_date=" + upload_date + ", recipe_detail=" + recipe_detail+"]";
	}
//--------------------------------	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRecipe_name() {
		return recipe_name;
	}
	public void setRecipe_name(String recipe_name) {
		this.recipe_name = recipe_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRecipe_amount() {
		return recipe_amount;
	}
	public void setRecipe_amount(String recipe_amount) {
		this.recipe_amount = recipe_amount;
	}
	public String getRecipe_cooktime() {
		return recipe_cooktime;
	}
	public void setRecipe_cooktime(String recipe_cooktime) {
		this.recipe_cooktime = recipe_cooktime;
	}
	public String getRecipe_picture() {
		return recipe_picture;
	}
	public void setRecipe_picture(String recipe_picture) {
		this.recipe_picture = recipe_picture;
		if(recipe_picture==null){
			this.recipe_picture = "noPic";
		}
	}
	public boolean isRecipe_status() {
		return recipe_status;
	}
	public void setRecipe_status(boolean recipe_status) {
		this.recipe_status = recipe_status;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	
	public String getRecipe_detail() {
		return recipe_detail;
	}
    public void setRecipe_detail(String recipe_detail) {
		this.recipe_detail = recipe_detail;
	}


	//---------------------------------------------------
	/**
	 * @param recipeobj Recipe 物件
	 * @return 比較異動集 
	 */
	public ArrayList<UpdateSet> getUpdateSet(Recipe recipeobj){
		/*private String recipe_id; //--pk
	      private String recipe_name,member_id,recipe_amount,recipe_cooktime;
	      private String recipe_picture;
	      private boolean recipe_status;
	      private String recipe_picture,recipe_detail;*/
		Recipe r = recipeobj;
		
		if(!this.getMember_id().equals(r.getMember_id())){
			this.upset.add(new UpdateSet("member_id",r.getMember_id(),"String"));
		}
		if(!this.getRecipe_name().equals(r.getRecipe_name())){
			this.upset.add(new UpdateSet("recipe_name",r.getRecipe_name(),"String"));
		}
		if(!this.getRecipe_amount().equals(r.getRecipe_amount())){
			this.upset.add(new UpdateSet("recipe_amount",r.getRecipe_amount(),"String"));
		}
		if(!this.getRecipe_cooktime().equals(r.getRecipe_cooktime())){
			this.upset.add(new UpdateSet("recipe_cooktime",r.getRecipe_cooktime(),"String"));
		}
		if(!this.getRecipe_picture().equals(r.getRecipe_picture())){
			this.upset.add(new UpdateSet("recipe_picture",r.getRecipe_picture(),"String"));
		}
		if(this.isRecipe_status() != r.isRecipe_status()){
			this.upset.add(new UpdateSet("recipe_status",r.isRecipe_status(),"boolean"));
		}
		if(!this.getUpload_date().equals(r.getUpload_date())){
			this.upset.add(new UpdateSet("upload_date",r.getUpload_date(),"date"));
		}
		if(!this.getRecipe_detail().equals(r.getRecipe_detail())){
			this.upset.add(new UpdateSet("recipe_detail",r.getRecipe_detail(),"String"));
		}
		return this.upset;
	}

}
