# CryptoViewer

## URL:
https://www.megaweb.ir/api/digital-money

## Java Class to map
```java
package ir.mehrdad32.example;

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
```

## Usage with GSON and OkHttp
```java
OkHttpClient client = new OkHttpClient();
Request request = new Request.Builder().url("https://www.megaweb.ir/api/digital-money").build();
Response responses = null;

try {
  responses = client.newCall(request).execute();
} catch (IOException e) {
  e.printStackTrace();
}

String responseFromServer = responses.body().string();

Gson gson = new Gson();
CryptoPrice cryptoPrice = gson.fromJson(s, CryptoPrice.class);

...
```
