package com.icerrate.matches.utils;

import com.icerrate.matches.data.ErrorType;
import com.icerrate.matches.data.ResponseCallback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author icerrate
 */
public final class CallbackUtils {

    /**
     * Handles services responses with {@link ResponseCallback} interface.
     */
    public static <T> Callback<T> getCallback(final ResponseCallback<T> volumesCallback) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    volumesCallback.onSuccess(response.body());
                } else {
                    if (response.code() == 403) {
                        volumesCallback.onError(ErrorType.FORBIDDEN);
                    } else if (response.code() == 401) {
                        volumesCallback.onError(ErrorType.UNAUTHORIZED);
                    } else {
                        volumesCallback.onError(ErrorType.NETWORK_ERROR);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof IOException) {
                    volumesCallback.onError(ErrorType.NO_INTERNET);
                } else {
                    volumesCallback.onError(ErrorType.UNKNOWN_ERROR);
                }
            }
        };
    }
}
