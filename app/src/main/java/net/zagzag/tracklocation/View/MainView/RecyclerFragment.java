package net.zagzag.tracklocation.View.MainView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zagzag.tracklocation.Model.UserData;
import net.zagzag.tracklocation.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by burim on 12/29/2016.
 */

public class RecyclerFragment extends Fragment {
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutAdapter;
    @BindView(R.id.my_recycler_view)
    RecyclerView mRecycler;
    @BindView(R.id.listRefresh)
    SwipeRefreshLayout mListRefresh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    public void init() {
        ///Recycler View
        mLayoutAdapter = new LinearLayoutManager(getActivity());
        mAdapter = new RecyclerAdapter();

        mRecycler.setLayoutManager(mLayoutAdapter);
        mRecycler.setAdapter(mAdapter);
        mAdapter.addAll(getDummyData());
        //refresh list
        mListRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListRefresh.setRefreshing(false);
            }
        });
    }

    private ArrayList<UserData> getDummyData() {
        ArrayList<UserData> list = new ArrayList<UserData>();
        for (int i = 0; i < 100; i++) {
            UserData user = new UserData("","","");
            list.add(user);
        }
        return list;
    }

}
