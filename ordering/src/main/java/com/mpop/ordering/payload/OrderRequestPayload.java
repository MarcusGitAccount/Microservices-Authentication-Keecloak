package com.mpop.ordering.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestPayload {

    @NotNull
    @NotEmpty
    private String itemName;

    @NotNull
    @NotEmpty
    private String userEmail;

}
