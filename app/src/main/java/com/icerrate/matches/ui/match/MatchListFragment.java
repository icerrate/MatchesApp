package com.icerrate.matches.ui.match;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icerrate.matches.R;
import com.icerrate.matches.data.model.Match;
import com.icerrate.matches.ui.match.fixture.FixtureListAdapter;
import com.icerrate.matches.ui.match.result.ResultListAdapter;

import java.util.List;

/**
 * @author icerrate
 */
public class MatchListFragment extends Fragment implements MatchContract.View {

    private final static String MATCH_TYPE = "match_type";

    private SearchView searchView = null;

    private RecyclerView mRecyclerView;

    private ProgressBar mProgressView;

    private AppCompatTextView mNoDataTextView;

    private MatchListAdapter mAdapter;

    private MatchContract.Presenter mPresenter;

    public static MatchListFragment newInstance(int type) {
        MatchListFragment fragment = new MatchListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MATCH_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.items);
        mProgressView = view.findViewById(R.id.progress);
        mNoDataTextView = view.findViewById(R.id.no_data);

        if (savedInstanceState == null && getArguments() != null) {
            int matchType = getArguments().getInt(MATCH_TYPE);
            initAdapter(matchType);
        }
    }

    private void initAdapter(int matchType) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        switch (matchType) {
            case Match.RESULTS_TYPE:
                mAdapter = new ResultListAdapter();
                break;
            case Match.FIXTURE_TYPE: default:
                mAdapter = new FixtureListAdapter();
                break;
        }
        mAdapter.setFilterListener(new MatchListAdapter.FilterListener() {
            @Override
            public void onResults(boolean isEmpty) {
                showNoDataFound(isEmpty);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setPresenter(MatchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingView(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNoDataFound(boolean show) {
        mNoDataTextView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadMatches(List<Match> matchList) {
        mAdapter.setItems(matchList);
    }
}
