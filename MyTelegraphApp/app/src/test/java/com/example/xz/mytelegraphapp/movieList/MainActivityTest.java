package com.example.xz.mytelegraphapp.movieList;

import android.app.Activity;
import android.content.Intent;

import com.example.xz.mytelegraphapp.BuildConfig;
import com.example.xz.mytelegraphapp.movieDetail.MovieDetailFragment;

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

import static junit.framework.Assert.assertNotNull;

/**
 * Created by xz on 17/05/2018.
 * Test the activity Lifecycle
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
public class MainActivityTest {

    private MovieDetailFragment fragment;
    private MainActivity activity;
    private ActivityController<MainActivity> activityController;
    private Activity mActivity;


    @Before
    public void setUp() throws Exception {
        //show the log
        ShadowLog.stream = System.out;
        activity = Robolectric.setupActivity(MainActivity.class);
        activityController = Robolectric.buildActivity(MainActivity.class);

    }

    @After
    public void tearDown() throws Exception {
        this.fragment = new MovieDetailFragment();
        activity = null;
        activityController = null;
        mActivity = null;
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
        Intent intent = new Intent(RuntimeEnvironment.application, MainActivity.class);
        mActivity = activityController
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }
}