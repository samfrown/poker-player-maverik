package org.leanpoker.player;

public class Card {
    private String suit;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank;


    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

}
