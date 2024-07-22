package com.rkisuru.customer.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;

}
