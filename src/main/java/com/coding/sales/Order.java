package com.coding.sales;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.coding.sales.card.Card;
import com.coding.sales.discount.DiscountCard;
import com.coding.sales.input.*;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.product.Product;

public class Order {
    private OrderCommand mCommand;
    private Card mCard;
    private ArrayList<Product> mProducts;
    private ArrayList<String> mDiscountCards;

    public Order(OrderCommand command, Card card, ArrayList<Product> products, ArrayList<String> discountCards) {
        mCommand = command;
        mCard = card;
        mProducts = products;
        mDiscountCards = discountCards;
    }

    public OrderRepresentation buy() {
        String orderId = mCommand.getOrderId();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = null;
        try {
            createTime = formatter.parse(mCommand.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String memberNo = mCommand.getMemberId();
        String memberName = mCard.getMemberName();
        String oldMemberType = mCard.getType();
        doBuy();
        ArrayList<OrderItemRepresentation> list = new ArrayList<OrderItemRepresentation>();
        BigDecimal totalPrice = new BigDecimal("0");// 订单总金额
        BigDecimal ttPrice = new BigDecimal("0");// 应收金额
        ArrayList<DiscountItemRepresentation> discounts = new ArrayList<DiscountItemRepresentation>();
        ArrayList<String> cards = new ArrayList<String>();
        for (Product pd : mProducts) {
            OrderItemRepresentation item = new OrderItemRepresentation(pd.productNo, pd.productName, pd.mPrice,
                    new BigDecimal(pd.mCount), pd.mTotalOriginPrice);
            list.add(item);
            totalPrice = totalPrice.add(pd.mTotalPrice);
            ttPrice = ttPrice.add(pd.mTotalOriginPrice);

            DiscountItemRepresentation dr = new DiscountItemRepresentation(pd.productNo, pd.productName,
                    pd.mTotalOriginPrice.subtract(pd.mTotalPrice));
            cards.addAll(pd.mDiscountCardUsed);
            if (pd.mTotalOriginPrice.doubleValue() != pd.mTotalPrice.doubleValue())
                discounts.add(dr);
        }
        BigDecimal totalDiscountPrice = ttPrice.subtract(totalPrice);
        mCard.add(totalPrice.intValue());
        String newMemberType = mCard.getType();
        int memberPointsIncreased = mCard.getAddedPoints();
        int memberPoints = mCard.getPoints();
        ArrayList<PaymentRepresentation> payments = new ArrayList<PaymentRepresentation>();
        for (PaymentCommand pc : mCommand.getPayments()) {
            payments.add(new PaymentRepresentation(pc.getType(), pc.getAmount()));
        }
        return new OrderRepresentation(orderId, createTime, memberNo, memberName, oldMemberType, newMemberType,
                memberPointsIncreased, memberPoints, list, ttPrice, discounts, ttPrice.subtract(totalPrice), totalPrice,
                payments, cards);
    }

    private void doBuy() {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(mDiscountCards);
        BigDecimal total = new BigDecimal("0");
        for (Product pt : mProducts) {
            total = total.add(pt.doBuy(list));
        }
    }

    private BigDecimal doManjian(boolean real) {
        BigDecimal total = new BigDecimal("0");
        for (Product pt : mProducts) {
            total = total.add(pt.getTotalPriceByActivity(real));
        }
        return total;
    }

    private BigDecimal doDazhe(boolean real) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(mDiscountCards);
        BigDecimal total = new BigDecimal("0");
        for (Product pt : mProducts) {
            total = total.add(pt.getTotalPriceByDiscount(list, real));
        }
        return total;
    }
}