package DefineInterface;

import java.sql.SQLException;

public interface BaseDAOmanipulate {
	//---- CRUD Sreach--
	public boolean Insert(Object o) throws SQLException;
	public int Delete(Object o);
	public int Update(Object oldDataObj,Object updateDataObj);
	//-------------------------------
	public boolean isExistByPK(Object o);
	public Object sreachByPK(Object o);
	public Object getAll();
	//------------------------------
	public boolean add(Object o);
	public void refreshByDataBase();
	public void refresh(Object o);
	public boolean remove(Object o);
		
	public void setJSON(String jsonString);
}
