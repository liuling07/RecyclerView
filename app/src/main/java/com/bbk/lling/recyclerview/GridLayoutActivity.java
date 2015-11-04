package com.bbk.lling.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class: GridLayoutActivity
 * @Description: RecycleView实现GridView的功能
 * @author: lling(www.liuling123.com)
 * @Date: 2015/10/29
 */
public class GridLayoutActivity extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mListAdapter = new ListAdapter(this, mDatas);
        mListAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GridLayoutActivity.this, "Click" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mListAdapter.remove(position); //remove the item
                Toast.makeText(GridLayoutActivity.this, "LongClick" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mListAdapter);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        // 设置item分
        mRecyclerView.addItemDecoration(new GridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grid_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_first:
                mListAdapter.add(0, "add first");
                break;
            case R.id.add_last:
                mListAdapter.add(mListAdapter.getItemCount(), "add last");
                break;
            case R.id.remove_first:
                String value = mListAdapter.remove(0);
                Toast.makeText(GridLayoutActivity.this, "remove:" + value, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_last:
                String value1 =  mListAdapter.remove(mListAdapter.getItemCount()-1);
                Toast.makeText(GridLayoutActivity.this, "remove:" + value1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.horizontal:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.vertical:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
        }
        return super.onOptionsItemSelected(item);
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
}
