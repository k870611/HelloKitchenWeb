package ControlServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import EntityComponents.Company;
import EntityComponents.Member;
import EntityComponents.Message;
import EntityComponents.Token;
import Factorys.CompanyFactory;
import Factorys.MemberFactory;
import Factorys.MessageFactory;
import Factorys.TokenFactory;
import Modules.SessionStamp;
import Utility.GeneralVarName;
import Utility.ProcessDate;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class CompanyMessageServlet
 */
@WebServlet("/CompanyMessageServlet")
public class CompanyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CompanyMessage(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CompanyMessage(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void CompanyMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MessageFactory mf = (MessageFactory) this.getServletContext().getAttribute(GeneralVarName.Web_MessageFactory);
		TokenFactory tf = (TokenFactory) this.getServletContext().getAttribute(GeneralVarName.Web_TokenFactory);
		
		String sendmsg =request.getParameter("msg");
		

		if(sendmsg != null && !sendmsg.isEmpty()){
			HttpSession mySession = request.getSession();
			String sender="";
			System.out.println("into success:"+sendmsg);

			if(((SessionStamp) mySession.getAttribute(GeneralVarName.Session_LoginAccount))!=null){
				SessionStamp st = (SessionStamp) mySession.getAttribute(GeneralVarName.Session_LoginAccount);
				sender = st.getLoginAccount();
			}else{
				sender=request.getParameter("sender");
			}
			
			int fId=1;
			String receiver=request.getParameter("receiver");
			String message=request.getParameter("contact_message");
			String sqlDate = ProcessDate.getCurrentLocalDateString();
			
			Message m = new Message(fId,sender,receiver,message,sqlDate);
			mf.sendMessage(m);
			
			
			if (tf.isExistByPK(m.getReceiver())) {
				Token myToken = (Token) tf.sreachByPK(m.getReceiver());
				callFirebaseMessages(myToken.getPhoneToken(), m.getSender());
			} 
			
			System.out.println(m.toString());
			
			
			out.println("<div id='alert' style='position: fixed;top:2%;left: 40%;min-width: 20%;z-index: 99999;padding: 15px;border: 1px solid transparent;border-radius: 4px;text-align:center;color: #000000;background-color:#808080;'></div>");
			out.println("<script type=\"text/javascript\">");
			out.println("document.getElementById('alert').innerHTML='表單送出成功';");
			out.println("setTimeout(function(){location.href='index.jsp'}, 2000 );");
			out.println("</script>");
			
			
		}else{
			String receiver=request.getParameter("receiver");
			mf.refreshByDataBase();
			String sender=request.getParameter("sender");//---站點 腳色
			System.out.println("receiver:"+receiver);
			System.out.println("sender:"+sender);

			String recName="";
			String senName="";
			
			CompanyFactory cf = (CompanyFactory) request.getServletContext().getAttribute(GeneralVarName.Web_CompanyFactory); 
	        MemberFactory memberf = (MemberFactory) request.getServletContext().getAttribute(GeneralVarName.Web_MemberFactory);

	        
	        System.out.println((cf.getAll()).toString());
	        System.out.println(cf.isExistByPK(sender));
	        
			if(cf.isExistByPK(sender)){
	        	Company ALcf= (Company)cf.sreachByPK(sender);
	        	senName=ALcf.getCompany_name();
	        	
				Member mb = memberf.sreachByPK(receiver);
				recName=mb.getMember_name();
				
				System.out.println("1."+recName+":"+senName);
	        }else{
				Member mb = memberf.sreachByPK(sender);
				senName = mb.getMember_name();
				
	        	Company ALcf= (Company)cf.sreachByPK(receiver);
	        	recName=ALcf.getCompany_name();
	        	System.out.println("2."+recName+":"+senName);
	        }
			
			
			
			ArrayList<Message> dataset =  (ArrayList<Message>)mf.getOldMixMessage(receiver, sender);
			
			String extra = ProcessDate.extraYMD(dataset.get(0).getTime());
			
			int timer=1;
			int Rectimer=1;
			
			System.out.println(receiver+":"+dataset.toString());
			
			out.write("<div class='direct-chat-messages'>"
					+ "<div class='chat-box-single-line'>"
					+ "<abbr class='timestamp'>"+ProcessDate.parseTime(dataset.get(0).getTime())+"</abbr></div>"
					+ "<div class='direct-chat-msg doted-border'>");
//Start---------------------------------------------------------------------------------------------------------			
			for(Message ms:dataset){
				//--- 畫線  ---
				if(extra.equals(ProcessDate.extraYMD(ms.getTime()))){
					
					
				}else{
					if(Rectimer==1){
						out.write("<div class='direct-chat-info clearfix'>"
								+ "<span class='direct-chat-timestamp pull-right'></span></div>");
						out.write("</div></div>");
					}
					if(Rectimer>=2){
						out.write("</div></div></div>");
					}
					
					out.write("<div class='direct-chat-messages'> "
							+ "<div class='chat-box-single-line'>"
							+ "<abbr class='timestamp'>"+ProcessDate.parseTime(ms.getTime())+"</abbr></div>"
							+ "<div class='direct-chat-msg doted-border'>");
					extra = ProcessDate.extraYMD(ms.getTime());
					Rectimer=1;
					timer=1;
				}
//--left----------------------------------------------------------------------------我是發送者//--我在右邊
				
				if(ms.getSender().equalsIgnoreCase(receiver)){
					if(Rectimer>=2){
						
						out.write("</div></div>"
								+ "<div class='direct-chat-msg doted-border'>");
					}
					
					if(timer==1){
						out.write("<div class='direct-chat-info clearfix'>"
								+ "<span class='direct-chat-name pull-left'>"+recName+"</span></div>");
						out.write("<div class='direct-chat-text' style='word-break:break-all;'>"+ms.getMessage()+"</div>");
					}else{
						out.write("<div class='direct-chat-text' style='word-break:break-all;'>"+ms.getMessage()+"</div>");
					}				

					timer++;
					Rectimer=1;
//right----------------------------------------------------------------
				}else{
					//---我的接收者--在左邊
					if(timer>=2){
						out.write("<div class='direct-chat-info clearfix'>"
								+ "<span class='direct-chat-timestamp pull-right'></span></div>");
					}				
					if(Rectimer==1){
						out.write("<div class='direct-chat-info clearfix'>"
								+ "<span class='direct-chat-name pull-right'>"+senName+"</span>"
								+ "<span class='direct-chat-reply-name'></span>");
						out.write("<div class='direct-chat-text2' style='word-break:break-all;'>"+ms.getMessage()+"</div>");

					}else{
						out.write("<div class='direct-chat-text2' style='word-break:break-all; margin-top:3%;'>"+ms.getMessage()+"</div>");
					}
					
					timer=1;
					Rectimer++;
				}
				
			}
//Over------------------------------------------------------------------------------------------------			
			if(Rectimer==1){
				out.write("<div class='direct-chat-info clearfix'>"
						+ "<span class='direct-chat-timestamp pull-right'></span></div>");
				out.write("</div></div>");
			}
			if(Rectimer>=2){
				out.write("</div></div></div>");
			}
			out.write("<div class='addmessage'></div>");
			out.write("<input type='hidden' id='receiver' value='"+receiver+"' />");
			
		}	
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
