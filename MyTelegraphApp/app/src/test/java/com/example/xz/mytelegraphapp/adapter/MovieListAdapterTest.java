package com.example.xz.mytelegraphapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xz.mytelegraphapp.BuildConfig;
import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.base.BaseRecyclerViewAdapter;
import com.example.xz.mytelegraphapp.base.BaseViewHolder;
import com.example.xz.mytelegraphapp.beans.MovieBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by xz on 18/05/2018.
 * Test the adapter build and click
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
public class MovieListAdapterTest {

    private BaseRecyclerViewAdapter adapter;
    private BaseViewHolder holder;
    private MovieBean.MovieItemBean itemBean;


    @Before
    public void setUp() throws Exception {
        itemBean = mock(MovieBean.MovieItemBean.class);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void itemCount() {
        List<MovieBean.MovieItemBean> data = new ArrayList<>();
        data.add(itemBean);
        data.add(itemBean);
        data.add(itemBean);
        adapter = new MovieListAdapter(RuntimeEnvironment.application, data, R.layout.movie_item_view);
        assertEquals(adapter.getItemCount(), 3);
    }

    //need to disable the picasso before have the test or context will be null
    @Test
    public void onBindViewHolder_setsTextAndClickEventForCandyView() {
        List<MovieBean.MovieItemBean> data = new ArrayList<>();
        data.add(itemBean);
        adapter = new MovieListAdapter(RuntimeEnvironment.application, data, R.layout.movie_item_view);
        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.movie_item_view, null, false);
        holder = new BaseViewHolder(listItemView);
        adapter.onBindViewHolder(holder, 0);
        holder.<LinearLayout>getView(R.id.movie_detail_layout).performClick();
        assertEquals(holder.<TextView>getView(R.id.movie_headline_tv).getText().toString(), "");


    }

}