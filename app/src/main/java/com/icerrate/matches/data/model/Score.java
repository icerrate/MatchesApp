package com.icerrate.matches.data.model;

/**
 * @author icerrate
 */
public class Score {

    public static final String HOME = "home";

    public static final String AWAYS = "away";

    private int home;

    private int away;

    private String winner;

    public Score(int home, int away, String winner) {
        this.home = home;
        this.away = away;
        this.winner = winner;
    }

    public int getHome() {
        return home;
    }

    public int getAway() {
        return away;
    }

    public String getWinner() {
        return winner;
    }
}
