package com.icerrate.matches.ui.common;

import com.icerrate.matches.Injection;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.data.source.MatchRepository;
import com.icerrate.matches.ui.match.MatchContract;
import com.icerrate.matches.ui.match.MatchListFragment;
import com.icerrate.matches.ui.match.fixture.FixtureListPresenter;
import com.icerrate.matches.ui.match.result.ResultListPresenter;

/**
 * @author icerrate
 */
public class ViewConfigurator {

    /**
     * Gets the <code>MatchListFragment</code> with all their dependencies.
     *
     * @return the <code>MatchListFragment</code> with {@link FixtureListPresenter}.
     */
    public static MatchListFragment fixturesFragment() {
        MatchListFragment fragment = MatchListFragment.newInstance(Match.FIXTURE_TYPE);
        MatchRepository repository = Injection.matchRepository();
        MatchContract.Presenter presenter = new FixtureListPresenter(fragment, repository);
        fragment.setPresenter(presenter);
        return fragment;
    }

    /**
     * Gets the <code>MatchListFragment</code> with all their dependencies.
     *
     * @return the <code>MatchListFragment</code> with {@link ResultListPresenter}.
     */
    public static MatchListFragment resultsFragment() {
        MatchListFragment fragment = MatchListFragment.newInstance(Match.RESULTS_TYPE);
        MatchRepository repository = Injection.matchRepository();
        MatchContract.Presenter presenter = new ResultListPresenter(fragment, repository);
        fragment.setPresenter(presenter);
        return fragment;
    }
}
