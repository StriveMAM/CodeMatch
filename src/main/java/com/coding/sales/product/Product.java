package com.coding.sales.product;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.coding.sales.activity.Activity;

public class Product {
    public ArrayList<String> mDiscountCard = new ArrayList<String>();
    public ArrayList<String> mActivity = new ArrayList<String>();;
    public BigDecimal mPrice;
    public int mCount;
    public String productNo;
    public String productName;
    public int amount;

    public ArrayList<String> mDiscountCardUsed = new ArrayList<String>();
    public String mActivityType;

    public BigDecimal mTotalPrice;// 优惠总价格
    public BigDecimal mTTPrice;// 总价格

    public BigDecimal doBuy(ArrayList<String> discounts) {

        BigDecimal discount = getTotalPriceByDiscount(discounts, false);
        BigDecimal manjian = getTotalPriceByActivity(false);
        if (discount.compareTo(manjian) == 1) {
            return getTotalPriceByDiscount(discounts, true);
        } else {
            return getTotalPriceByActivity(true);
        }
    }

    public BigDecimal getTotalPriceByDiscount(ArrayList<String> discounts, boolean real) {
        mTTPrice = mPrice.multiply(new BigDecimal(mCount));
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
                if (real) {
                    mDiscountCardUsed.add(t);
                }
            }
        }
        price = price.add(mPrice.multiply(new BigDecimal(count)));
        if (real) {
            mTotalPrice = price;
        }
        return price;
    }

    public BigDecimal getTotalPriceByActivity(boolean real) {
        BigDecimal price = mPrice.multiply(new BigDecimal(mCount));
        BigDecimal priceJian = null;
        String typeJian = null;
        if (mActivity.contains("每满1000元减10")) {
            if (price.compareTo(new BigDecimal("1000")) > -1) {
                priceJian = price.subtract(new BigDecimal("10"));
                typeJian = "每满1000元减10";
            }
        }
        if (mActivity.contains("每满2000元减30")) {
            if (price.compareTo(new BigDecimal("2000")) > -1) {
                priceJian = price.subtract(new BigDecimal("30"));
                typeJian = "每满2000元减30";
            }
        }
        if (mActivity.contains("每满3000元减350")) {
            if (price.compareTo(new BigDecimal("3000")) > -1) {
                priceJian = price.subtract(new BigDecimal("350"));
                typeJian = "每满3000元减350";
            }
        }
        if (priceJian == null) {
            priceJian = price;
        }
        BigDecimal priceShan = null;
        String typeShan = null;
        if (mCount >= 4) {
            priceShan = mPrice.multiply(new BigDecimal(mCount - 1));
            typeShan = "满3送1";
        } else if (mCount == 3) {
            priceShan = mPrice.multiply(new BigDecimal(mCount).subtract(mPrice.multiply(new BigDecimal("0.5"))));
            typeShan = "第3件半价";
        } else {
            priceShan = price;
        }
        if (priceShan.compareTo(priceJian) > -1) {
            price = priceJian;
            if (real) {
                mActivityType = typeJian;
            }
        } else {
            price = priceShan;
            if (real) {
                mActivityType = typeShan;
            }
        }
        if (real) {
            mTotalPrice = price;
        }
        return price;
    }

    private BigDecimal getPriceByDiscount(String type) {
        if ("95折券".equals(type)) {
            return mPrice.multiply(new BigDecimal(0.95f));
        } else if ("9折券".equals(type)) {
            return mPrice.multiply(new BigDecimal(0.9f));
        } else {
            return mPrice;
        }
    }
}