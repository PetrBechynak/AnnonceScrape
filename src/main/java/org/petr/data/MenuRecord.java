package org.petr.data;

/**
 * Created by petr on 5.11.16.
 */


public class MenuRecord {
    String metric;
    String text;
    String price;

    public MenuRecord() {
    }

    public MenuRecord(String metric, String text, String price) {
        this.metric = metric;
        this.text = text;
        this.price = price;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return metric + "\t" +text + "\t" + price;
    }
}
