package com.icerrate.matches.data.source;

import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.MatchApi;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.utils.CallbackUtils;

import java.util.List;

/**
 * @author icerrate
 */
public class MatchRemoteDataSource implements MatchDataSource {

    private static volatile MatchRemoteDataSource INSTANCE;

    private MatchApi mMatchApi;

    public MatchRemoteDataSource(final MatchApi matchAPI) {
        this.mMatchApi = matchAPI;
    }

    public static MatchRemoteDataSource getInstance(final MatchApi matchAPI) {
        if (INSTANCE == null) {
            INSTANCE = new MatchRemoteDataSource(matchAPI);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getFixtures(ResponseCallback<List<Match>> callback) {
        mMatchApi.getFixtures().enqueue(CallbackUtils.getCallback(callback));
    }

    @Override
    public void getResults(ResponseCallback<List<Match>> callback) {
        mMatchApi.getResults().enqueue(CallbackUtils.getCallback(callback));
    }
}
