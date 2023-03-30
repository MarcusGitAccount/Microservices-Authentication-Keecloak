package com.mpop.ordering.client;

import com.mpop.ordering.payload.EmailRequestPayload;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        configuration = FeignClientsConfiguration.class,
        name = "notificationClient",
        url = "${NOTIFICATION_HOST:localhost}:${NOTIFICATION_PORT:8082}")
public interface NotificationClient {

    @PostMapping("/notification/email")
    @Headers("Content-Type: application/json")
    void sendEmail(@RequestBody EmailRequestPayload emailRequest,
                   @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization);
}
