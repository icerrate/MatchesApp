package com.icerrate.matches.ui.match;

import com.icerrate.matches.data.model.Match;

import java.util.List;

/**
 * @author icerrate
 */
public interface MatchContract {

    interface View extends MatchView<Presenter> {

        void loadMatches(List<Match> matchList);
    }

    interface Presenter {

        void start();
    }
}
