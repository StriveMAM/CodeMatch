package com.coding.sales.discount;

import java.util.ArrayList;

import com.coding.sales.input.OrderCommand;

public class DiscountCardBuilder {
    public static ArrayList<String> getDiscountCards(OrderCommand command) {
        return (ArrayList<String>) command.getDiscounts();
    }
}