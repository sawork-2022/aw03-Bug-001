package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        try {
            for (int i = 0; i < items.size(); ++i) {
                if (items.get(i).getProduct().equals(item.getProduct())) {
                    return this.itemInc(i);
                }
            }
            return items.add(item);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delItem(int index) {
        try {
            items.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean itemInc(int index) {
        try {
            Item item = items.get(index);
            item.setQuantity(item.getQuantity() + 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean itemDec(int index) {
        try {
            Item item = items.get(index);
            int qtt = item.getQuantity();
            if (qtt <= 1) {
                this.delItem(index);
            }
            item.setQuantity(qtt - 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }

    public double total() {
        double total = 0;

        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }

        return total;
    }

    public void clear() {
        items.clear();
    }
}
