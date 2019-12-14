package Kafka;

import java.io.Serializable;

public class Purchase implements Serializable {

    private int item_id;
    private int price;
    private int units;

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

    public Purchase(int item_id, int price, int units) {
        this.item_id = item_id;
        this.price = price;
        this.units = units;
    }
}
