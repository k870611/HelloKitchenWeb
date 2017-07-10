package EntityComponents;

import java.util.ArrayList;
import java.util.Comparator;

import Modules.UpdateSet;

public class RecipeMaterial implements java.io.Serializable, Comparable<RecipeMaterial> {

	/*
	 * `trecipe_material` ( `recipe_id` VARCHAR(50) NOT NULL, `material_id`
	 * VARCHAR(50) NOT NULL, `material_name` VARCHAR(50) NOT NULL,
	 * `material_amount` VARCHAR(50) NOT NULL, `material_picture` VARCHAR(100)
	 * NULL DEFAULT NULL, PRIMARY KEY (`recipe_id`, `material_id`),
	 */
	private String recipe_id, material_id;
	private String material_name, material_amount, material_picture;
	private ArrayList<UpdateSet> upset = new ArrayList<UpdateSet>();

	// ---- construct ---
	public RecipeMaterial() {

	}

	public RecipeMaterial(String recipe_id, String material_id, String material_name, String material_amount,
			String material_picture) {
		super();
		this.recipe_id = recipe_id;
		this.material_id = material_id;
		this.material_name = material_name;
		this.material_amount = material_amount;
		this.material_picture = material_picture;
	}

	// ------------------------------
	@Override
	public String toString() {
		return "RecipeMaterial [recipe_id=" + recipe_id + ", material_id=" + material_id + ", material_name="
				+ material_name + ", material_amount=" + material_amount + ", material_picture=" + material_picture
				+ "]";
	}

	public String getRecipe_id() {
		return recipe_id;
	}

	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getMaterial_amount() {
		if (material_amount == null) {
			return "noData";
		} else {
			return material_amount;
		}
	}

	public void setMaterial_amount(String material_amount) {
		this.material_amount = material_amount;
	}

	public String getMaterial_picture() {
		if(material_picture == null){
			return "noData";
		}else{
			return material_picture;
		}
		
	}

	public void setMaterial_picture(String material_picture) {
		this.material_picture = material_picture;
	}

	// -------------------------
	/**
	 * @param updateObj
	 *            RecipeMaterial 物件
	 * @return 比較異動集
	 */
	public ArrayList<UpdateSet> getUpdateSet(RecipeMaterial updateobj) {
		RecipeMaterial rm = updateobj;
		/*
		 * private String recipe_id,material_id; private String
		 * material_name,material_amount,material_picture;
		 */
		if (!this.getMaterial_id().equals(rm.getMaterial_id())) {
			this.upset.add(new UpdateSet("material_id", rm.getMaterial_id(), "String"));
		}
		if (!this.getMaterial_name().equals(rm.getMaterial_name())) {
			this.upset.add(new UpdateSet("material_name", rm.getMaterial_name(), "String"));
		}
		if (!this.getMaterial_amount().equals(rm.getMaterial_amount())) {
			this.upset.add(new UpdateSet("material_amount", rm.getMaterial_amount(), "String"));
		}
		if (!this.getMaterial_picture().equals(rm.getMaterial_picture())) {
			this.upset.add(new UpdateSet("material_picture", rm.getMaterial_picture(), "String"));
		}
		return this.upset;
	}

	@Override
	public int compareTo(RecipeMaterial o) {
		// TODO Auto-generated method stub
		Integer self = Integer.parseInt(this.getMaterial_id().substring(1));
		Integer outter = Integer.parseInt(o.getMaterial_id().substring(1));
		int result = self.compareTo(outter);
		return result;
	}

}/// -- class end
