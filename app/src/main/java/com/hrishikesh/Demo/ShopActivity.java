package com.hrishikesh.Demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShopActivity extends AppCompatActivity {

    String name,url;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        name = getIntent().getStringExtra("name");
        TextView textView;
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(name));
        Shop info = db.getShop(name);
        url = info.getUrl();
        //Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load(url).into(imageView);
    }
}
