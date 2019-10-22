package com.icerrate.matches.ui.match;

/**
 * @author icerrate
 */
public interface MatchView<T> {

    void setPresenter(T presenter);

    void showLoadingView(boolean show);

    void showNoDataFound(boolean show);
}
