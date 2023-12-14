package org.example.privat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PrivatCurrencyService {
    public PrivatCurrencyRate getRate(Currency currency) {
        String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";

        //Get JSON
        String json;
        try {
            json = Jsoup
                    .connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            throw new IllegalStateException("Can`t connect to Privat API");
        }

        //Convert JSON => Java Object
        Type typeToken = TypeToken
                .getParameterized(List.class, CurrencyItem.class)
                .getType();
        List<CurrencyItem> currencyItems = new Gson().fromJson(json, typeToken);

        float buyRate = currencyItems.stream()
                .filter(c -> c.getCcy() == currency)
                .filter(c -> c.getBase_ccy() == Currency.UAH)
                .map(c -> c.getBuy())
                .findFirst()
                .orElseThrow();

        float sellRate = currencyItems.stream()
                .filter(c -> c.getCcy() == currency)
                .filter(c -> c.getBase_ccy() == Currency.UAH)
                .map(c -> c.getSale())
                .findFirst()
                .orElseThrow();

        return new PrivatCurrencyRate(buyRate, sellRate);

    }
}
