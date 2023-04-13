package com.example.lightsout;

/***********************************************
 Author: <John E Youte>
 Date: < Nov 6 2022>
 Purpose: <To be able to multiple screen app using fragment, ArgSafe, and Binding>
 What Learned: <I have learned to make a multiple screen app, and using good technic>
 Sources of Help: <Java Doc, class documentation, stackOverflow>
 Time Spent: <about 7 Hours>
 ***********************************************/

/*
Mobile App Development I -- COMP.4630 Honor Statement
The practice of good ethical behavior is essential for
maintaining good order in the classroom, providing an enriching
learning experience for students, and training as a practicing
computing professional upon graduation. This practice is
manifested in the University's Academic Integrity policy.
Students are expected to strictly avoid academic dishonesty and
adhere to the Academic Integrity policy as outlined in the course
catalog. Violations will be dealt with as outlined therein. All
programming assignments in this class are to be done by the
student alone unless otherwise specified. No outside help is
permitted except the instructor and approved tutors.
I certify that the work submitted with this assignment is mine
and was generated in a manner consistent with this document, the
course academic policy on the course website on Blackboard, and
the UMass Lowell academic code.
Date: Nov 6
Name: John E Youte
*/

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lightsout.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //private MaterialToolbar toolbar;
        com.example.lightsout.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        //setContentView(R.layout.activity_main);
        setContentView(view);
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        //this is for the bottom Nav
        BottomNavigationView bottomNavigationView = binding.bottomNav;
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


//the above code gets a reference to the navigation controller from the navigation host
        AppBarConfiguration.Builder builder = new AppBarConfiguration.Builder(navController.getGraph());
        AppBarConfiguration appBarConfiguration = builder.build();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}