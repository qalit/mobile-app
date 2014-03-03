package id.ac.unsyiah.jte.mobile;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.*;


@SuppressWarnings("serial")
public class PrefServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,
						HttpServletResponse respons)
			throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		DatastoreService data = DatastoreServiceFactory.getDatastoreService();
		Key userKey = KeyFactory.createKey("UserPref", user.getUserId());
		Entity userPref = new Entity(userKey);
		MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
		String cacheKey = "UserPref : " + user.getUserId();
				
		try{
			int tzOffset = new Integer(request.getParameter("tz_offset")).intValue();
			
			userPref.setProperty("tz_offset", tzOffset);
			userPref.setProperty("user", user);
			data.put(userPref);
			memcache.delete(cacheKey);
			
		} catch (NumberFormatException nfe){
			
		}
		respons.sendRedirect("/");
		
	}
	
}
