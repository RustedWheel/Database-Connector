package nz.ac.auckland.mysql.connector;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/upload")
public class RestEndpoint {

    Connector _databaseConnector;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.status(Status.OK).entity("OK").build();
    }

}
