package Wines.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Wines.Dao.*;
import Wines.model.*;

@WebServlet("/findwines")
public class FindWines extends HttpServlet{
	protected WinesDao winesDao;
	
	@Override
	public void init() throws ServletException {
		winesDao = WinesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Wines> wine1 = new ArrayList<Wines>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String wineryName = req.getParameter("wineryName");
        if (wineryName == null || wineryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid wine title.");
        } else {
        	// Retrieve Wines, and store as a message.
        	try {
            	wine1 = winesDao.getWinesByWineryName(wineryName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + wineryName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousWinaryName", wineryName);
        }
        req.setAttribute("wine1", wine1);
        
        req.getRequestDispatcher("/FindWines.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Wines> wine1 = new ArrayList<Wines>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String wineryName = req.getParameter("wineryName");
        if (wineryName == null || wineryName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Wines, and store as a message.
        	try {
            	wine1 = winesDao.getWinesByWineryName(wineryName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + wineryName);
        }
        req.setAttribute("wine1", wine1);
        
        req.getRequestDispatcher("/FindWines.jsp").forward(req, resp);
    }
}
