package com.prismic2.util.helper;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class UrlHelper {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String ACCEPT = "application/vnd.github.inertia-preview+json";
    private final String AUTHORIZATION = "token 5ed9778f8c58d84741584214282b98c2346454e1";


    public HttpURLConnection getConnection(String url) throws Exception{
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept", ACCEPT);
        con.setRequestProperty("Authorization", AUTHORIZATION);

        return con;
    }

    public StringBuffer getResponse (HttpURLConnection con) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response;
    }

}
