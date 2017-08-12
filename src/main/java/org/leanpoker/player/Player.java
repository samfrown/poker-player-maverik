package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {


    static final String VERSION = "Maverik";

    public static int betRequest(JsonElement request) {
        return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}

int getCardRank(List<Card> cards) {

    String url = "http://rainman.leanpoker.org/rank";

    URL obj = null;
    try {
        obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }


}
}