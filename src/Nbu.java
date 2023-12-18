package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Nbu {
    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final String USD = "USD";
    private static final String EUR = "EUR";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

//    public static void main(String[] args) {
//        // for testing purpose only
//        getCource(USD).entrySet().forEach(System.out::println);
//        getCource(EUR).entrySet().forEach(System.out::println);
//    }

    public static Map<String, Double> getCource(String currency){
        return getCurrency( currency);
    }

    private static Map<String, Double> getCurrency(String currency) {
        final HttpResponse<String> response = sendGET(URI.create(NBU_URL));
        JSONArray jsonArray = new JSONArray(response.body());
        HashMap<String, Double> mapCurrency = new HashMap<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(currency.equals(jsonObject.getString("cc"))) {
                mapCurrency.put(jsonObject.get("cc").toString(), jsonObject.getDouble("rate") );
            }
        }
        return mapCurrency;
    }

    private static HttpResponse<String> sendGET(URI uri)  {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response = null;
        try {
            response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
}