package com.icerrate.matches.data.source;

import com.icerrate.matches.data.DataSuit;
import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.MatchApi;
import com.icerrate.matches.data.model.Match;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author icerrate
 */
public class MatchRemoteDataSourceTest {

    private MatchRemoteDataSource mRemoteDataSource;

    @Mock
    private MatchApi mMatchApi;

    @Mock
    private ResponseCallback<List<Match>> matchesCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mRemoteDataSource = MatchRemoteDataSource.getInstance(mMatchApi);
    }

    @After
    public void destroyRepositoryInstance() {
        MatchRemoteDataSource.destroyInstance();
    }

    @Test
    public void testInstance() {
        //Check same instance
        assertSame(MatchRemoteDataSource.getInstance(mMatchApi),
                MatchRemoteDataSource.getInstance(mMatchApi));
        //Destroys instance
        MatchRemoteDataSource.destroyInstance();
        //Creates new instance when calling getInstance
        assertNotNull(MatchRemoteDataSource.getInstance(mMatchApi));
    }

    @Test
    public void testGetFixturesSuccessfully() {
        List<Match> response = DataSuit.getFixturesData(this.getClass());
        Call<List<Match>> call = Calls.response(Response.success(response));
        when(mMatchApi.getFixtures()).thenReturn(call);
        mRemoteDataSource.getFixtures(matchesCallback);
        verify(matchesCallback).onSuccess(response);
    }

    @Test
    public void testGetResultsSuccessfully() {
        List<Match> response = DataSuit.getResultsData(this.getClass());
        Call<List<Match>> call = Calls.response(Response.success(response));
        when(mMatchApi.getResults()).thenReturn(call);
        mRemoteDataSource.getResults(matchesCallback);
        verify(matchesCallback).onSuccess(response);
    }
}
