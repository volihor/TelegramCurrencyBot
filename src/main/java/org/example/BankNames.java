package org.example;

public enum BankNames {
    Privat,
    Nbu,
    Mono;

    @Override
    public String toString() {
        switch (this) {
            case Privat: return "Приватбанк";
            case Nbu: return "НБУ";
            case Mono: return "Monobank";
        }
        return super.toString();
    }
}
