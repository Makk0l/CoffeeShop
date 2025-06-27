package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private MenuItem menuItem;
    private List<MenuItem> menuItemList = new ArrayList<>();


    public void addItem(MenuItem item) {
        menuItemList.add(item);
    }

    public List<MenuItem> getItems() {
        return menuItemList;
    }

    public double getTotalPrice() {
        return menuItemList.stream().mapToDouble(MenuItem::getPrice).sum();
    }
}
