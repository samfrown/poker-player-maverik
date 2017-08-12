package org.leanpoker.player;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(JsonArray cardsJson) {
        for(int i = 0; i < cardsJson.size(); i++) {
            cards.add(new Card(cardsJson.get(i).getAsJsonObject().get("suit").getAsString(),
                               cardsJson.get(i).getAsJsonObject().get("rank").getAsString()));
        }
    }

    public  List<Card> getCarts() {
        return cards;
    }

    public int getRating() {
        int rating  = 0;
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);

        if (card1.getRank() == card2.getRank()) {
            rating = 2;
        }

        if (card1.getSuit().equals(card2.getSuit())) {
            rating = 1;
        }

        int rank = card1.getRank();
        int rank1 = card2.getRank();
        if (rank > 12 || rank1 > 12) {
            rating = 1;
        }

        System.err.println("Maverik " + Integer.toString(rating));

        return rating;
    }
}
