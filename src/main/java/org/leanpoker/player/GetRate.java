package org.leanpoker.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by Haster on 12.08.2017.
 */
public class GetRate {

    static public int get(List<Card> cards) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("cards=[");

        int len = cards.size();
        int i = 0;
        for (Card card: cards) {

            builder.append(card.toString());
            if (i != len-1) {
                builder.append(",");
            }
            i++;
        }
        builder.append("]");

        String body = builder.toString();
        System.err.println("MAVERIK LOG:" + body);

        String url = "http://rainman.leanpoker.org/rank";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Content-Length", Integer.toString(body.length()));


        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(body.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JsonElement el = new JsonParser().parse(inputLine);
        return el.getAsJsonObject().get("rank").getAsInt();
    }
}
