package edu.scranton.ctleweb.droidtrack;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import edu.scranton.ctleweb.droidtrack.projtrack.ProjectContent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        AddClientFragment.OnFragmentInteractionListener,
        AddProjectFragment.OnFragmentInteractionListener,
        AllProjectsFragment.OnListFragmentInteractionListener,
        MyProjectsFragment.OnListFragmentInteractionListener {

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.token = getIntent().getStringExtra("TOKEN");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack("Home").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Log.d("MainActivity", "Home");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment fragment = new HomeFragment();
            Bundle b = new Bundle();
            b.putString("token", this.token);
            fragment.setArguments(b);
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("Home").commit();
        } else if (id == R.id.nav_my_projects) {
            Log.d("MainActivity", "My Projects");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MyProjectsFragment fragment = new MyProjectsFragment();
            Bundle b = new Bundle();
            b.putString("token", this.token);
            fragment.setArguments(b);
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("MyProjects").commit();
        } else if (id == R.id.nav_all_projects) {
            Log.d("MainActivity", "All Projects");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AllProjectsFragment fragment = new AllProjectsFragment();
            Bundle b = new Bundle();
            b.putString("token", this.token);
            fragment.setArguments(b);
            fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack("AllProjects").commit();
        } else if (id == R.id.nav_add_project) {
            Log.d("MainActivity", "Add Project");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AddProjectFragment fragment = new AddProjectFragment();
            Bundle b = new Bundle();
            b.putString("token", this.token);
            fragment.setArguments(b);
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddProject").commit();
        } else if (id == R.id.nav_add_client) {
            Log.d("MainActivity", "Add Client");
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            AddClientFragment fragment = new AddClientFragment();
            Bundle b = new Bundle();
            b.putString("token", this.token);
            fragment.setArguments(b);
            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddClient").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(ProjectContent.ProjectItem item) {

    }
}
