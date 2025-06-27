package service;

import models.MenuItem;
import models.Order;

import java.util.List;

public class OrderService {
    private Order order = new Order();

    public void addOrder(MenuItem item) {
        order.addItem(item);
    }

    public double getTotalPrice() {
        return order.getTotalPrice();
    }

    public List<MenuItem> getItems() {
        return order.getItems();
    }
    public double getTotalPriceWithDiscount(double discount){
        double total = getTotalPrice();
        return total - (total * discount / 100);
    }
}
