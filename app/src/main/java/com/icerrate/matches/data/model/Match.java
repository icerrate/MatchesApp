package com.icerrate.matches.data.model;

/**
 * @author icerrate
 */
public class Match {

    public static final int FIXTURE_TYPE = 0;

    public static final int RESULTS_TYPE = 1;

    public static final String POSTPONED_STATE = "postponed";

    private int id;

    private String type;

    private Team homeTeam;

    private Team awayTeam;

    private String date;

    private CompetitionStage competitionStage;

    private PairData venue;

    private String state;

    private Score score;

    public Match(int id, String type, Team homeTeam, Team awayTeam, String date,
                 CompetitionStage competitionStage, PairData venue, String state, Score score) {
        this.id = id;
        this.type = type;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.competitionStage = competitionStage;
        this.venue = venue;
        this.state = state;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getDate() {
        return date;
    }

    public CompetitionStage getCompetitionStage() {
        return competitionStage;
    }

    public PairData getVenue() {
        return venue;
    }

    public String getState() {
        return state;
    }

    public Score getScore() {
        return score;
    }
}
