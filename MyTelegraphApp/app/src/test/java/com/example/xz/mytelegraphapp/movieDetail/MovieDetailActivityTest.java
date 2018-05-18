package com.example.xz.mytelegraphapp.movieDetail;

import android.app.Activity;
import android.content.Intent;

import com.example.xz.mytelegraphapp.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by xz on 17/05/2018.
 * test the activity lifecycle and start the fragment
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
public class MovieDetailActivityTest {
    private MovieDetailActivity activity;
    private ActivityController<MovieDetailActivity> activityController;
    private Activity mActivity;
    private MovieDetailFragment fragment;


    @Before
    public void setUp() throws Exception {
        //show the log
        ShadowLog.stream = System.out;

        activity = Robolectric.setupActivity(MovieDetailActivity.class);
        this.fragment = (MovieDetailFragment) this.activity.getSupportFragmentManager().findFragmentByTag("Detail");
        activityController = Robolectric.buildActivity(MovieDetailActivity.class);

    }

    @After
    public void tearDown() throws Exception {
        activity = null;
        activityController = null;
        mActivity = null;
        fragment = null;
    }

    @Test
    public void textActivity() throws Exception {
        assertNotNull(activity);
    }

    // Test that simulates the full lifecycle of an activity
    @Test
    public void testLifecycle() throws Exception {
        createWithIntent();
        assertNotNull(mActivity);
    }

    @Test
    public void pausesAndResumesActivity() {
        createWithIntent();
        mActivity = activityController.pause().resume().get();
        assertNotNull(mActivity);
    }


    private void createWithIntent() {
        Intent intent = new Intent(RuntimeEnvironment.application, MovieDetailActivity.class);
        mActivity = activityController
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void testStartFragment() {
        //此api可以主动添加Fragment到Activity中，因此会触发Fragment的onCreateView()
        SupportFragmentTestUtil.startFragment(fragment);
        assertNotNull(fragment.getView());
    }

}