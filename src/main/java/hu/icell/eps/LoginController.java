package hu.icell.eps;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)    
    public @ResponseBody String login(@RequestBody String json) {
    	
//    	if ( du.getUsername().equals(jo.getString("username")) && du.getPassword().equals(jo.getString("password")) ) {
//    		return du.toString();
//    	} else {
//    		return null;
//    	}
    	
    	JSONObject obj = new JSONObject(json);
    	if ( obj.getString("username").equals("validuser") && obj.getString("password").equals("validpass") ) {
        	obj.remove("password");
    		obj.put("userId", "33");   		
    	} else {
    		obj.remove("username");
    		obj.remove("password");
    		obj.put("errorMessage", "Please send a valid user credentials.");
    	}

    	return obj.toString(); 	    	

    }
}
