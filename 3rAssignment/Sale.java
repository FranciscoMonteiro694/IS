package Kafka;

import java.io.Serializable;

public class Sale implements Serializable {

    private int item_id;
    private int price;
    private int units;
    private int country_id;

    public Sale(int item_id, int price, int units, int country_id) {
        this.item_id = item_id;
        this.price = price;
        this.units = units;
        this.country_id = country_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
