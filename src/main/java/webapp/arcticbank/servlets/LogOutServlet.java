package webapp.arcticbank.servlets;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import webapp.arcticbank.model.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2696676700455754923L;
	
	Logger logger = Logger.getLogger(LogOutServlet.class);
	
	User user;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	if(session != null){
    		
    		//removing user from ServletContext
    		if(session.getAttribute("current_user") != null){
    		user = (User) session.getAttribute("current_user");
    		request.getServletContext().removeAttribute(user.getEmail());
    		}
    		
    		session.invalidate();
    		logger.info("session invalidated succesfuly");
    	}
    	request.getRequestDispatcher("/WelcomePage.jsp").forward(request, response);
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/ArcticBank/WelcomePage.jsp");
	}
}