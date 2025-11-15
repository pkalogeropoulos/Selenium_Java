package com.example.demowebshop.model;

import com.example.demowebshop.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private Gender gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
