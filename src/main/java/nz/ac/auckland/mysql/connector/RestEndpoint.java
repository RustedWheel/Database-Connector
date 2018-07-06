package nz.ac.auckland.mysql.connector;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/connect")
public class RestEndpoint {

    Connector _databaseConnector;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.status(Status.OK).entity("OK").build();
    }
    
    @GET
    @Path("select")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response select(@FormParam("id")String id,
    								@FormParam("queryString")String queryString) {
    	
    	String fullQueryString = replaceWithID(queryString, id);
    	JSONArray result = _databaseConnector.executeQuery(queryString);
    	JSONObject jo = new JSONObject();
    	jo.put("patient", result);
    	return Response.status(Status.OK).entity(jo.toString()).build();
    }
    
    private String replaceWithID(String query, String id) {
    	return "";
    }
  
}
