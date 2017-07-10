package Factorys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AbstractAncestor.AccountManager;
import EntityComponents.Message;
import Modules.MessageKey;
import Utility.GeneralVarName;

public class MessageFactory extends AccountManager implements java.io.Serializable {
/* MessageKey (送信者ID 接收者ID TIME) */
	private static final long serialVersionUID = 1L;
	private HashMap<MessageKey, Message> hereDataSet = new HashMap<MessageKey, Message>();
	private Connection conn;
	private HashMap<MessageKey, Message> newMessageSet = new HashMap<MessageKey, Message>();
    	
	// --------- construct -------
	public MessageFactory() {

	}
	
	@Override
	public boolean Insert(Object o) throws SQLException {
		// TODO Auto-generated method stub
		Message m = (Message) o;
		conn = super.getSQLConnection();
		String sql = "INSERT INTO foods.tmessage (sender, receiver, message, time) VALUES (?, ?, ?, ?);";
		PreparedStatement myPS = conn.prepareStatement(sql);
		myPS.setNString(1, m.getSender());
		myPS.setNString(2, m.getReceiver());
		myPS.setNString(3, m.getMessage());
		myPS.setNString(4, m.getTime());
		boolean isup = myPS.execute();
		myPS.close();
		super.destorySQLConnection(conn);
		System.out.println("訊息新增成功");
		return isup;
	}

	@Override
	public int Delete(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Update(Object oldDataObj, Object updateDataObj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExistByPK(Object o) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public Object sreachByPK(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAll() {
		// TODO Auto-generated method stub
		ArrayList<Message> messSet = new ArrayList<Message>();
		for(MessageKey mk : this.hereDataSet.keySet()){
			messSet.add(this.hereDataSet.get(mk));
		}
		return messSet;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		Message m = (Message) o;// -- 不保險寫法--
		if (isExistByPK(o)) {
			System.out.println("目前訊息工廠資料集有該筆資料");
			return false;
		} else {
			MessageKey mk = new MessageKey(m.getSender(),m.getReceiver(),m.getTime());
			this.hereDataSet.put(mk, m);
			System.out.println("寫入目前訊息工廠資料集");
			return true;
		}
	}

	@Override
	public void refreshByDataBase() {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			conn = super.getSQLConnection();
			String sql = "Select * From tmessage"; // --偷懶寫法
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			this.hereDataSet.clear(); // -- 洗白
			// --- 重新注入資料 從現行資料庫 --
			while (rs.next()) {
				Message tempM = new Message(rs.getInt("fId"), rs.getNString("sender"), rs.getNString("receiver"),
						rs.getNString("message"), rs.getNString("time"));
				/*sid rid time*/
				MessageKey mk = new MessageKey(rs.getNString("sender"),rs.getNString("receiver"),rs.getNString("time"));
				this.hereDataSet.put(mk, tempM);
				
				count++;
			}
			rs.close();
			super.destorySQLConnection(conn);
			System.out.println("訊息工廠資料重洗 ==> 筆數 " + count);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void refresh(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setJSON(String jsonString) {
		// TODO Auto-generated method stub
		
	}

	/* 寄訊息  */
	public void sendMessage(Message message) throws SQLException{
		this.Insert(message);
		MessageKey mk = new MessageKey(message.getSender(),message.getReceiver(),message.getTime());
		this.newMessageSet.put(mk, message);
	}
	/*找出寄給我的名單(已讀的) 寄給我的名單*/
	public Set getSendToMeNames(String myId){
		Set mySendNames = new HashSet();
		for(MessageKey mk:this.hereDataSet.keySet()){
			if(mk.findByReceiverId(myId)){
				mySendNames.add(mk.getSender());
			}
		}
		return mySendNames;
	}
	/*找出我有寄出訊息的名單(對方已讀) 寄給別人名單*/
	public Set getSendToOtherNames(String myId){
		Set mySendNames = new HashSet();
		for(MessageKey mk:this.hereDataSet.keySet()){
			if(mk.findBySenderId(myId)){
				mySendNames.add(mk.getReceiver());
			}
		}
		return mySendNames;
	}
	/*取得新寄給我的名單 (我未讀) */
	public Set getSendToMeNews(String myId){
		Set mySendNames = new HashSet();
		for(MessageKey mk:this.newMessageSet.keySet()){
			if(mk.findByReceiverId(myId)){
				mySendNames.add(mk.getSender());
			}
		}
		return mySendNames;
	}
	/*找出我有寄出訊息的名單(對方未讀) 寄給別人名單*/
	public Set getSendToOtherNews(String myId){
		Set mySendNames = new HashSet();
		for(MessageKey mk:this.newMessageSet.keySet()){
			if(mk.findBySenderId(myId)){
				mySendNames.add(mk.getReceiver());
			}
		}
		return mySendNames;
	}
	/* 取得已讀舊訊息 */
	public ArrayList<Message> getOldMessage(String sender,String receiver){
		ArrayList<Message> oldmessage = new ArrayList<Message>();
		for(MessageKey mk: this.hereDataSet.keySet()){
			if(mk.findBySidAndRid(sender, receiver)){
				oldmessage.add(hereDataSet.get(mk));
			}
		}
		return oldmessage;
	}
	/*取得最新未讀訊息*/
	public ArrayList<Message> getNewMessage(String sender,String receiver){
		ArrayList<Message> newmessage = new ArrayList<Message>();
		HashMap<MessageKey, Message> temp = (HashMap<MessageKey, Message>) newMessageSet.clone();
		for(MessageKey mk: temp.keySet()){
			if(mk.findBySidAndRid(sender, receiver)){
				newmessage.add(newMessageSet.get(mk));
				this.hereDataSet.put(mk, newMessageSet.get(mk));
				newMessageSet.remove(mk);
			}
		}
		
		return newmessage;
	}
	/* 取得混合訊息(old) */
	public ArrayList<Message> getOldMixMessage(String sender,String receiver){
		ArrayList<Message> oldmixmessage = new ArrayList<Message>();
		for(MessageKey mk: this.hereDataSet.keySet()){
			if(mk.findMixMessage(sender, receiver)){
				oldmixmessage.add(hereDataSet.get(mk));
			}
		}
		Collections.sort(oldmixmessage);
		return oldmixmessage;
	}
	public ArrayList<Message> getNewMixMessage(String sender,String receiver){
		ArrayList<Message> newMixmessage = new ArrayList<Message>();
		HashMap<MessageKey, Message> temp = (HashMap<MessageKey, Message>) newMessageSet.clone();
		for(MessageKey mk: temp.keySet()){
			if(mk.findMixMessage(sender, receiver)){
				newMixmessage.add(newMessageSet.get(mk));
				this.hereDataSet.put(mk, newMessageSet.get(mk));
				newMessageSet.remove(mk);
			}
		}
		Collections.sort(newMixmessage);
		return newMixmessage;
	}
	
	/*搜尋出寄件人收件人雙方ID留言訊息*/
	public String AndroidSearch(String idOne, String idTwo) {
		JSONArray searchJsonArray = new JSONArray();
		try {
			conn = super.getSQLConnection();
			String sql = "select * from tmessage where ( sender = ? and receiver = ? ) or ( sender = ? and receiver = ? )";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, idOne);
			ps.setNString(2, idTwo);
			ps.setNString(3, idTwo);
			ps.setNString(4, idOne);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(GeneralVarName.Android_JSON_Key_Message_fId, rs.getInt("fId")); 
				jsonObject.put(GeneralVarName.Android_JSON_Key_Message_Sender, rs.getNString("sender"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Message_Receiver, rs.getNString("receiver"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Message_Message, rs.getString("message"));
				jsonObject.put(GeneralVarName.Android_JSON_Key_Message_Time, rs.getNString("time"));
				searchJsonArray.put(jsonObject);
			}
			rs.close();
			super.destorySQLConnection(conn);
			return searchJsonArray.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchJsonArray.toString();
	}
	
	/*搜尋留言給某廠商的會員ID*/
	public String AndroidSearchMemberID(String company) {
		JSONArray searchJsonArray = new JSONArray();
		try {
			conn = super.getSQLConnection();
			String sql = "select distinct sender from tmessage where receiver = ? order by time desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, company);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				searchJsonArray.put(rs.getString("sender"));
			}
			rs.close();
			super.destorySQLConnection(conn);
			return searchJsonArray.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return searchJsonArray.toString();
	}
	
}//---- class end 
