package com.example.assignment03;

public class CardInfo {
    private int imageViewId, drawableId;
    private boolean isClicked = false;
    private boolean isMatched = false;

    public CardInfo(int imageViewId, int drawableId) {
        this.imageViewId = imageViewId;
        this.drawableId = drawableId;
    }

    public void updateCardInfo(int imageViewId, int drawableId){
        this.imageViewId = imageViewId;
        this.drawableId = drawableId;
    }

    public int getImageViewId() {
        return imageViewId;
    }

    public void setImageViewId(int imageViewId) {
        this.imageViewId = imageViewId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public boolean isClicked() {
        return !isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "imageViewId=" + imageViewId +
                ", drawableId=" + drawableId +
                ", isClicked=" + isClicked +
                ", isMatched=" + isMatched +
                '}';
    }
}
