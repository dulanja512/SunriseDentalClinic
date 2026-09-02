package com.sunrise.dental.api;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;
@ApplicationPath("/api") public class RestApplication extends ResourceConfig {
    public RestApplication() {
        packages("com.sunrise.dental.api");
    }
}
