package org.example;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Optional;

@Path("/")
public class HomeResource {

    @Inject
    Template home;

    @Inject
    JsonWebToken jwt;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() {
        Optional<String> userName = Optional.ofNullable(jwt.getName());

        // Посилання на інші мікросервіси
        String bookingServiceUrl = "http://localhost:8083/q/swagger-ui/"; // змінити на реальні порти
        String userServiceUrl = "http://localhost:8081/q/swagger-ui/";
        String eventServiceUrl = "http://localhost:8082/q/swagger-ui/";

        return home.data("userName", userName)
                .data("bookingServiceUrl", bookingServiceUrl)
                .data("userServiceUrl", userServiceUrl)
                .data("eventServiceUrl", eventServiceUrl);
    }
}
