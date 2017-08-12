package org.leanpoker.player;

public class Card {
    private String suit;
    private String rankStr;

    public String getSuit() {
        return suit;
    }

    public String getRankStr() {return rankStr;}

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

    public Card(String suit, String rankStr) {
        this.suit = suit;
        this.rank = getRandFromString(rankStr);
        this.rankStr = rankStr;
    }

    public static int getRandFromString(String ranksym) {
        int newrank = 2;
        try {
            newrank = Integer.valueOf(ranksym);
        } catch (IllegalArgumentException e) {
            if ( ranksym.equalsIgnoreCase("j")) {
                newrank = 11;
            } else if (ranksym.equalsIgnoreCase("q")) {
                newrank = 12;
            } else if (ranksym.equalsIgnoreCase("k")) {
                newrank = 13;
            } else if (ranksym.equalsIgnoreCase("a")) {
                newrank = 14;
            }
        }
        return  newrank;
    }

    public String toString() {
        return String.format("{\"rank\":\"%s\",\"suit\":\"%s\"}", rankStr, suit);
    }

}
