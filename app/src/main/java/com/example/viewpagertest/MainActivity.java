package com.example.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.viewpagertest.fragment.adapter.FragAdapter;
import com.example.viewpagertest.fragment.Fragment222;
import com.example.viewpagertest.fragment.Fragment111;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private RadioGroup rgTabButtons;
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            //viewPager的第arg0页处于当前屏幕的时候，将当前的arg0值赋值给记录Fragment的标签
//            mCurrentFragment=arg0;
            //将下面tab的RadioGroup的第arg0个radioButton设为选中状态
            ((RadioButton) rgTabButtons.getChildAt(arg0)).setChecked(true);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //设置一个标签，用来记录当前所选的radioButton值
                    int checkedItem = 0;
                    switch (checkedId) {
                        case R.id.rb_localFile:
                            checkedItem = 0;
                            break;
                        case R.id.rb_classifyFile:
                            checkedItem = 1;
                            break;
                    }
                    //将ViewPager的第checkedItem个页面设为当前屏幕的展示页面
                    vp.setCurrentItem(checkedItem);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment111());
        fragments.add(new Fragment222());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器，使用ViewPager实现滑动两个fragment.
        vp = (ViewPager) findViewById(R.id.vpContainer);
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(onPageChangeListener);

        rgTabButtons = (RadioGroup) findViewById(R.id.rgTabBtns);
        rgTabButtons.setOnCheckedChangeListener(onCheckedChangeListener);
        //设置RadioGroup的第一个RadioButton为初始状态为选中状态
        ((RadioButton) rgTabButtons.getChildAt(0)).setChecked(true);
    }

}
