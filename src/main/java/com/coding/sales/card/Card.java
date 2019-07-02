package com.coding.sales.card;

public class Card{
    private String mType;
    private String mName;
    private String mCardNo;
    private int mPoints;
    private int mAddedPoints;

    public Card(){

    }

    public void add(int addedPoints){
        mPoints = mPoints + addedPoints;
    }

    public String getMemberName(){
        return mName;
    }

    public String getType(){
        return mType;
    }

	public int getAddedPoints() {
		return mAddedPoints;
    }
    
    public int getPoints(){
        return mPoints;
    }

}