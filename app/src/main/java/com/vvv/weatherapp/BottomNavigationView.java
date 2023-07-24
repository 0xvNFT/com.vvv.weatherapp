package com.vvv.weatherapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.vvv.weatherapp.fragment.FirstFragment;
import com.vvv.weatherapp.fragment.FourthFragment;
import com.vvv.weatherapp.fragment.SecondFragment;
import com.vvv.weatherapp.fragment.ThirdFragment;

public class BottomNavigationView extends LinearLayout {

    public BottomNavigationView(Context context) {
        super(context);
        init();
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_navigation, this, true);

        View item1 = findViewById(R.id.bottomNavItem1);
        View item2 = findViewById(R.id.bottomNavItem2);
        View item3 = findViewById(R.id.bottomNavItem3);
        View item4 = findViewById(R.id.bottomNavItem4);

        item1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new FirstFragment());
            }
        });

        item2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new SecondFragment());
            }
        });

        item3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new ThirdFragment());
            }
        });

        item4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment(new FourthFragment());
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
