package ir.mehrdad32.cryptoviewer;

public class ListItem {
    private String name;
    private String price;
    private String marketCap;

    public ListItem(String name, String price, String marketCap) {
        this.name = name;
        this.price = price;
        this.marketCap = marketCap;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getMarketCap() {
        return marketCap;
    }
}
