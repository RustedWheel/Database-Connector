package nz.ac.auckland.mysql.connector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;


@Path("/connect")
public class RestEndpoint {

    Connector _databaseConnector;
    
    public RestEndpoint() {
    	_databaseConnector = new Connector();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.status(Status.OK).entity("OK").build();
    }
    
    @GET
    @Path("select")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response select(@QueryParam("id")String id,
    								@QueryParam("queryString")String queryString) {
    	
    	String fullQueryString = replaceWithID(queryString, id);
    	JSONObject result = _databaseConnector.executeQuery(fullQueryString);
    	return Response.status(Status.OK).entity(result.toString()).build();
    }
    
    private String replaceWithID(String query, String id) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("'");
    	sb.append(id);
    	sb.append("'");
    	return query.replace("$", sb.toString() );
    }
  
}
