package com.example.xz.mytelegraphapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.Utils.UtilsMethod;
import com.example.xz.mytelegraphapp.base.BaseRecyclerViewAdapter;
import com.example.xz.mytelegraphapp.base.BaseViewHolder;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xz on 16/05/2018.
 */
public class MovieListAdapter extends BaseRecyclerViewAdapter<MovieBean.MovieItemBean> {


    public MovieListAdapter(Context context, List<MovieBean.MovieItemBean> data, int layoutId) {
        super(context, data, layoutId);
    }


    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected void bindData(final BaseViewHolder holder, final MovieBean.MovieItemBean data, int position) {
        String uri = data.getPicture_url();
        Picasso.get().load(uri).fit()
                .centerInside().error(R.drawable.ic_launcher_foreground).into(holder.<ImageView>getView(R.id.movie_poster_iv));
        //
        holder.<TextView>getView(R.id.movie_headline_tv).setText(data.getHeadline());
        holder.<TextView>getView(R.id.movie_description_tv).setText(data.getDescription());
        holder.<TextView>getView(R.id.movie_rate_tv).setText("Rate:" + String.valueOf(data.getRatings()));
        holder.<TextView>getView(R.id.movie_actor_tv).setText(UtilsMethod.getActorString(data.getActors()));
        //
        holder.<ImageView>getView(R.id.movie_poster_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListenerll) {
                    int pos = holder.getLayoutPosition();
                    //register the clicklistener to the onItemClickListenerll
                    onItemClickListenerll.onItemClickListener(view, pos);
                }
            }
        });

        holder.<LinearLayout>getView(R.id.movie_detail_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListenerll) {
                    int pos = holder.getLayoutPosition();
                    //register the clicklistener to the onItemClickListenerll
                    onItemClickListenerll.onItemClickListener(view, pos);
                }
            }
        });

    }
}