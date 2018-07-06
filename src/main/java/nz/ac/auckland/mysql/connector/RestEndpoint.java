package nz.ac.auckland.mysql.connector;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/connect")
public class RestEndpoint {

    Connector _databaseConnector;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.status(Status.OK).entity("OK").build();
    }
    
    @GET
    @Path("selectPatient")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response selectByPatient(@QueryParam("pid")String pid,
    								@QueryParam("queryString")String queryString) {
    	
    	String result = _databaseConnector.executeQuery(queryString);
    	return Response.status(Status.OK).entity(result).build();
    }
    
    @GET
    @Path("selectEncounter")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response selectByEncounter(@QueryParam("eid")String eid,
    								@QueryParam("queryString")String queryString) {
    	
    	String result = _databaseConnector.executeQuery(queryString);
    	return Response.status(Status.OK).entity(result).build();
    }
  
}
