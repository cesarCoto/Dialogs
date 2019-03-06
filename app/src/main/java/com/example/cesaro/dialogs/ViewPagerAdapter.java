package com.example.cesaro.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ViewPagerAdapter extends PagerAdapter {


    int post = 0;

    private Context context;
    private LayoutInflater inflater;
    private String[] urlImage;
    private String [] titles;



    public ViewPagerAdapter(Context context, String[] urlImage, String[] titles) {
        this.context = context;
        this.urlImage = urlImage;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {



        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.my_clayout,null);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewBanner);
        final TextView textView = (TextView) view.findViewById(R.id.textView);
        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.lay);

        textView.setText(titles[post]);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                    .load(urlImage[post])
                    .apply(requestOptions)
                    .into(imageView);


        ViewPager viewPager = (ViewPager) container;

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(textView.getText()), Toast.LENGTH_SHORT).show();
            }
        });

        if(post >= urlImage.length - 1)
            post =0;
        else
            ++post;



        viewPager.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;

        View view = (View)object;

        viewPager.removeView(view);

    }
}
