package com.mpop.ordering.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponsePayload {

    private Date timestamp = new Date();
    private String itemName;

    private Integer quantity;

    private String userEmail;

}
