package com.app.ecom.DTO;

import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String zipCode;
    private String city;
    private String state;
    private String country;
}
