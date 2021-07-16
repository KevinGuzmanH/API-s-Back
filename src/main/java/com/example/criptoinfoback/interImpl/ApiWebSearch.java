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
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiWebSearch {
    private static String apiKey = "791208d7bbmsh900b2969c3e8afap1023b8jsn7969bde344a2";

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader("x-rapidapi-host", "contextualwebsearch-websearch-v1.p.rapidapi.com");
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

    public String getResults(String[] palabras) {

        String uri = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/WebSearchAPI?q=" ;
        for (int i = 0; i < palabras.length; i++) {

            String parametro = "%20";

            if (i == 0){
                uri+= palabras[0];
            }else {
                uri+= parametro + palabras[i];
            }

        }
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("pageNumber","1"));
        paratmers.add(new BasicNameValuePair("pageSize","10"));
        paratmers.add(new BasicNameValuePair("autoCorrect","true"));

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
