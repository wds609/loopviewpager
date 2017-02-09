package com.wds.loopviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class LoopViewPager extends ViewPager {
    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        addOnPageChangeListener(new LoopOnPageChangeListener(this));
        setCurrentItem(1);
        setOffscreenPageLimit(adapter.getCount());
    }

    public static class LoopOnPageChangeListener implements OnPageChangeListener {
        private LoopViewPager mLoopViewPager;
        private int mFinalPosition;

        public LoopOnPageChangeListener(LoopViewPager loopViewPager) {
            this.mLoopViewPager = loopViewPager;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mFinalPosition = position;

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                PagerAdapter adapter = mLoopViewPager.getAdapter();
                if (mFinalPosition == 0) {
                    mLoopViewPager.setCurrentItem(adapter.getCount() - 2, false);
                }
                if (mFinalPosition == adapter.getCount() - 1) {
                    mLoopViewPager.setCurrentItem(1, false);
                }
            }
        }
    }

    public static abstract class LoopViewPagerAdapter<T> extends PagerAdapter {
        protected ArrayList<T> mDatas = new ArrayList<>();

        public LoopViewPagerAdapter(ArrayList<T> datas) {
            if (datas != null && datas.size() != 0) {
                mDatas.add(datas.get(datas.size() - 1));
                mDatas.addAll(datas);
                mDatas.add(datas.get(0));
            }
        }

        @Override
        public final int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
