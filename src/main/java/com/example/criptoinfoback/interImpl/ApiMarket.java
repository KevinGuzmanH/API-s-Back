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
public class ApiMarket {
    private static String apiKey = "f03690d8f5msh331f361962422c1p1e14eejsn08d62e82efc7";

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader("x-rapidapi-host", "stock-market-data.p.rapidapi.com");
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

    public String getNews(String company) {
        String uri = "https://stock-market-data.p.rapidapi.com/stock/buzz/news" ;
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

        if (company.equals("google")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "GOOGL"));
        }
        if (company.equals("apple")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "AAPL"));
        }
        if (company.equals("amazon")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "AMZN"));
        }

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

    public String getCompanyInfo(String company) {
        String uri = "https://stock-market-data.p.rapidapi.com/stock/company-info" ;
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();

        if (company.equals("google")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "GOOGL"));
        }
        if (company.equals("apple")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "AAPL"));
        }
        if (company.equals("amazon")) {
            paratmers.add(new BasicNameValuePair("ticker_symbol", "AMZN"));
        }

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
