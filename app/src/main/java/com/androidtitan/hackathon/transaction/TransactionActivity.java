package com.androidtitan.hackathon.transaction;

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
