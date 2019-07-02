package com.coding.sales.card;

import java.util.HashMap;

import com.coding.sales.input.OrderCommand;

public class CardBuilder {
    public static Card getCard(OrderCommand command) {
        Card card = new Card();
        if ("6236609999".equals(command.getMemberId())) {
            card.mCardNo = command.getMemberId();
            card.mName = "马丁";
            card.mType = "普卡";
            card.mPoints = 9860;
        } else if ("6630009999".equals(command.getMemberId())) {
            card.mCardNo = command.getMemberId();
            card.mName = "王立";
            card.mType = "金卡";
            card.mPoints = 48860;
        }
        if ("8230009999".equals(command.getMemberId())) {
            card.mCardNo = command.getMemberId();
            card.mName = "李想";
            card.mType = "白金卡";
            card.mPoints = 98860;
        }
        if ("9230009999".equals(command.getMemberId())) {
            card.mCardNo = command.getMemberId();
            card.mName = "张三";
            card.mType = "钻石卡";
            card.mPoints = 198860;
        }
        return card;
    }

}