package com.example.er_ja.jave_pr10_fct.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.er_ja.jave_pr10_fct.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private NavController navController;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    AppBarConfiguration appBarConfiguration;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.main_navigation);
        int startDestionationResId = R.id.proximasFragment;
        switch (settings.getString(getResources().getString(R.string.lstKey), "proximas")){
            case "proximas":
                startDestionationResId = R.id.proximasFragment;
                break;
            case "visitas":
                startDestionationResId = R.id.visitasFragment;
                break;
            case "alumnos":
                startDestionationResId = R.id.alumnosFragment;
                break;
            case "empresas":
                startDestionationResId = R.id.empresasFragment;
                break;
        }
        navGraph.setStartDestination(startDestionationResId);
        navController.setGraph(navGraph);
        toolbar = ActivityCompat.requireViewById(this, R.id.toolbar);
        drawerLayout = ActivityCompat.requireViewById(this, R.id.drawerLayout);
        setSupportActionBar(toolbar);
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.empresasFragment, R.id.alumnosFragment, R.id.visitasFragment, R.id.proximasFragment)
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        getSupportActionBar().setTitle(navController.getCurrentDestination().getLabel());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Se infla el menú a partir del XML.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Dependiendo del item pulsado se realiza la acción deseada.
        switch (item.getItemId()) {
            case R.id.mnuPref:
                navigateToPreferences();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void navigateToPreferences() {
        navController.navigate(R.id.preferencias);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
    @Override
    protected void onResume(){
        super.onResume();
        settings.registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    protected void onPause() {
        settings.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
