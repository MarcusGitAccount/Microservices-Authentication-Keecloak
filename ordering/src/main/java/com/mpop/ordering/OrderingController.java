package com.mpop.ordering;

import com.mpop.ordering.client.ClientCredentialsKeycloakClient;
import com.mpop.ordering.client.NotificationClient;
import com.mpop.ordering.payload.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderingController {

    private static final String CLIENT_CREDENTIALS_GRANT_TYPE = "client_credentials";

    private static final String PASSWORD_GRANT_TYPE = "password";

    private final OrderingService orderingService;

    private final NotificationClient notificationClient;

    private final ClientCredentialsKeycloakClient credentialsKeycloakClient;

    @Value("${KEYCLOAK_REALM:services-realm}")
    private String realm;

    @Value("${KEYCLOAK_CLIENT_ID:id}")
    private String clientId;

    @Value("${KEYCLOAK_CLIENT_SECRET:secret}")
    private String clientSecret;

    @PostMapping
    public ResponseEntity<OrderResponsePayload> createOrder(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
            @Validated @RequestBody OrderRequestPayload payload) {

        String[] tokens = authorization.split(":");
        String username = tokens[0];
        String password = tokens[1];

        OrderResponsePayload responsePayload = this.orderingService.createOrder(
                payload.getItemName(),
                payload.getUserEmail()
        );

        ClientCredentialsRequestPayload credentialsPayload = new ClientCredentialsRequestPayload(
                clientId, clientSecret, PASSWORD_GRANT_TYPE, username, password);

        log.info(credentialsPayload.toString());

        try {
            ClientCredentialsResponsePayload credentials = credentialsKeycloakClient.getCredentials(realm, credentialsPayload);

            log.info(credentials.getAccessToken());
            log.info("Notification client call");

            notificationClient.sendEmail(
                    new EmailRequestPayload(payload.getUserEmail()),
                    "Bearer " + credentials.getAccessToken());
        }
        catch(FeignException ex) {
            log.error("Error while trying to reach the notification client {}", ex, ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(responsePayload);
    }
}
