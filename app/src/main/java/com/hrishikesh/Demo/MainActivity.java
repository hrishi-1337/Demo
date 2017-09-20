package com.hrishikesh.Demo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {

    private static final String TAG = "MainActivity";
    private AsymmetricGridView listView;
    private DemoAdapter adapter;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    LocationRequest mLocationRequest;
    //double lat = 19.167405;
    //double lng = 72.879781;
    double lat,lng;
    boolean chosen1 = false;
    boolean connected= false;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (AsymmetricGridView) findViewById(R.id.listView);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();

        chosen1 = getIntent().getBooleanExtra("chosen", false);
        if(chosen1){
            lat = getIntent().getDoubleExtra("chosenLat", 0.0);
            lng = getIntent().getDoubleExtra("chosenLng", 0.0);
            Toast.makeText(this, lat + " or " + lng, Toast.LENGTH_LONG).show();
        }


        //Toast.makeText(this, lat + " and " + lng, Toast.LENGTH_LONG).show();

        /*if (savedInstanceState == null) {
            adapter = new DefaultListAdapter(this, getMoreItems(50));

        } else {
            adapter = new DefaultListAdapter(this);
        }
        listView.setRequestedColumnCount(6);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        listView.setAdapter(getNewAdapter());
        listView.setDebugging(true);
        listView.setNestedScrollingEnabled(true);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (mLastFirstVisibleItem < firstVisibleItem) {
                    Log.i("SCROLLING DOWN", "TRUE");
                }
                if (mLastFirstVisibleItem > firstVisibleItem) {
                    Log.i("SCROLLING UP", "TRUE");
                }
                mLastFirstVisibleItem = firstVisibleItem;

            }
        });
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.button3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation3, R.anim.animation4).toBundle();
                startActivity(intent, bndlanimation);

            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.button2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation3, R.anim.animation4).toBundle();
                startActivity(intent, bndlanimation);
            }
        }); */

/*
        db.addShop(new Shop(1, "Dockers", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/storefront27.jpg?alt=media&token=4ea27811-4e06-4bf5-8b96-44a583040088", 19.172151, 72.873733));
        db.addShop(new Shop(2, "Dunkin Donuts", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/dunkin.jpg?alt=media&token=f63c6d5f-b074-47ae-a8ca-6f7974ddbd47",19.174745, 72.835120));
        db.addShop(new Shop(3, "Pizza Parlor", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download.jpg?alt=media&token=1eb9a9bb-495d-416b-9a84-3699214149b9", 19.122123, 72.827567));
        db.addShop(new Shop(4, "Town Bakers", "", "https://firebasestorage.googleapis.com/v0/b/fir-87605.appspot.com/o/download%20(1).jpg?alt=media&token=01b16918-272f-4a9f-9607-61f559d30360", 19.151315, 77.573661));
*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                Intent intent = new Intent(this, LocationActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private AsymmetricGridViewAdapter<?> getNewAdapter() {
        return new AsymmetricGridViewAdapter<>(this, listView, adapter);
    }

   /* private List<DemoItem> getMoreItems(int qty) {

        List<DemoItem> items = new ArrayList<>();
        List<Shop> shops = db.getShops(lat,lng);
        int a[] = {4, 2, 2, 2, 2, 2, 3, 3, 2, 2, 4, 2, 2, 2, 3, 3};
        int j = 0;
        for (Shop shop : shops) {
            if (j <= 15) {
                DemoItem item = new DemoItem(a[j], a[j], shop.getId(), shop.getName(), shop.getUrl());
                items.add(item);
                j++;
            } else
                j = 0;
        }
        return items;
    } */

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,this);
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null && !chosen1) {
            lat = mLastLocation.getLatitude();
            lng = mLastLocation.getLongitude();
            connected = true;
            //LatLng loc = new LatLng(lat, lng);
            //Toast.makeText(this, lat + " or " + lng, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null && !chosen1)
        {
            lat = location.getLatitude();
            lng = location.getLongitude();
            //Toast.makeText(this, lat + " or " + lng, Toast.LENGTH_LONG).show();
            List<DemoItem> items = new ArrayList<>();
            List<Shop> shops = db.getShops(lat,lng);
            int a[] = {4, 2, 2, 2, 2, 2, 3, 3, 2, 2, 4, 2, 2, 2, 3, 3};
            int j = 0;
            for (Shop shop : shops) {
                if (j <= 15) {
                    DemoItem item = new DemoItem(a[j], a[j], shop.getId(), shop.getName(), shop.getUrl());
                    items.add(item);
                    j++;
                } else
                    j = 0;
            }

            adapter = new DefaultListAdapter(this, items);


            listView.setRequestedColumnCount(6);
            listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
            listView.setAdapter(getNewAdapter());
            listView.setDebugging(true);
            listView.setNestedScrollingEnabled(true);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                private int mLastFirstVisibleItem;

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {

                    if (mLastFirstVisibleItem < firstVisibleItem) {
                        Log.i("SCROLLING DOWN", "TRUE");
                    }
                    if (mLastFirstVisibleItem > firstVisibleItem) {
                        Log.i("SCROLLING UP", "TRUE");
                    }
                    mLastFirstVisibleItem = firstVisibleItem;

                }
            });
            ImageButton imageButton3 = (ImageButton) findViewById(R.id.button3);
            imageButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation3, R.anim.animation4).toBundle();
                    startActivity(intent, bndlanimation);

                }
            });

            ImageButton imageButton2 = (ImageButton) findViewById(R.id.button2);
            imageButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation3, R.anim.animation4).toBundle();
                    startActivity(intent, bndlanimation);
                }
            });

        }
    }
}