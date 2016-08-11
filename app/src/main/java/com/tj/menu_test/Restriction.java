package com.tj.menu_test;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/*
This is the activity that restrict user's using apps
 */
public class Restriction extends Activity {
    private TextView hr;
    private TextView mi;
    private TextView se;
    private MyCount mc;
    private int hour=1;//需要Main_Activity传来，暂时直接给定
    private int minute=30;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restriction_layout);

        //倒计时
        hr = (TextView)findViewById(R.id.hour);
        mi=(TextView)findViewById(R.id.minute);
        se=(TextView)findViewById(R.id.second);
        mc = new MyCount(hour*60*60*1000+minute*60*1000, 1000);
        mc.start();

        //显示可使用应用列表
        GridView gridview=(GridView)findViewById(R.id.gridView);
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.mipmap.ic_launcher);//添加图像资源的ID
            map.put("ItemText", "NO."+String.valueOf(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释
                lstImageItem,//数据来源
                R.layout.app_icon,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage","ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage,R.id.ItemText});
        //添加并且显示
        gridview.setAdapter(saImageItems);
    }

    //倒计时
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            //退出监督
        }
        @Override
        public void onTick(long millisUntilFinished) {
            int remain_hour,remain_min,remain_sec;
            long temp=millisUntilFinished/1000;
            remain_sec=(int)temp%60;
            temp/=60;
            remain_min=(int)temp%60;
            temp/=60;
            remain_hour=(int)temp;
            hr.setText(""+remain_hour);
            mi.setText(""+remain_min);
            se.setText(""+remain_sec);

        }
    }

}
