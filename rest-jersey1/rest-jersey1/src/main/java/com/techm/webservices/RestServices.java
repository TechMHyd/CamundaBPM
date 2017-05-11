package com.techm.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("restServ")
public class RestServices {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("meth1/{var1}")
    public String appendOrange(@PathParam("var1")  String var1) {
    	String result="";
    	if(var1.equalsIgnoreCase("Orange")){
    		result= "green";
    	}
    	return result;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("meth2/{var2}")
    public String appendGreen(@PathParam("var2")  String var2) {
    	String result="";
    	if(var2.equalsIgnoreCase("green")){
    		result="blue";
    	}
        return result;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("meth3/{var3}")
    public String appendBlue(@PathParam("var3")  String var3) {
    	String result="";
    	if(var3.equalsIgnoreCase("blue")){
    		result="yellow";
    	}
        return result;
    }
    
}
