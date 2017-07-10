package WebListeners;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Factorys.CompanyFactory;
import Factorys.CompanyPictureFactory;
import Factorys.MemberFactory;
import Factorys.MessageFactory;
import Factorys.RecipeFactory;
import Utility.GeneralVarName;
import Factorys.RecipeMaterialFactory;
import Factorys.RecipeMethodFactory;
import Factorys.TokenFactory;

/**
 * Application Lifecycle Listener implementation class InitServerListener
 *
 */
@WebListener
public class InitServerListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public InitServerListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sc) {
		// TODO Auto-generated method stub
		ServletContext webSc = sc.getServletContext();
		Enumeration<String> attNames = webSc.getAttributeNames();
		while (attNames.hasMoreElements()) {
			webSc.removeAttribute(attNames.nextElement());
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sc) {
		// TODO Auto-generated method stub

		ServletContext webSc = sc.getServletContext();
		MemberFactory mf = new MemberFactory();
		mf.refreshByDataBase();
		RecipeFactory rc = new RecipeFactory();
		rc.refreshByDataBase();
		CompanyFactory cf = new CompanyFactory();
		cf.refreshByDataBase();
		RecipeMaterialFactory rmaterial = new RecipeMaterialFactory();
		rmaterial.refreshByDataBase();
		RecipeMethodFactory rmethod = new RecipeMethodFactory();
		rmethod.refreshByDataBase();
		CompanyPictureFactory cp = new CompanyPictureFactory();
		cp.refreshByDataBase();
		MessageFactory msgf = new MessageFactory();
		msgf.refreshByDataBase();
		TokenFactory tf = new TokenFactory();
		tf.refreshByDataBase();

		// ----- web層級放置區 ----
		webSc.setAttribute(GeneralVarName.Web_MemberFactory, mf);
		webSc.setAttribute(GeneralVarName.Web_RecipeFactory, rc);
		webSc.setAttribute(GeneralVarName.Web_CompanyFactory, cf);
		webSc.setAttribute(GeneralVarName.Web_RecipeMaterialFactory, rmaterial);
		webSc.setAttribute(GeneralVarName.Web_RecipeMethodFactory, rmethod);
		webSc.setAttribute(GeneralVarName.Web_CompanyPictureFactory, cp);
		webSc.setAttribute(GeneralVarName.Web_MessageFactory, msgf);
		webSc.setAttribute(GeneralVarName.Web_TokenFactory, tf);
		
	}// --- init end

}// -- class end
