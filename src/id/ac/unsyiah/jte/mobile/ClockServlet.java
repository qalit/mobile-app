package id.ac.unsyiah.jte.mobile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class ClockServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,
						HttpServletResponse resp)
				throws IOException, ServletException{
		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSS");
		dtf.setTimeZone(new SimpleTimeZone(0, ""));
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String loginurl = userService.createLoginURL("/");
		String logouturl = userService.createLogoutURL("/logout");
		
		req.setAttribute("user", user);
		req.setAttribute("loginurl", loginurl);
		req.setAttribute("logouturl", logouturl);
		req.setAttribute("currentTime", dtf.format(new Date()));
		
		Entity userPref = null;
		if(user != null){
			DatastoreService data = DatastoreServiceFactory.getDatastoreService();
			MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
			
			String cacheKey = "UserPref : " + user.getUserId();
			userPref = (Entity) memcache.get(cacheKey);
			if(userPref == null){
				Key userKey = KeyFactory.createKey("UserPref", user.getUserId());
				try{
					userPref = data.get(userKey);
					memcache.put(cacheKey, userPref);
				} catch (EntityNotFoundException e){
					
				}
			}
		}
		
		if(userPref != null){
			int tzOffset = ((Long) userPref.getProperty("tz_offset")).intValue();
			dtf.setTimeZone(new SimpleTimeZone(tzOffset * 60 * 60 *60 * 1000, ""));
			req.setAttribute("tzOffset", tzOffset);
		} else {
			req.setAttribute("tzOffset", 0);
		}
		
		resp.setContentType("text/html");
		
		RequestDispatcher jsp = req.getRequestDispatcher("time.jsp");
		jsp.forward(req, resp);
	}
	
}
