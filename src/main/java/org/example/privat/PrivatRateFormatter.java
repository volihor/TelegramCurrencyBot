package org.example.privat;

public class PrivatRateFormatter {
    PrivatCurrencyService privatCurrencyService = new PrivatCurrencyService();

    // Метод, що приймає валюту та кількість знаків після коми
    public String privatRateFornmater(Currency currency, int decimalPlaces) {
        PrivatCurrencyRate currencyRate = privatCurrencyService.getRate(currency);
        float buyRate = currencyRate.getBuyRate();
        float sellRate = currencyRate.getSellRate();

        // Форматування повідомлення
        return String.format("Курс від ПриватБанку: %s/UAH:\nКупівля: %." + decimalPlaces + "f\nПродаж: %." + decimalPlaces + "f", currency, buyRate, sellRate);
    }
}
