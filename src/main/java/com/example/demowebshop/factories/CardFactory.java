package com.example.demowebshop.factories;

import com.example.demowebshop.config.CardConfig;
import com.example.demowebshop.model.Card;

public class CardFactory {

    private CardConfig cardConfig;

    public CardFactory(CardConfig cardConfig) {
        this.cardConfig = cardConfig;
    }

    public Card getDefaultCard() {
        Card card = new Card();
        card.setCardNumber(cardConfig.getCardNumber());
        card.setCardType(cardConfig.getCardType());
        card.setCardholderName(cardConfig.getCardholderName());
        card.setExpirationMonth(cardConfig.getExpirationMonth());
        card.setExpirationYear(cardConfig.getExpirationYear());
        card.setCvv(cardConfig.getCvv());

        return card;
    }
}
