package com.sunrise.dental.api;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.sunrise.dental.controller.AppContext;
@Path("/treatments") @Produces(MediaType.APPLICATION_JSON) public class TreatmentResource {
    @GET public Response all() {
        return Response.ok(AppContext.TREATMENT_DAO.findAll()).build();
    }
}
