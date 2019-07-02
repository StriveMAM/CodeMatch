package com.coding.sales.product;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.coding.sales.activity.Activity;

public abstract class Product {
    protected ArrayList<String> mDiscountCard;
    protected BigDecimal mPrice;
    protected int mCount;

    public BigDecimal getTotalPriceByDiscount(ArrayList<String> discounts) {
        BigDecimal price = new BigDecimal("0");
        int count = mCount;
        for (String t : mDiscountCard) {
            BigDecimal p = mPrice;
            if (count == 0) {
                break;
            }
            if (discounts.contains(t)) {
                discounts.remove(t);
                count = count - 1;
                p = getPriceByDiscount(t);
                price = price.add(p);
            }
        }
        price = price.add(mPrice.multiply(new BigDecimal(count)));
        return price;
    }

    public abstract BigDecimal getTotalPriceByActivity();

    private BigDecimal getPriceByDiscount(String type) {
        if ("95折券".equals(type)) {
            return mPrice.multiply(new BigDecimal("0.95"));
        } else if ("9折卷".equals(type)) {
            return mPrice.multiply(new BigDecimal("0.9"));
        } else {
            return mPrice;
        }
    }
}