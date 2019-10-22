package com.icerrate.matches.ui;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.icerrate.matches.R;
import com.icerrate.matches.ui.common.TabsAdapter;
import com.icerrate.matches.ui.common.ViewConfigurator;

/**
 * @author icerrate
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private TabLayout mTabsLayout;

    private ViewPager mViewPager;

    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.view_pager);
        mTabsLayout = findViewById(R.id.tabs);

        initTabs();
        initToolbar();
    }

    private void initTabs() {
        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mTabsAdapter.addFragment(ViewConfigurator.fixturesFragment(), getString(R.string.tab_fixtures));
        mTabsAdapter.addFragment(ViewConfigurator.resultsFragment(), getString(R.string.tab_results));
        mViewPager.setAdapter(mTabsAdapter);
        mTabsLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
    }
}