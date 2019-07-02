package com.coding.sales.card;

import java.util.HashMap;

import com.coding.sales.input.OrderCommand;

public class CardBuilder{
    public static Card getCard(OrderCommand command){
        return new Card();
    }

}