package com.coding.sales.card;

public class Card {
    public String mType;
    public String mName;
    public String mCardNo;
    public int mPoints;
    public int mAddedPoints;

    public Card() {

    }

    public void add(int addedPoints) {
        float bei = 1;
        if ("金卡".equals(mType)) {
            bei = 1.5f;
        } else if ("白金卡".equals(mType)) {
            bei = 1.8f;
        } else if ("白金卡".equals(mType)) {
            bei = 2.0f;
        }
        mAddedPoints = (int) (addedPoints * bei);
        mPoints = mPoints + mAddedPoints;
        if (mPoints < 10000) {
            mType = "普卡";
        } else if (mPoints >= 10000 && mPoints < 50000) {
            mType = "金卡";
        } else if (mPoints >= 50000 && mPoints < 100000) {
            mType = "白金卡";
        } else {
            mType = "钻石卡";
        }
    }

    public String getMemberName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

    public int getAddedPoints() {
        return mAddedPoints;
    }

    public int getPoints() {
        return mPoints;
    }

}