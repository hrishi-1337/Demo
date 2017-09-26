package com.hrishikesh.Demo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ShopActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ImageView header;
    Toolbar toolbarFlexibleSpace;
    CollapsingToolbarLayout collapsingToolbar;

    String name,url;
    List<Product> products;
    DBHandler db = new DBHandler(this);
    ProductDBHandler db2 = new ProductDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shop);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        header = (ImageView) findViewById(R.id.header);
        toolbarFlexibleSpace = (Toolbar) findViewById(R.id.toolbar_flexible_space);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        name = getIntent().getStringExtra("name");
        Shop info = db.getShop(name);
        url = info.getUrl();

        initView();
    }

    private void initView() {

        toolbarFlexibleSpace.setTitle(name);
        setSupportActionBar(toolbarFlexibleSpace);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Glide.with(this).load(url).into(header);
        toolbarFlexibleSpace.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopActivity.super.onBackPressed();
            }
        });

        products = db2.getAllProducts();
        int numberOfColumns = 2;
        ProductAdapter adapter = new ProductAdapter(this,products);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mRecyclerView.setAdapter(adapter);

    }
}