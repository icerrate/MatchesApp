package com.icerrate.matches.ui.match.fixture;

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
public class FixtureListPresenterTest {

    private FixtureListPresenter mPresenter;

    @Mock
    private MatchRepository mRepository;

    @Mock
    private MatchContract.View mView;

    @Captor
    private ArgumentCaptor<ResponseCallback<List<Match>>> mMatchesCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new FixtureListPresenter(mView, mRepository);
    }

    @Test
    public void testGetFixturesSuccessfully() {
        mPresenter.start();
        verify(mView).showLoadingView(true);
        captureFixtures();
        verify(mView).showNoDataFound(false);
        verify(mView).loadMatches(new ArrayList<>(anyCollectionOf(Match.class)));
        verify(mView).showLoadingView(false);
    }

    @Test
    public void testGetFixturesWithError() {
        mPresenter.start();
        verify(mView).showLoadingView(true);
        captureFixturesErrorType();
        verify(mView).showNoDataFound(true);
        verify(mView).showLoadingView(false);
    }

    private void captureFixtures() {
        verify(mRepository).getFixtures(mMatchesCaptor.capture());
        mMatchesCaptor.getValue().onSuccess(DataSuit.getFixturesData(this.getClass()));
    }

    private void captureFixturesErrorType() {
        verify(mRepository).getFixtures(mMatchesCaptor.capture());
        mMatchesCaptor.getValue().onError(ErrorType.UNKNOWN_ERROR);
    }
}