package com.hrishikesh.Demo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AsymmetricGridView listView;
    private DemoAdapter adapter;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (AsymmetricGridView) findViewById(R.id.listView);

        if (savedInstanceState == null) {
            adapter = new DefaultListAdapter(this, getMoreItems(50));
        } else {
            adapter = new DefaultListAdapter(this);
        }
        listView.setRequestedColumnCount(6);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        listView.setAdapter(getNewAdapter());
        listView.setDebugging(true);
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.button1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation1,R.anim.animation2).toBundle();
                startActivity(intent, bndlanimation);

            }
        });
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.button3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation3,R.anim.animation4).toBundle();
                startActivity(intent, bndlanimation);

            }
        });


/*
        db.addShop(new Shop(1,"Dockers","https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/storefront27.jpg?alt=media&token=4ea27811-4e06-4bf5-8b96-44a583040088",""));
        db.addShop(new Shop(2,"Dunkin Donuts","https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/dunkin.jpg?alt=media&token=f63c6d5f-b074-47ae-a8ca-6f7974ddbd47",""));
        db.addShop(new Shop(3,"Pizza Parlor","https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download.jpg?alt=media&token=1eb9a9bb-495d-416b-9a84-3699214149b9",""));
        db.addShop(new Shop(4,"Town Bakers","https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download%20(1).jpg?alt=media&token=01b16918-272f-4a9f-9607-61f559d30360",""));
  */
    }

    private AsymmetricGridViewAdapter<?> getNewAdapter() {
        return new AsymmetricGridViewAdapter<>(this, listView, adapter);
    }

    private List<DemoItem> getMoreItems(int qty) {
        List<DemoItem> items = new ArrayList<>();
        List<Shop> shops = db.getAllShops();
        int a[] = {4,2,2,2,2,2,3,3,2,2,4,2,2,2,3,3};
        int j=0;
        for (Shop shop : shops){
            if(j<=15) {
                DemoItem item = new DemoItem(a[j],a[j],shop.getId(),shop.getName(),shop.getAddress());
                items.add(item);
                j++;
            }
            else
                j=0;
        }
        return items;

    }
}