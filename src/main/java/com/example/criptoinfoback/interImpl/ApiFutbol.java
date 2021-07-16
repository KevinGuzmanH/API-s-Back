package com.example.criptoinfoback.interImpl;

import com.example.criptoinfoback.coinController.controller;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiFutbol {

private static String apiKey = "81b31b19aemsh2a28352bbac4db8p13327fjsn493a96a2f779";

public static String makeAPICall(String uri, List<NameValuePair> parameters)

        throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader("x-rapidapi-host", "api-football-beta.p.rapidapi.com");
        request.addHeader("x-rapidapi-key", apiKey);


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

        public String getResults(String[] parametros) {

        String uri = "https://api-football-beta.p.rapidapi.com/standings?" ;

        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("season",parametros[0]));
        paratmers.add(new BasicNameValuePair("league",parametros[1]));
                System.out.println(parametros[0]);
                System.out.println(parametros[1]);
        try {
        String result = makeAPICall(uri, paratmers);
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
