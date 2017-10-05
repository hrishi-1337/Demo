package com.hrishikesh.Demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    Button login;
    Button logout;
    TextView welcome;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        login = (Button) findViewById(R.id.login);
        logout = (Button) findViewById(R.id.logout);
        welcome = (TextView) findViewById(R.id.welcome);
        FirebaseAuth firebaseAuth = mAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
       FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null) {
                    if(user.getDisplayName() != null) {
                        name = "Welcome " + user.getDisplayName();
                    }else{
                        name = "Welcome ";
                    }
                    welcome.setText(name);
                    login.setVisibility(View.GONE);
                    welcome.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.VISIBLE);
                } else {
                    login.setVisibility(View.VISIBLE);
                    welcome.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.GONE);
                }
            }
        };
       if (user != null) {
           if(user.getDisplayName() != null) {
               name = "Welcome " + user.getDisplayName();
           }else{
               name = "Welcome ";
           }
           welcome.setText(name);
           login.setVisibility(View.GONE);
           welcome.setVisibility(View.VISIBLE);
           logout.setVisibility(View.VISIBLE);
       } else {
           login.setVisibility(View.VISIBLE);
           welcome.setVisibility(View.VISIBLE);
           logout.setVisibility(View.GONE);
       }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));

            }
        });

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.button1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, GridActivity.class);
                //Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left1,R.anim.left2).toBundle();
                //startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);

            }
        });

        ImageButton imageButton2 = (ImageButton) findViewById(R.id.button2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SearchActivity.class);
                //Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.left1,R.anim.left2).toBundle();
                //startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(intent, 0);

            }
        });
        TextView License = (TextView) findViewById(R.id.license);
        License.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLicensesAlertDialog();
            }
        });


    }
    private void displayLicensesAlertDialog() {
        WebView view = (WebView) LayoutInflater.from(this).inflate(R.layout.dialog_licenses, null);
        view.loadUrl("file:///android_asset/open_source_licenses.html");
        AlertDialog mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(getString(R.string.action_licenses))
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                });
    }

 }