package com.coding.sales;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.coding.sales.activity.Activity;
import com.coding.sales.card.Card;
import com.coding.sales.discount.DiscountCard;
import com.coding.sales.input.*;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.product.Product;

public class Order{
    private OrderCommand mCommand;
    private Card mCard;
    private ArrayList<Product> mProducts;
    private ArrayList<DiscountCard> mDiscountCards;

    public Order(OrderCommand command,Card card , ArrayList<Product> products,ArrayList<DiscountCard> discountCards){
        mCommand = command;
        mCard = card;
        mProducts = products;
        mDiscountCards = mDiscountCards;
    }

    public OrderRepresentation buy(){
        String orderId = mCommand.getOrderId();
        String createTime = mCommand.getCreateTime();
        String memberNo = mCommand.getMemberId();
        String memberName = mCard.getMemberName();
        String oldMemberType = mCard.getType();
        doBuy();
        String newMemberType = mCard.getType();
        int memberPointsIncreased = mCard.getAddedPoints();
        int memberPoints = mCard.getPoints();
        return "";
    }

    private void doBuy(){
        BigDecimal discount = doDazhe();
        BigDecimal manjian = doManjian();
    }

    private BigDecimal doManjian(){
        
    }
}