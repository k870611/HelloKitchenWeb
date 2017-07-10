package EntityComponents;

import java.util.ArrayList;

import Modules.UpdateSet;

/**
 * @author user
 *
 */
public class Company implements java.io.Serializable{

	/*tcompany` (
  `company_id` VARCHAR(50) NOT NULL,
  `company_name` VARCHAR(50) NOT NULL,
  `company_logo` VARCHAR(100) NULL DEFAULT NULL,
  `company_cover` VARCHAR(100) NULL DEFAULT NULL,
  `company_intro` VARCHAR(8000) NOT NULL,
  `company_address` VARCHAR(100) NOT NULL,
  `company_tel` VARCHAR(50) NOT NULL,
  `company_email` VARCHAR(100) NOT NULL,
  `company_owner` VARCHAR(50) NOT NULL,
  `company_password` VARCHAR(45) NOT NULL,
  `company_status` BIT(1) NULL DEFAULT 0,
  PRIMARY KEY (`company_id`))
	  */
	//-------------
	//----------Field --------------
	
	private String company_id; //-- pk
	private String company_name,company_logo,company_cover,company_intro;
	private String company_address,company_tel,company_email,company_owner;
	private String company_password;
	private boolean company_status;
	private ArrayList<UpdateSet> upset = new  ArrayList<UpdateSet>();
	//----- construct -----
	public Company(){
		
	}

	/**
	 * 建構式--公司欄位說明
	 * @param company_id 公司編號/帳號?
	 * @param company_name 公司名稱
	 * @param company_logo 公司商標
	 * @param company_cover 公司cover
	 * @param company_intro 公司資訊(簡介)
	 * @param company_address 公司地址
	 * @param company_tel 公司聯絡電話
	 * @param company_email 公司電子郵件地址
	 * @param company_owner 公司負責人
	 * @param company_password 公司登入密碼
	 * @param company_status 公司目前狀態
	 */
	public Company(String company_id, String company_name, String company_logo, String company_cover,
			String company_intro, String company_address, String company_tel, String company_email,
			String company_owner, String company_password, boolean company_status) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.company_logo = company_logo;
		this.company_cover = company_cover;
		this.company_intro = company_intro;
		this.company_address = company_address;
		this.company_tel = company_tel;
		this.company_email = company_email;
		this.company_owner = company_owner;
		this.company_password = company_password;
		this.company_status = company_status;
	}
	
	//-------------------------------------------------------------
	public String getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_logo() {
		if(company_logo == null){
			return "noData";
		}else{
			return company_logo;
		}
	}
	public void setCompany_logo(String company_logo) {
		this.company_logo = company_logo;
	}
	public String getCompany_cover() {
		if(company_cover == null){
			return "noData";
		}else{
			return company_cover;
		}
	}
	public void setCompany_cover(String company_cover) {
		this.company_cover = company_cover;
	}
	public String getCompany_intro() {
		return company_intro;
	}
	public void setCompany_intro(String company_intro) {
		this.company_intro = company_intro;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_tel() {
		return company_tel;
	}
	public void setCompany_tel(String company_tel) {
		this.company_tel = company_tel;
	}
	public String getCompany_email() {
		return company_email;
	}
	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}
	public String getCompany_owner() {
		return company_owner;
	}
	public void setCompany_owner(String company_owner) {
		this.company_owner = company_owner;
	}
	public String getCompany_password() {
		return company_password;
	}
	public void setCompany_password(String company_password) {
		this.company_password = company_password;
	}
	public boolean isCompany_status() {
		return company_status;
	}
	public void setCompany_status(boolean company_status) {
		this.company_status = company_status;
	}
	
	/*複寫 toString */
	@Override
	public String toString(){
		String info = "";
		info += String.format("company_id-> %s company_name-> %s company_logo-> %s company_cover-> %s",company_id,company_name,company_logo,company_cover );
		info+= String.format("company_intro-> %s company_address-> %s company_tel-> %s company_email-> %s company_owner-> %s",company_intro,company_address,company_tel,company_email,company_owner);
		info+= String.format("company_password-> %s company_status-> %b", company_intro,company_status);
		return info;
	}
	//--- 產生更新字串  ---
	
	/**
	 * @param updateObject Company 物件
	 * @return 比較異動集 
	 */
	public ArrayList<UpdateSet> getUpdateSet(Company compareCompany){
		/*private String company_id; //-- pk
	private String company_name,company_logo,company_cover,company_intro;
	private String company_address,company_tel,company_email,company_owner;
	private String company_password;
	private boolean company_status;*/
		Company c = compareCompany;
		if(!this.getCompany_name().equals(c.getCompany_name())){
			this.upset.add(new UpdateSet("company_name",c.getCompany_name(),"String"));
		}
		if(!this.getCompany_logo().equals(c.getCompany_logo())){
			this.upset.add(new UpdateSet("company_logo",c.getCompany_logo(),"String"));
		}
		
		if(!this.getCompany_cover().equals(c.getCompany_cover())){
			this.upset.add(new UpdateSet("company_cover",c.getCompany_cover(),"String"));
		}
		
		if(!this.getCompany_intro().equals(c.getCompany_intro())){
			this.upset.add(new UpdateSet("company_intro",c.getCompany_intro(),"String"));
		}
		if(!this.getCompany_address().equals(c.getCompany_address())){
			this.upset.add(new UpdateSet("company_address",c.getCompany_address(),"String"));
		}
		if(!this.getCompany_tel().equals(c.getCompany_tel())){
			this.upset.add(new UpdateSet("company_tel",c.getCompany_tel(),"String"));
		}
		if(!this.getCompany_email().equals(c.getCompany_email())){
			this.upset.add(new UpdateSet("company_email",c.getCompany_email(),"String"));
		}
		if(!this.getCompany_owner().equals(c.getCompany_owner())){
			this.upset.add(new UpdateSet("company_owner",c.getCompany_owner(),"String"));
		}
		if(!this.getCompany_password().equals(c.getCompany_password())){
			this.upset.add(new UpdateSet("company_password",c.getCompany_password(),"String"));
		}
		if(!this.isCompany_status() == c.isCompany_status()){
			this.upset.add(new UpdateSet("company_status",c.isCompany_status(),"boolean"));
		}
		
		return upset;
	}
	
	
}//--- class end 
