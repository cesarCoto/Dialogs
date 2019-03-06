package com.example.cesaro.dialogs;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SliderActivity extends AppCompatActivity {

    ViewPager viewPager;
    private Handler handler = new Handler();

    String [] urlImage = {
            "https://cdn.pixabay.com/photo/2016/05/09/19/26/puffin-1382275_960_720.jpg",
            "https://cdn.pixabay.com/photo/2018/09/05/04/21/flowers-3655451_960_720.jpg",
            "https://cdn.pixabay.com/photo/2019/02/26/04/04/mountain-4021090_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/07/19/08/50/gardening-2518377_960_720.jpg",
            "https://cdn.pixabay.com/photo/2019/03/01/12/24/lotus-flower-4027825_960_720.jpg",
            "https://cdn.pixabay.com/photo/2018/06/03/01/00/water-lilies-3449574_960_720.jpg"
    };

    String[] titles = {
           "Pinguino",
           "Flor",
           "Montaña",
           "Manos",
           "Hikari",
           "Loto"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);


        viewPager =  findViewById(R.id.myViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,urlImage, titles);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(viewPagerAdapter);



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.i("Pager", String.valueOf(viewPager.getCurrentItem() + 1));
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                handler.postDelayed(this,9000);

            }
        },9000);

       }
}
