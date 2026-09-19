package com.app.ecom.DTO;

import com.app.ecom.Model.Address;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
}
