package com.icerrate.matches.data;

import com.icerrate.matches.data.model.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author icerrate
 */
public interface MatchApi {

    @GET(ServerPaths.FIXTURES_URL)
    Call<List<Match>> getFixtures();

    @GET(ServerPaths.RESULTS_URL)
    Call<List<Match>> getResults();
}
