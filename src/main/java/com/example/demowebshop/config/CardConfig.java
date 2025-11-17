package com.example.demowebshop.config;

import lombok.Getter;

import java.util.Properties;

@Getter
public class CardConfig {

    private String cardType;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;
    private String cardholderName;

    public CardConfig(Properties props) {
        cardType = props.getProperty("card.type");
        cardNumber = props.getProperty("card.number");
        expirationMonth = props.getProperty("card.expiration.month");
        expirationYear = props.getProperty("card.expiration.year");
        cvv = props.getProperty("card.cvv");
        cardholderName = props.getProperty("card.cardholder");
    }
}
