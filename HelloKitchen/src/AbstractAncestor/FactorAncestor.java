package AbstractAncestor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DefineInterface.BaseDAOmanipulate;
import Modules.Sequencetor;
import Modules.UpdateSet;
import Utility.MySqlJDNIConnect;
import Utility.ProcessDate;

public abstract class FactorAncestor implements BaseDAOmanipulate {

	private MySqlJDNIConnect sqlComponents = new MySqlJDNIConnect();
	private Connection conn;

	// --------------- 資料庫連線 區域 -------------------------
	protected Connection getSQLConnection() throws SQLException {
		this.conn = sqlComponents.getConnection();
		//System.out.println("取得SQL連線");
		return this.conn;
	}

	protected void destorySQLConnection(Connection conn) throws SQLException {
		sqlComponents.closeConnection(conn);
		//System.out.println("銷毀SQL連線");
	}

	// ---------- 取得更新字串 ---------
	protected String getUpdateString(String sqlhead, ArrayList<UpdateSet> upset) {
		StringBuilder sb = new StringBuilder(sqlhead);
		int lastcount = upset.size() - 1;
		for (int index = 0; index < upset.size(); index++) {
			UpdateSet temp = upset.get(index);
			sb.append(temp.getFieldName() + " = ?");
			if (lastcount != index) {
				sb.append(",");
			}
		}

		return sb.toString();
	}

	// ------ id 產生器 -----
	public String IDGenerator() {
		String gid = ProcessDate.getCurrentLocalDateString();
		Sequencetor sq = Sequencetor.getSequencetorIntance();
		int counter = sq.getCounter();
		return String.format("%s%03d", gid, counter);
	}

	/**
	 * (檔案重新命名)
	 * 
	 * @param filename
	 *            上傳檔名
	 * @param replace
	 *            替換字串
	 * @return 替換後詳細檔名
	 */
	public String replaceFileName(String filename, String replace) {
		String[] sp = filename.split("\\.");
		String reName = String.format("%s%s%s", replace, ".", sp[sp.length - 1]);
		return reName;
	}

	public int deleteAboutRecipe(String tablename,String RecipeId) throws SQLException{
		Connection conn = this.getSQLConnection();
		String sql = "Delete from "+tablename+" where recipe_id = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, RecipeId);
		int rows = ps.executeUpdate();
		this.destorySQLConnection(conn);
		return rows;
	}
}/// - class end
