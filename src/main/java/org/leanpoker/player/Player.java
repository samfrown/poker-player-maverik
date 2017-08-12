package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class Player {


    static final String VERSION = "Maverik";

    public static int betRequest(JsonElement request) {
        return fff(request);
    }

    public static void showdown(JsonElement game) {
    }


    static int fff(JsonElement req) {
        try {
            System.err.println(req.toString());
            int round = req.getAsJsonObject().get("round").getAsInt();
            int maxBet = req.getAsJsonObject().get("current_buy_in").getAsInt();

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
        } catch (Exception e) {
            System.err.println(e.getMessage());

            return 1000;
        }
    }
}