package com.icerrate.matches.ui.match.result;

import com.icerrate.matches.data.ErrorType;
import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.data.source.MatchRepository;
import com.icerrate.matches.ui.match.MatchContract;

import java.util.List;

/**
 * @author icerrate
 */
public class ResultListPresenter implements MatchContract.Presenter {

    private MatchContract.View mView;

    private MatchRepository mRepository;

    public ResultListPresenter(MatchContract.View view, MatchRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void start() {
        getResults();
    }

    private void getResults() {
        mView.showLoadingView(true);
        mRepository.getResults(new ResponseCallback<List<Match>>() {
            @Override
            public void onSuccess(List<Match> response) {
                showResults(response);
                mView.showLoadingView(false);
            }

            @Override
            public void onError(ErrorType errorType) {
                mView.showNoDataFound(true);
                mView.showLoadingView(false);
            }
        });
    }

    private void showResults(List<Match> results) {
        boolean isEmpty = results == null || results.isEmpty();
        mView.showNoDataFound(isEmpty);
        if(!isEmpty) {
            mView.loadMatches(results);
        }
    }
}
