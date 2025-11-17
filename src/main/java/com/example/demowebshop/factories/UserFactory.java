package com.example.demowebshop.factories;

import com.example.demowebshop.config.AppConfig;
import com.example.demowebshop.config.AuthConfig;
import com.example.demowebshop.enums.Gender;
import com.example.demowebshop.model.User;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UserFactory {

    private AuthConfig authConfig;

    public UserFactory(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }

    public User getDefaultUser() {
        return User.builder()
                .gender(randomGender())
                .firstName("TestName")
                .lastName("TestSurname")
                .email(authConfig.getDefaultUsername())
                .password(authConfig.getDefaultPassword())
                .build();
    }

    // Generates a fully random but valid user
    public User createRandomUser() {
        String random = UUID.randomUUID().toString().substring(0, 8);

        return User.builder()
                .gender(randomGender())
                .firstName("Name" + random)
                .lastName("Surname" + random)
                .email(getRandomEmail())
                .password("Pass!" + random)
                .build();
    }

    public String getRandomEmail() {
        return String.format("demowebshop%s%s", String.valueOf(Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L).replaceAll("\\D", ""), "@yopmail.com");
    }

    public Gender randomGender() {
        return ThreadLocalRandom.current().nextBoolean()
                ? Gender.MALE
                : Gender.FEMALE;
    }
}
