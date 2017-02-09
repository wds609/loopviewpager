package com.wds.loopviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoopViewPager loop = (LoopViewPager) findViewById(R.id.loop);
        ArrayList<String> data = new ArrayList<>();
        data.add("text1");
        data.add("text2");
        data.add("text3");
        loop.setAdapter(new SimpleAdapter(data));
    }

    public static class SimpleAdapter extends LoopViewPager.LoopViewPagerAdapter<String> {

        public SimpleAdapter(ArrayList<String> datas) {
            super(datas);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.item, null);
            ((TextView) ll.findViewById(R.id.item)).setText(mDatas.get(position));
            container.addView(ll);
            return ll;
        }

    }
}
