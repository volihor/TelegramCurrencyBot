package org.example.privat;

public class PrivatTest {
    public static void main(String[] args) {
        PrivatRateFormatter rateFormatter = new PrivatRateFormatter();
        String eurMessage = rateFormatter.privatRateFornmater(Currency.EUR, 3);
        String usdMessage = rateFormatter.privatRateFornmater(Currency.USD, 3);
        System.out.println(eurMessage);
        System.out.println(usdMessage);
    }
}
