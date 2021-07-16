package com.example.criptoinfoback.interImpl;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiCoinMarketCap {
    private static String apiKey = "791208d7bbmsh900b2969c3e8afap1023b8jsn7969bde344a2";

    public static String makeAPICall(String uri, List<NameValuePair> parameters, boolean rapidapi)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        if (rapidapi) {
            request.setHeader("x-rapidapi-host", "contextualwebsearch-websearch-v1.p.rapidapi.com");
            request.addHeader("x-rapidapi-key", apiKey);
        }

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    public String getInfo() {
        String uri = "https://pokeapi.co/api/v2/pokemon" ;
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("limit","2"));

        try {
            String result = makeAPICall(uri, paratmers,false);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
            return "Error: cannont access content - " + e.toString();
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
            return "Error: cannont access content - " + e.toString();
        }
    }


}
