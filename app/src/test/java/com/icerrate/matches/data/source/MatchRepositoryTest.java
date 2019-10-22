package com.icerrate.matches.data.source;

import com.icerrate.matches.data.DataSuit;
import com.icerrate.matches.data.ErrorType;
import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

/**
 * @author icerrate
 */
public class MatchRepositoryTest {

    private List<Match> mMatchesResponse;

    private MatchRepository mRepository;

    @Mock
    private MatchDataSource mRemoteDataSource;

    @Mock
    private ResponseCallback<List<Match>> matchesCallback;

    @Captor
    private ArgumentCaptor<ResponseCallback<List<Match>>> matchesCallbackCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mRepository = MatchRepository.getInstance(mRemoteDataSource);
        mMatchesResponse = DataSuit.getFixturesData(this.getClass());
    }

    @After
    public void destroyRepositoryInstance() {
        MatchRepository.destroyInstance();
    }

    @Test
    public void testInstance() {
        //Check same instance
        assertSame(MatchRepository.getInstance(mRemoteDataSource),
                MatchRepository.getInstance(mRemoteDataSource));
        //Destroys instance
        MatchRepository.destroyInstance();
        //Creates new instance when calling getInstance
        assertNotNull(MatchRepository.getInstance(mRemoteDataSource));
    }

    @Test
    public void testGetFixturesSuccessfully() {
        mRepository.getFixtures(matchesCallback);
        captureFixtures(mRemoteDataSource);
        verify(matchesCallback).onSuccess(mMatchesResponse);
    }

    @Test
    public void testGetFixturesWithError() {
        mRepository.getFixtures(matchesCallback);
        captureFixturesErrorType(mRemoteDataSource);
        verify(matchesCallback).onError(ErrorType.UNKNOWN_ERROR);
    }

    @Test
    public void testGetResultsSuccessfully() {
        mRepository.getResults(matchesCallback);
        captureResults(mRemoteDataSource);
        verify(matchesCallback).onSuccess(mMatchesResponse);
    }

    @Test
    public void testGetResultsWithError() {
        mRepository.getResults(matchesCallback);
        captureResultsErrorType(mRemoteDataSource);
        verify(matchesCallback).onError(ErrorType.UNKNOWN_ERROR);
    }

    private void captureFixtures(MatchDataSource dataSource) {
        verify(dataSource).getFixtures(matchesCallbackCaptor.capture());
        matchesCallbackCaptor.getValue().onSuccess(mMatchesResponse);
    }

    private void captureFixturesErrorType(MatchDataSource dataSource) {
        verify(dataSource).getFixtures(matchesCallbackCaptor.capture());
        matchesCallbackCaptor.getValue().onError(ErrorType.UNKNOWN_ERROR);
    }

    private void captureResults(MatchDataSource dataSource) {
        verify(dataSource).getResults(matchesCallbackCaptor.capture());
        matchesCallbackCaptor.getValue().onSuccess(mMatchesResponse);
    }

    private void captureResultsErrorType(MatchDataSource dataSource) {
        verify(dataSource).getResults(matchesCallbackCaptor.capture());
        matchesCallbackCaptor.getValue().onError(ErrorType.UNKNOWN_ERROR);
    }
}
