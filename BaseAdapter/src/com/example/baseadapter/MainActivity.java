package com.example.baseadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends Activity {

	private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ItemBean> itemBeanList=new ArrayList<ItemBean>();
        for(int i=0;i<20;i++)
        {
        	itemBeanList.add(new ItemBean(R.drawable.ic_launcher,"我是标题"+i,"我是内容"+i));
        }
        listView=(ListView) findViewById(R.id.iv_main);
        listView.setAdapter(new MyAdapter(this,itemBeanList));
        
    }


}
