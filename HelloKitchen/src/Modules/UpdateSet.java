package Modules;

public class UpdateSet implements java.io.Serializable{
//--- field -----
	private String fieldName;
	private Object updateValue;
	private String dataType;
 	
	//-- construct ----
	public UpdateSet(String fieldName,Object updateValue,String dataType){
		this.fieldName = fieldName;
		this.updateValue = updateValue;
		this.dataType = dataType;
	}
	public UpdateSet(){
		
	}
   //------------------------------------
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getUpdateValue() {
		return updateValue;
	}

	public void setUpdateValue(Object updateValue) {
		this.updateValue = updateValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}//--- class end
