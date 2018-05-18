package com.example.xz.mytelegraphapp.moviePoster;

import android.app.ProgressDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.xz.mytelegraphapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xz on 17/05/2018.
 * show the poster
 */
public class PosterActivity extends AppCompatActivity {
    //simple activity, so not using any MVP patten. this will avoid the interface overload

    @BindView(R.id.show_poster_iv)
    ImageView show_poster_iv;

    private String url;
    private ProgressDialog dialog;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the frame on the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_poster);
        //
        unbinder = ButterKnife.bind(this);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.show();
        //
        url = getIntent().getExtras().getString("WEB_URL");
        if (savedInstanceState == null) {
            LoadThePoster();
        }
        //


    }

    //Picasso to handle the image download and progressbar
    private void LoadThePoster() {
        Picasso.get()
                .load(url)
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(show_poster_iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        BitmapDrawable drawable = (BitmapDrawable) show_poster_iv.getDrawable();
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("Poster", e.getMessage());
                    }
                });
    }

    @OnClick(R.id.show_poster_iv)
    public void byClick(View view) {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("INDEX", 1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
