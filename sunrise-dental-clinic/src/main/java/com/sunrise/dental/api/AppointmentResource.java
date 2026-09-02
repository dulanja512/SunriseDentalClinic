package com.sunrise.dental.api;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import com.sunrise.dental.controller.AppContext;
@Path("/appointments") @Produces(MediaType.APPLICATION_JSON) public class AppointmentResource {
    @GET public Response all() {
        return Response.ok(AppContext.APPT.all()).build();
    }
    @GET @Path("/{number}") public Response one(@PathParam("number")String n) {
        try {
            return Response.ok(AppContext.APPT.find(n)).build();
        } catch(Exception e) {
            return Response.status(404).entity(Collections.singletonMap("error",e.getMessage())).build();
        }
    }
}
