package com.icerrate.matches.data;

/**
 * @author icerrate
 */
public interface ResponseCallback<T> {

    void onSuccess(T response);

    void onError(ErrorType errorType);
}