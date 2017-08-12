package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


public class Player {


    static final String VERSION = "Maverik";
    static final String NAME = "Maverik";

    public static int betRequest(JsonElement request) {
        return newstr(request);
    }

    public static void showdown(JsonElement game) {
    }

    static int ddd(JsonElement req) {
        try {
            System.err.println("STRATAGY ddd");
            Cards c = getMyCards(req);
            int r = GetRate.get(c.getCarts());

            int round = req.getAsJsonObject().get("round").getAsInt();
            int maxBet = req.getAsJsonObject().get("current_buy_in").getAsInt() - getPlayerBet(req, "Maverik");

            System.err.println("MAVERIK raiting from api" + Integer.toString(r));

            int ourBet = 200;



            if (r < 1) {
                return 0;
            }

            if (round == 0) {

            } else if (round < 3) {
                ourBet = 500;
            } else {
                ourBet = 101;
            }

            if (maxBet > ourBet) {
                return maxBet;
            } else {
                return ourBet;
            }

        }catch (Exception e) {
            System.err.println(e.getMessage());
            return newstr(req);
        }
    }

    static int fff(JsonElement req) {
        try {
            System.err.println("STRATAGY fff");
            int round = req.getAsJsonObject().get("round").getAsInt();
            int maxBet = req.getAsJsonObject().get("current_buy_in").getAsInt();

            int ourBet = 100;

            if (round == 0) {

            } else if (round < 3) {
                ourBet = 200;
            } else {
                ourBet = 500;
            }

            if (maxBet > ourBet) {
                return maxBet;
            } else {
                return ourBet;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());

            return 102;
        }
    }

    static int newstr(JsonElement req) {
        try {
            System.err.println("NEW STRATAGY");
            Cards c = getMyCards(req);
            int round = req.getAsJsonObject().get("round").getAsInt();
            int maxBet = req.getAsJsonObject().get("current_buy_in").getAsInt() - getPlayerBet(req, "Maverik");
            System.err.println("MAXBET = " + Integer.toString(maxBet));

            if (c.getRating() == 0) {
                if (round == 0 && numPlayers(req) == 2) {
                    System.err.println("Maverik num = 2");

                    return req.getAsJsonObject().get("minimum_raise").getAsInt();
                }
                return 0;
            }

            if (c.getCarts().size() > 4) {
                int r = GetRate.get(c.getCarts());
                if (r == 0) {
                    return 0;
                }
            } else if (c.getRating() == 1) {
                int playerBet = getPlayerBet(req, "Wise Kaa 2");
                if (playerBet > 4) {
                    return 0;
                } else {
                    if (round == 0 && maxBet > 100) {
                        return 0;
                    }
                    System.err.println("YYYY = " + Integer.toString(maxBet));
                    return maxBet;
                }
            }

            int ourBet = 100;

            if (round == 0) {

            } else if (round < 3) {
                ourBet = 200;
            } else {
                ourBet = 250;
            }

            ourBet = ourBet - getPlayerBet(req, "Maverik");
            if (maxBet > ourBet) {
                System.err.println("XXXX = " + Integer.toString(maxBet));
                return maxBet;
            } else {
                System.err.println("OURBET = " + Integer.toString(ourBet));
                return ourBet;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());

            return fff(req);
        }
    }

    public static Cards getMyCards(JsonElement request) {
        Cards my = new Cards();

        JsonElement player = getMyPlayer(request);
        if ( player != null ) {
            my.add(player.getAsJsonObject().get("hole_cards").getAsJsonArray());
        } else {
            System.err.println("My player not found");
        }

        try {
            my.add(request.getAsJsonObject().get("community_cards").getAsJsonArray());
        } catch (Exception e) {
            System.err.println("community_cards not found");
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

    public static int getPlayerBet(JsonElement request, String name) {
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        for (JsonElement player: players) {
            if (player.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)) {
                return player.getAsJsonObject().get("bet").getAsInt();
            }
        }
        System.err.println();
        return 0;
    }

    public static int numPlayers(JsonElement request) {
        JsonArray players = request.getAsJsonObject().get("players").getAsJsonArray();
        int count = 0;
        for (JsonElement player: players) {
            if (player.getAsJsonObject().get("status").getAsString().equalsIgnoreCase("active")) {
                count++;
            }
        }
        return count;
    }

}
