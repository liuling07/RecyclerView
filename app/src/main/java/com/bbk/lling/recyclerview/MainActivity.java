package com.bbk.lling.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.list_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListLayoutActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.grid_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridLayoutActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.staggergrid_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StaggerGridLayoutActivity.class);
                startActivity(intent);
            }
        });

    }

}
