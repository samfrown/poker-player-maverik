package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class Player {


    static final String VERSION = "Maverik";
    static final String NAME = "Maverik";

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

    public static Cards getMyCards(JsonElement request) {
        Cards my = new Cards();

        try {
            my.add(request.getAsJsonObject().get("community_cards").getAsJsonArray());
        } catch (Exception e) {
            System.err.println("community_cards not found");
        }

        JsonElement player = getMyPlayer(request);
        if ( player != null ) {
            my.add(player.getAsJsonObject().get("hole_cards").getAsJsonArray());
        } else {
            System.err.println("My player not found");
        }
        return my;
    }

    public static JsonElement getMyPlayer(JsonElement request) {
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        for (JsonElement player: players) {
            if (player.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(NAME)) {
                return player;
            }
        }
        return null;
    }

}
