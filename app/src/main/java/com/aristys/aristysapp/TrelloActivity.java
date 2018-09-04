package com.aristys.aristysapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.aristys.aristysapp.ui.login_trello.AuthorizeFragment;

public class TrelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trello);

        if (savedInstanceState == null) {
            Fragment authorizeFragment = AuthorizeFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_frame_container, authorizeFragment)
                    .commit();
        }
    }
}

