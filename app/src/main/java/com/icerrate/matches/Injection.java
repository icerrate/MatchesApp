package com.icerrate.matches;

import com.icerrate.matches.data.RetrofitModule;
import com.icerrate.matches.data.source.MatchRemoteDataSource;
import com.icerrate.matches.data.source.MatchRepository;

/**
 * @author icerrate
 */
public class Injection {

    public static MatchRepository matchRepository() {
        MatchRemoteDataSource remoteDataSource = MatchRemoteDataSource.getInstance(
                RetrofitModule.get().provideMatchApi());
        return MatchRepository.getInstance(remoteDataSource);
    }
}
