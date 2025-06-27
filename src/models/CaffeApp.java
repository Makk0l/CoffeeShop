package models;

import service.OrderService;

import java.util.List;
import java.util.Scanner;

public class CaffeApp {
    public static void main(String[] args) {
        CaffeApp caffeApp = new CaffeApp();
        caffeApp.start();
    }

    Scanner scanner = new Scanner(System.in);
    OrderService orderService = new OrderService();

    List<MenuItem> itemList = List.of(
            new Drink("Вода", 15.0),
            new Drink("Кола", 30),
            new Food("Пицца", 100),
            new Food("Гамбургер", 100)
    );

    public void start() {
        boolean run = true;
        while (run) {
            printMenuOptions();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showMenu();
                case "2" -> addOrder();
                case "3" -> showCurrentOrder();
                case "4" -> showTotalPrice();
                case "5" -> applyDiscount();
                case "0" -> {
                    System.out.println("Завершение работы");
                    run = false;
                }
                default -> System.out.println("Неверный ввод");
            }
        }
    }

    private void printMenuOptions() {
        System.out.println("\n==== Кафе ====");
        System.out.println("1. Показать меню");
        System.out.println("2. Добавить товар в заказ");
        System.out.println("3. Показать текущий заказ");
        System.out.println("4. Показать итоговую сумму");
        System.out.println("5. Применить скидку");
        System.out.println("0. Выход" + '\n');
        System.out.print("Выберите действие: ");
    }

    private void showMenu() {
        System.out.println("=====Меню=====");
        for (int i = 0; i < itemList.size(); i++) {
            MenuItem item = itemList.get(i);
            System.out.println(i + 1 + " " + item.getName() + " " + item.getPrice());
        }
    }

    private void addOrder() {
        showMenu();
        System.out.println("Введите номер товара ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        try {
            if (index >= 0 && index < itemList.size()) {
                orderService.addOrder(itemList.get(index));
                System.out.println("Товар добавлен в заказ");
            } else {
                System.out.println("Неверный номер");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите корректное число ");
        }
    }

    private void showCurrentOrder() {
        List<MenuItem> items = orderService.getItems();
        if (itemList.isEmpty()) {
            System.out.println("Заказ пуст");
        } else {
            System.out.println("Ваш заказ");
            items.forEach(item -> System.out.println(item.getName()
                    + " " + item.getPrice()));
        }
    }

    private void showTotalPrice() {
        System.out.println("Итоговая сумма " + orderService.getTotalPrice());
    }

    private void applyDiscount() {
        try {
            double discount = Double.parseDouble(scanner.nextLine());
            double discountedPrice = orderService.getTotalPriceWithDiscount(discount);
            System.out.println("Сумма со скидкой " + discountedPrice);
        } catch (NumberFormatException e) {
            System.out.println("Число не верно");
        }
    }
}