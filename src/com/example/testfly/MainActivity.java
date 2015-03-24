package com.example.testfly;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.R.integer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private PagerTitleStrip mPagerTitleStrip;
	private int[] pics={ R.drawable.a,R.drawable.b,R.drawable.c};//定义资源里的图片数组
	final ArrayList<View> views=new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mPagerTitleStrip=(PagerTitleStrip)findViewById(R.id.pagertitle);
        LinearLayout.LayoutParams mParams=new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i=0;i<pics.length;i++)
        {
        	ImageView iv=new ImageView(this);
        	iv.setLayoutParams(mParams);
        	iv.setImageResource(pics[i]);
        	views.add(iv);
        }
        final ArrayList<String>titleStrings=new ArrayList<String>();
        titleStrings.add("tab1");
        titleStrings.add("tab2");
        titleStrings.add("tab3");
        
        PagerAdapter mPagerAdapter=new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}
			@Override
			public void destroyItem(View container,int position,Object object){
				((ViewPager)container).removeView(views.get(position));
			}
			@Override
			public CharSequence getPageTitle(int position){
				return titleStrings.get(position);
			}
			@Override
			public Object instantiateItem(View container,int position){
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
