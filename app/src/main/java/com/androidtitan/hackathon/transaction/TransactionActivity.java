package com.androidtitan.hackathon.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;

import butterknife.ButterKnife;

public class TransactionActivity extends BaseActivity {

    private final static int NUMBER_OF_PAGES = 2;
    private static final String EXTRA_SELECTED_PAGE = "EXTRA_SELECTED_PAGE";

    public static Intent newIntent(Context context, int selectedPage) {
        Intent intent = new Intent(context, TransactionActivity.class);
        intent.putExtra(EXTRA_SELECTED_PAGE, selectedPage);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payerpayee);
        ButterKnife.bind(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Send Money"));
        tabLayout.addTab(tabLayout.newTab().setText("Receive Money"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_layout);
        final TransactionTypeAdapter adapter = new TransactionTypeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (getIntent().getExtras().getInt(EXTRA_SELECTED_PAGE) == 0) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
    }

    private static class TransactionTypeAdapter extends FragmentPagerAdapter {

        public TransactionTypeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return  NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? SendMoneyFragment.newInstance() : ReceiveMoneyFragment.newInstance();
        }
    }
}
