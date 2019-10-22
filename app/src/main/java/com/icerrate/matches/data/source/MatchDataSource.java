package com.icerrate.matches.data.source;

import com.icerrate.matches.data.ResponseCallback;
import com.icerrate.matches.data.model.Match;

import java.util.List;

/**
 * @author icerrate
 */
public interface MatchDataSource {

    void getFixtures(ResponseCallback<List<Match>> callback);

    void getResults(ResponseCallback<List<Match>> callback);
}
