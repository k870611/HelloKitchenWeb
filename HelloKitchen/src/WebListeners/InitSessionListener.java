package WebListeners;

import java.util.Enumeration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Modules.SessionStamp;
import Utility.GeneralVarName;

/**
 * Application Lifecycle Listener implementation class InitSessionListener
 *
 */
@WebListener
public class InitSessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public InitSessionListener() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent hse)  { 
         // TODO Auto-generated method stub
    	HttpSession mySession  = hse.getSession();
    	SessionStamp st = new SessionStamp("nonelog");
    	mySession.setAttribute(GeneralVarName.Session_LoginAccount, st);
    	System.out.println("login set attribute  : "+ st.getLoginAccount());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent hse)  { 
         // TODO Auto-generated method stub
    	//--- 連線失效--清除該連線的暫存
    	
    	HttpSession mySession  = hse.getSession();
    	Enumeration<String> attributeNames = mySession.getAttributeNames();
    	    	while(attributeNames.hasMoreElements()){
    		mySession.removeAttribute(attributeNames.nextElement());
    	}
    }
	
}
