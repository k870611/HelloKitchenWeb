package EntityComponents;

import java.util.ArrayList;

import Modules.UpdateSet;

public class CompanyPictures implements java.io.Serializable {
	/*
	 * tcomapany_pictures` ( `picture_id` VARCHAR(50) NOT NULL, `company_id`
	 * VARCHAR(50) NOT NULL, `picture_path` VARCHAR(100) NOT NULL,
	 * `picture_name` VARCHAR(20) NOT NULL, `picture_description` VARCHAR(5000)
	 * NULL, PRIMARY KEY (`picture_id`, `company_id`),
	 */
	private String picture_id, company_id; // -- sk
	private String picture_path, picture_name, picture_description;
	private ArrayList<UpdateSet> upset = new ArrayList<UpdateSet>();

	// ------------construct --------------------
	public CompanyPictures() {

	}

	public CompanyPictures(String picture_id, String company_id, String picture_path, String picture_name,
			String picture_description) {
		super();
		this.picture_id = picture_id;
		this.company_id = company_id;
		this.picture_path = picture_path;
		this.picture_name = picture_name;
		this.picture_description = picture_description;
	}

	// -----------------------------------------
	public String getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(String picture_id) {
		this.picture_id = picture_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPicture_path() {
		return picture_path;
	}

	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	public String getPicture_name() {
		if(picture_name == null){
			return "noData";
		}else{
			return picture_name;
		}
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public String getPicture_description() {
		if(picture_description == null){
			return "noData";
		}else{
			return picture_description;
		}
	}

	public void setPicture_description(String picture_description) {
		this.picture_description = picture_description;
	}

	/* 複寫 toString */
	@Override
	public String toString() {
		String info = "";
		/*
		 * private String picture_id,company_id; //-- sk private String
		 * picture_path,picture_name,picture_description;
		 */
		info += String.format("picture_id->%s company_id->%s picture_path->%s", picture_id, company_id, picture_path);
		info += String.format("picture_name->%s picture_description->%s", picture_name, picture_description);
		return info;
	}

	//-----------------------------------------
	/**
	 * @param updateObj CompanyPictures 物件
	 * @return 比較異動集 
	 */
	public ArrayList<UpdateSet> getUpdateSet(CompanyPictures updateobj){
		/*private String picture_id, company_id; // -- sk
		  private String picture_path, picture_name, picture_description;*/
		CompanyPictures cp = updateobj;
		if(!this.getPicture_id().equals(cp.getPicture_id())){
			this.upset.add(new UpdateSet("picture_id",cp.getPicture_id(),"String"));
		}
		if(!this.getPicture_path().equals(cp.getPicture_path())){
			this.upset.add(new UpdateSet("picture_path",cp.getPicture_path(),"String"));
		}
		if(!this.getPicture_name().equals(cp.getPicture_name())){
			this.upset.add(new UpdateSet("picture_name",cp.getPicture_name(),"String"));
		}
		if(!this.getPicture_description().equals(cp.getPicture_description())){
			this.upset.add(new UpdateSet("picture_description",cp.getPicture_description(),"String"));
		}
		return this.upset;
	}
	
	
}// -- class end
