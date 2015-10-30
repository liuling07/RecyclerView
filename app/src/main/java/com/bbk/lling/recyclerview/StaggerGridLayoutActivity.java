package com.bbk.lling.recyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class StaggerGridLayoutActivity extends ActionBarActivity {
    private RecyclerView mRecyclerView;
    private StaggerGridAdapter mAdapter;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger_grid_layout);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new StaggerGridAdapter(this, mDatas);
        mAdapter.setOnItemClickListener(new StaggerGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggerGridLayoutActivity.this, "Click" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mAdapter.remove(position); //remove the item
                Toast.makeText(StaggerGridLayoutActivity.this, "LongClick" + mDatas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stagger_grid_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_first:
                mAdapter.add(0, "add first");
                break;
            case R.id.add_last:
                mAdapter.add(mAdapter.getItemCount(), "add last");
                break;
            case R.id.remove_first:
                String value = mAdapter.remove(0);
                Toast.makeText(StaggerGridLayoutActivity.this, "remove:" + value, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_last:
                String value1 =  mAdapter.remove(mAdapter.getItemCount()-1);
                Toast.makeText(StaggerGridLayoutActivity.this, "remove:" + value1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.horizontal:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.vertical:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
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
