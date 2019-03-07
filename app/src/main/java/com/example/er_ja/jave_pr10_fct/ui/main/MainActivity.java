package com.example.er_ja.jave_pr10_fct.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavController navController;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        toolbar = ActivityCompat.requireViewById(this, R.id.toolbar);
        drawerLayout = ActivityCompat.requireViewById(this, R.id.drawerLayout);
        setSupportActionBar(toolbar);
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.empresasFragment, R.id.alumnosFragment, R.id.visitasFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        getSupportActionBar().setTitle(navController.getCurrentDestination().getLabel());
        setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        NavigationView navigationView = ActivityCompat.requireViewById(this, R.id.navigationView);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
        getSupportActionBar().setTitle(navController.getCurrentDestination().getLabel());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.alumnosFragment:
                navController.navigate(R.id.alumnosFragment);
                break;
            case R.id.empresasFragment:
                navController.navigate(R.id.empresasFragment);
                break;
            case R.id.visitasFragment:
                navController.navigate(R.id.visitasFragment);
                break;
                default:
                    Toast.makeText(this, "Error de navegacion", Toast.LENGTH_SHORT).show();
                    break;
        }
        getSupportActionBar().setTitle(navController.getCurrentDestination().getLabel());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}
