package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class Nbu {
    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final String USD = "USD";
    private static final String EUR = "EUR";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Nbu.getCource().entrySet().forEach(System.out::println);
        System.out.println("Nbu.getCource() = " + Nbu.getCource());
    }
  public static Map<String, String> getCource(){
        return getCurrency();
    }

    private static Map<String, String> getCurrency() {
        final Map<String, String> mapCurrency = new LinkedHashMap<>();
        final HttpResponse<String> response = sendGET(URI.create(NBU_URL));
        JSONArray jsonArray = new JSONArray(response.body());
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(USD.equals(jsonObject.getString("cc"))) {
                mapCurrency.put("USD_buy", "0.0000");
                mapCurrency.put("USD_sell", String.valueOf(jsonObject.getDouble("rate")));
            }else if (EUR.equals(jsonObject.getString("cc"))) {
                mapCurrency.put("EUR_buy", "0.0000");
                mapCurrency.put("EUR_sell", String.valueOf(jsonObject.getDouble("rate")));
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
