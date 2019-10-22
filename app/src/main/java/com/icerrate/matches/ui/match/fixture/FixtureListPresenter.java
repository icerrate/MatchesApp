package com.icerrate.matches.ui.match.fixture;

import com.icerrate.matches.data.ErrorType;
import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.data.source.MatchRepository;
import com.icerrate.matches.ui.match.MatchContract;

import java.util.Collections;
import java.util.List;

/**
 * @author icerrate
 */
public class FixtureListPresenter implements MatchContract.Presenter {

    private MatchContract.View mView;

    private MatchRepository mRepository;

    public FixtureListPresenter(MatchContract.View view, MatchRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void start() {
        getFixtures();
    }

    private void getFixtures() {
        mView.showLoadingView(true);
        mRepository.getFixtures(new ResponseCallback<List<Match>>() {
            @Override
            public void onSuccess(List<Match> response) {
                showFixtures(response);
                mView.showLoadingView(false);
            }

            @Override
            public void onError(ErrorType errorType) {
                mView.showNoDataFound(true);
                mView.showLoadingView(false);
            }
        });
    }

    private void showFixtures(List<Match> matches) {
        boolean isEmpty = matches == null || matches.isEmpty();
        mView.showNoDataFound(isEmpty);
        if(!isEmpty) {
            mView.loadMatches(matches);
        }
    }
}
