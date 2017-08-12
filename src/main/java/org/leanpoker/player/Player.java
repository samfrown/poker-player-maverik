package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.SortedSet;


public class Player {


    static final String VERSION = "Maverik";
    static final String NAME = "Maverik";

    public static int betRequest(JsonElement request) {
        return ddd(request);
    }

    public static void showdown(JsonElement game) {
    }

    static int ddd(JsonElement req) {
        Cards c = getMyCards(req);
        try {
            int r = GetRate.get(c.getCarts());

            int round = req.getAsJsonObject().get("round").getAsInt();
            int maxBet = req.getAsJsonObject().get("current_buy_in").getAsInt();

            System.err.println("HASTER");

            int ourBet = 200;

            if (r < 1) {
                return 0;
            }

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

        }catch (Exception e) {

            return fff(req);
        }
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


                return maxBet;

        } catch (Exception e) {
            System.err.println(e.getMessage());

            return 1000;
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

    public static JsonElement getPlayer(JsonElement request) {
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        for (JsonElement player: players) {
            if (player.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(NAME)) {
                return player;
            }
        }
        return null;
    }

    public int getRating(Cards cards) {
        return cards.getRating();

    }

}
