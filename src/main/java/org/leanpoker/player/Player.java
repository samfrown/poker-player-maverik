package org.leanpoker.player;

import com.google.gson.JsonElement;


public class Player {


    static final String VERSION = "Maverik";

    public static int betRequest(JsonElement request) {
        return 1000;
    }

    public static void showdown(JsonElement game) {
    }


    static int fff(JsonElement req) {
        int round = req.getAsJsonObject().get("round").getAsInt();
        int maxBet = req.getAsJsonObject().get("current_by_in").getAsInt();

        int ourBet = 200;

        if (round == 0) {

        } else if (round < 3) {
            ourBet = 500;
        } else {
            ourBet = 1000;
        }

        if (maxBet > ourBet) {
            return maxBet;
        } else {
            return ourBet;
        }

    }
}