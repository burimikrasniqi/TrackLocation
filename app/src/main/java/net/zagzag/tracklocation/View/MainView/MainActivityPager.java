package net.zagzag.tracklocation.View.MainView;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import net.zagzag.tracklocation.Presenter.MainPresenter;
import net.zagzag.tracklocation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivityPager extends MvpActivity<MainInterface, MainPresenter> implements MainInterface ,LunchFragment.OnFragmentInteractionListener {

    @BindView(R.id.pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }


    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return LunchFragment.newInstance("","");
                case 1:
                    return new RecyclerFragment();
                case 2:
                    return new MapsFragments();
                default:
                    return new LunchFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
