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
            cards.add(new Card(cardsJson.getAsJsonObject().get("suit").getAsString(),
                               cardsJson.getAsJsonObject().get("rank").getAsString()));
        }
    }

    public  List<Card> getCarts() {
        return cards;
    }

    public int getRating() {
        return 1;
    }
}
