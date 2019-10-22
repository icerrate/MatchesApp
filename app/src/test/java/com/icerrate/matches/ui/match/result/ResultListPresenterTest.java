package com.icerrate.matches.ui.match.result;

import com.icerrate.matches.data.DataSuit;
import com.icerrate.matches.data.ErrorType;
import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.data.source.MatchRepository;
import com.icerrate.matches.ui.match.MatchContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.verify;

/**
 * @author icerrate
 */
public class ResultListPresenterTest {

    private ResultListPresenter mPresenter;

    @Mock
    private MatchRepository mRepository;

    @Mock
    private MatchContract.View mView;

    @Captor
    private ArgumentCaptor<ResponseCallback<List<Match>>> mMatchesCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new ResultListPresenter(mView, mRepository);
    }

    @Test
    public void testGetFixturesSuccessfully() {
        mPresenter.start();
        verify(mView).showLoadingView(true);
        captureResults();
        verify(mView).showNoDataFound(false);
        verify(mView).loadMatches(new ArrayList<>(anyCollectionOf(Match.class)));
        verify(mView).showLoadingView(false);
    }

    @Test
    public void testGetFixturesWithError() {
        mPresenter.start();
        verify(mView).showLoadingView(true);
        captureResultsErrorType();
        verify(mView).showNoDataFound(true);
        verify(mView).showLoadingView(false);
    }

    private void captureResults() {
        verify(mRepository).getResults(mMatchesCaptor.capture());
        mMatchesCaptor.getValue().onSuccess(DataSuit.getResultsData(this.getClass()));
    }

    private void captureResultsErrorType() {
        verify(mRepository).getResults(mMatchesCaptor.capture());
        mMatchesCaptor.getValue().onError(ErrorType.UNKNOWN_ERROR);
    }
}