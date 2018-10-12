package com.example.alexey.maxi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.maxi.R;
import com.example.alexey.maxi.ui.fragments.rubrics.RubricsFragment;

public class MainActivity extends AppCompatActivity {

    public static final String RUBRICS_TAG = "Rubrics fragment";
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .add(R.id.frame_container, new RubricsFragment(), RUBRICS_TAG)
                .commit();
    }
}
