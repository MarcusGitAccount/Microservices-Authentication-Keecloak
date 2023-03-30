package com.mpop.ordering.client;

import com.mpop.ordering.configuration.ClientCredentialsKeycloakConfiguration;
import com.mpop.ordering.payload.ClientCredentialsRequestPayload;
import com.mpop.ordering.payload.ClientCredentialsResponsePayload;
import feign.Headers;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        configuration = ClientCredentialsKeycloakConfiguration.class,
        name = "clientCredentialsKeycloak",
        url = "${KEYCLOAK_SERVER_URL:localhost:8080/auth}")
public interface ClientCredentialsKeycloakClient {

    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ClientCredentialsResponsePayload getCredentials(@PathVariable("realm") @NotEmpty String realm,
                                                    @RequestBody ClientCredentialsRequestPayload body);
}
