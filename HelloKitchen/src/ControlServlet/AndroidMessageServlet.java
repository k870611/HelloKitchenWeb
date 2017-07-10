package ControlServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import EntityComponents.Message;
import EntityComponents.Token;
import Factorys.MessageFactory;
import Factorys.TokenFactory;
import Utility.GeneralVarName;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class AndroidMessageServlet
 */
@WebServlet("/AndroidMessageServlet")
public class AndroidMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	ServletContext sc;
	MessageFactory msgf;
	TokenFactory tf;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndroidMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidMessageServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processAndroidMessageServlet(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processAndroidMessageServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
				
		out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		String jsonString = reader.readLine();
		System.out.println(jsonString);
				
		sc = this.getServletContext();
		msgf = (MessageFactory) sc.getAttribute(GeneralVarName.Web_MessageFactory);
		tf = (TokenFactory) sc.getAttribute(GeneralVarName.Web_TokenFactory);
		
		JSONObject myJsonObject = new JSONObject(jsonString);
		
		if (myJsonObject.getString("action").equals("downloadallmessages")) {
			downloadMessage(myJsonObject);
		} else if  (myJsonObject.getString("action").equals("uploadnewmessage")){
			uploadMessage(myJsonObject);
		} else if (myJsonObject.getString("action").equals("downloadallusers")); {
			downloadUsers(myJsonObject);
		}
		
		out.close();
		
	}

	/*搜尋留言給某廠商的會員ID*/
	private void downloadUsers(JSONObject myJsonObject) throws JSONException {
		// TODO Auto-generated method stub
		String messageJsonArray = msgf.AndroidSearchMemberID(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Receiver));
		
		ifMessageJsonArray(messageJsonArray);
	}

	/*新增新訊息*/
	private void uploadMessage(JSONObject myJsonObject) throws JSONException {
		// TODO Auto-generated method stub
		Message myMessage = new Message();
		myMessage.setSender(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Sender));
		myMessage.setReceiver(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Receiver));
		myMessage.setMessage(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Message));
		myMessage.setTime(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Time));
		JSONObject myJsonObj = new JSONObject();
		try {
			msgf.Insert(myMessage);
			if (tf.isExistByPK(myMessage.getReceiver())) {
				Token myToken = (Token) tf.sreachByPK(myMessage.getReceiver());
				callFirebaseMessages(myToken.getPhoneToken(), myMessage.getSender());
			} 
			myJsonObj.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Success);
			out.write(myJsonObj.toString());
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			myJsonObj.put(GeneralVarName.Android_JSON_Key_Information, GeneralVarName.Android_JSON_Value_Fail);
			out.write(myJsonObj.toString());
			out.close();
			e.printStackTrace();
		}
		out.close();
	}

	/*搜尋新訊息*/
	private void downloadMessage(JSONObject myJsonObject) throws JSONException {
		String messageJsonArray = msgf.AndroidSearch(myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Sender), 
				myJsonObject.getString(GeneralVarName.Android_JSON_Key_Message_Receiver));
		
		ifMessageJsonArray(messageJsonArray);
	}
	
	/*判斷回傳值是否空值*/
	private void ifMessageJsonArray(String messageJsonArray) {
		if (messageJsonArray.equals("[]")) {
			out.write(GeneralVarName.Android_JSON_Value_Fail);
			out.close();
		} else {
			out.write(messageJsonArray);
			out.close();
		}
				
		out.close();
	}
	
	/*callFirebase FCM*/
	private void callFirebaseMessages(final String toToken, final String sender) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("body", "來自" + sender + "一則新訊息");
                    jsonObject.put("sound", "default");
                    JSONObject json = new JSONObject();
                    json.put("to", toToken);
                    json.put("notification", jsonObject);
                    json.put("data", jsonObject);

                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .url("https://fcm.googleapis.com/fcm/send")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", GeneralVarName.FirebaseApiKey)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
						
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
	
}
