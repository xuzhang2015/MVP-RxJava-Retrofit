package com.example.xz.mytelegraphapp.movieDetail;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.xz.mytelegraphapp.BuildConfig;
import com.example.xz.mytelegraphapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowWebView;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by xz on 17/05/2018.
 * test the fragment and webview
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "AndroidManifest.xml")
public class MovieDetailFragmentTest {
    private MovieDetailFragment fragment;
    private MovieDetailActivity activity;
    //
    private WebView webView;
    private ShadowWebView shadowWebView;
    //


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MovieDetailActivity.class)
                .create()
                .start()
                .resume()
                .get();

        this.fragment = new MovieDetailFragment();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, "Detail");
        fragmentTransaction.commit();
        //
        webView = (WebView) fragment.getActivity().findViewById(R.id.movie_detail_wv);
        shadowWebView = shadowOf(webView);
    }

    @After
    public void tearDown() throws Exception {
        fragment = null;
    }

    @Test
    public void fragmentIsNotNull() {
        assertNotNull(fragment);
    }


    @Test
    public void testWebView() throws Exception {

        webView.loadUrl("https://www.google.co.uk/");
        assertEquals(shadowOf(webView).getLastLoadedUrl(), "https://www.google.co.uk/");

    }

    @Test
    public void shouldRecordLastLoadedData() {
        webView.loadData("<html><body><h1>Hi</h1></body></html>", "text/html", "utf-8");
        ShadowWebView.LoadData lastLoadData = shadowOf(webView).getLastLoadData();
        assertEquals(lastLoadData.data, "<html><body><h1>Hi</h1></body></html>");
        assertEquals(lastLoadData.mimeType, "text/html");
        assertEquals(lastLoadData.encoding, "utf-8");
    }

    @Test
    public void shouldReturnSettings() {
        WebSettings webSettings = webView.getSettings();
        assertNotNull(webSettings);
    }

    @Test
    public void shouldRecordClearCacheWithoutDiskFiles() {
        assertFalse(shadowWebView.wasClearCacheCalled());
        webView.clearCache(false);
        assertTrue(shadowWebView.wasClearCacheCalled());
        assertFalse(shadowWebView.didClearCacheIncludeDiskFiles());
    }

}