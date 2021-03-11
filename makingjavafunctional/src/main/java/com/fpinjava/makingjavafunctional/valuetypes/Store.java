package com.fpinjava.makingjavafunctional.valuetypes;

import java.util.List;

import static com.fpinjava.common.CollectionUtilities.foldLeft;
import static com.fpinjava.common.CollectionUtilities.list;
import static com.fpinjava.makingjavafunctional.valuetypes.Price.price;
import static com.fpinjava.makingjavafunctional.valuetypes.Weight.weight;

public class Store {

    public static void main(String[] args) {

        Product toothPaste = new Product("Tooth paste", price(1.5), weight(0.5));
        Product toothBrush = new Product("Tooth brush", price(3.5), weight(0.3));

        List<OrderLine> order = list(
                new OrderLine(toothPaste, 2),
                new OrderLine(toothBrush, 3));

        Price price = foldLeft(order, Price.ZERO, Price.sum);
        Weight weight = foldLeft(order, Weight.ZERO, Weight.sum);

        System.out.println(String.format("Total price: %s", price));
        System.out.println(String.format("Total weight: %s", weight));

    }
}