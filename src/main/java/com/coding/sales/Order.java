package com.coding.sales;

import java.util.ArrayList;

import com.coding.sales.activity.Activity;
import com.coding.sales.card.Card;
import com.coding.sales.discount.DiscountCard;
import com.coding.sales.input.*;
import com.coding.sales.product.Product;

public class Order{
    private String mOrderId;
    private String mCreateTime;

    public Order(OrderCommand command){
        mOrderId = command.getOrderId();
        mCreateTime = command.getCreateTime();
    }

    public String buy(Card card , ArrayList<Product> products,ArrayList<DiscountCard> discountCards){
        return "";
    }
}