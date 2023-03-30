package com.mpop.ordering;

import com.mpop.ordering.payload.OrderResponsePayload;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service
public class OrderingService {

    private SecureRandom secureRandom = new SecureRandom();

    OrderResponsePayload createOrder(String itemName, String userEmail) {
        return new OrderResponsePayload(
                new Date(),
                itemName,
                secureRandom.nextInt(1, 10),
                userEmail
        );
    }
}
