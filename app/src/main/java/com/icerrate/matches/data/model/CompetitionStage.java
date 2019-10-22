package com.icerrate.matches.data.model;

/**
 * @author icerrate
 */
public class CompetitionStage {

    private PairData competition;

    public CompetitionStage(PairData competition) {
        this.competition = competition;
    }

    public PairData getCompetition() {
        return competition;
    }
}
