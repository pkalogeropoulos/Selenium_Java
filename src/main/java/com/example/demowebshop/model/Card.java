package com.example.demowebshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String cardType;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;
    private String cardholderName;
}
