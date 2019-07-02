package com.coding.sales.discount;

import java.util.ArrayList;
import java.util.Comparator;

import com.coding.sales.input.OrderCommand;

public class DiscountCardBuilder {
    public static ArrayList<String> getDiscountCards(OrderCommand command) {
        ArrayList<String> ll = (ArrayList<String>) command.getDiscounts();
        ll.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if ("9折券".equals(o1)) {
                    return -1;
                }
                return 1;
            }

        });
        return ll;
    }
}