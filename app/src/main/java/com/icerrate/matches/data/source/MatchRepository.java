package com.icerrate.matches.data.source;

import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;

import java.util.List;

/**
 * @author icerrate
 */
public class MatchRepository implements MatchDataSource {

    private MatchDataSource mRemoteMatchDataSource;

    private static MatchRepository INSTANCE = null;

    public MatchRepository(MatchDataSource remoteMatchDataSource) {
        mRemoteMatchDataSource = remoteMatchDataSource;
    }

    public static MatchRepository getInstance(MatchDataSource remoteMatchDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MatchRepository(remoteMatchDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getFixtures(ResponseCallback<List<Match>> callback) {
        mRemoteMatchDataSource.getFixtures(callback);
    }

    @Override
    public void getResults(ResponseCallback<List<Match>> callback) {
        mRemoteMatchDataSource.getResults(callback);
    }
}
