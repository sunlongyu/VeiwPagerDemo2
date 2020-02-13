package com.example.viewpagerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    int[] layouts = {
//            R.layout.first,
//            R.layout.second,
//            R.layout.third
//    };
    private List<View> views;
    private ViewPager viewPager;
    private ViewGroup dotViewGroup;
    private List<ImageView> dotViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dotViewGroup = findViewById(R.id.linerLayout);
        views = new ArrayList<>();
        dotViews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            views.add(imageView);

            //新建导航要用的圆点
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxWidth(100);
            dot.setMaxHeight(100);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80, 80);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);

            dotViewGroup.addView(dot);

            //将每个点加入集合中以便下面的listener调用
            dotViews.add(dot);

        }


        viewPager.setAdapter(pagerAdapter);
        //设置左右各两个viewPager
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0);
        setDotImage(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setDotImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //设置当前的显示的小圆点
    private void setDotImage(int position) {
        for (int i = 0; i < 3; i++) {
            dotViews.get(i).setImageResource(i == position ? R.drawable.ic_android_pink_24dp : R.drawable.ic_android_black_24dp);
        }
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = views.get(position);
            container.removeView(view);
        }
    };
}
