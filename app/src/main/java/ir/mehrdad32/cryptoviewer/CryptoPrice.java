package ir.mehrdad32.cryptoviewer;

import java.util.List;

public class CryptoPrice {
    public List<Datum> data;
    public Info info;
}

class Info{
    public int coins_num;
    public int time;
}

class Datum{
    public String id;
    public String symbol;
    public String name;
    public String nameid;
    public int rank;
    public String price_usd;
    public String percent_change_24h;
    public String percent_change_1h;
    public String percent_change_7d;
    public String price_btc;
    public String market_cap_usd;
    public double volume24;
    public double volume24a;
    public String csupply;
    public String tsupply;
    public String msupply;
}