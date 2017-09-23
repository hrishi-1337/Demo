package com.hrishikesh.Demo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);
    SearchView searchView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ImageButton imageButton1 = (ImageButton) findViewById(R.id.button1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, GridActivity.class);
                //Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left1,R.anim.left2).toBundle();
                startActivity(intent);

            }
        });

        ImageButton imageButton3 = (ImageButton) findViewById(R.id.button3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
                //Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.right1,R.anim.right2).toBundle();
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // adds item to action bar
        getMenuInflater().inflate(R.menu.search_menu, menu);

        // Get Search item from action bar and Get Search service
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setIconified(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    // Every time when you press search button on keypad an Activity is recreated which in turn calls this function
    @Override
    protected void onNewIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }

            // User entered text and pressed search button. Perform task ex: fetching data from database and display
            final ArrayList<String> list = new ArrayList<String>();
            ListView listview = (ListView) findViewById(R.id.list_view);
            List<Shop> shops = db.getSearch(query);
            for (Shop shop : shops) {
                list.add(shop.getName());
            }
            listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    String name = list.get(position);
                    Intent intent = new Intent(SearchActivity.this, ShopActivity.class);
                    intent.putExtra("name",name);
                    //Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.right1, R.anim.right2)
                    //Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });
        }
    }

}