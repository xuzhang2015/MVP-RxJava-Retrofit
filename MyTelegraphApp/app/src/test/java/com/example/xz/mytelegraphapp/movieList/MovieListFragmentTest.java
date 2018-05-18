package com.example.xz.mytelegraphapp.movieList;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.example.xz.mytelegraphapp.BuildConfig;
import com.example.xz.mytelegraphapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;


/**
 * Created by xz on 17/05/2018.
 * Test the fragment and recyclerView
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
public class MovieListFragmentTest {
    private MovieListFragment fragment;
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();

        this.fragment = new MovieListFragment();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }

    @After
    public void tearDown() throws Exception {
        fragment = null;
        activity = null;
    }

    @Test
    public void fragmentIsNotNull() {
        assertNotNull(fragment);
    }

    @Test
    public void testRecyclerView() throws Exception {

        RecyclerView recycler = (RecyclerView) fragment.getActivity().findViewById(R.id.movie_list_recycler_view);
        // test recyclerView issue
        recycler.measure(0, 0);
        recycler.layout(0, 0, 100, 1000);
    }
}