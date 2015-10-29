package com.bbk.lling.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ListAdapter listAdapter = new ListAdapter(this, mDatas);
        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Click" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "LongClick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(listAdapter);
        /**
         * 设置布局管理器，listview风格则设置为LinearLayoutManager
         * gridview风格则设置为GridLayoutManager
         * pu瀑布流风格的设置为StaggeredGridLayoutManager
         */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置item分
        mRecyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /* ==========This Part is not necessary========= */

    /**
     * Create datas
     */
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add(String.valueOf(i));
        }
    }

    /* ==========This Part is not necessary========= */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
