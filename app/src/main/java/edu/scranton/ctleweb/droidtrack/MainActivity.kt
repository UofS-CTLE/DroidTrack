package edu.scranton.ctleweb.droidtrack

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, AddClientFragment.OnFragmentInteractionListener, AddProjectFragment.OnFragmentInteractionListener, AllProjectsView.OnFragmentInteractionListener, MyProjectsView.OnFragmentInteractionListener, AllProjectsFragment.OnListFragmentInteractionListener, MyProjectsFragment.OnListFragmentInteractionListener {

    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        this.token = intent.getStringExtra("TOKEN")

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = HomeFragment()
        fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack("Home").commit()
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.nav_home -> {
                Log.d("MainActivity", "Home")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = HomeFragment()
                val b = Bundle()
                b.putString("token", this.token)
                fragment.arguments = b
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("Home").commit()
            }
            R.id.nav_my_projects -> {
                Log.d("MainActivity", "My Projects")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = MyProjectsFragment()
                val b = Bundle()
                b.putString("token", this.token)
                fragment.arguments = b
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("MyProjects").commit()
            }
            R.id.nav_all_projects -> {
                Log.d("MainActivity", "All Projects")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = AllProjectsFragment()
                val b = Bundle()
                b.putString("token", this.token)
                fragment.arguments = b
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AllProjects").commit()
            }
            R.id.nav_add_project -> {
                Log.d("MainActivity", "Add Project")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = AddProjectFragment()
                val b = Bundle()
                b.putString("token", this.token)
                fragment.arguments = b
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddProject").commit()
            }
            R.id.nav_add_client -> {
                Log.d("MainActivity", "Add Client")
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val fragment = AddClientFragment()
                val b = Bundle()
                b.putString("token", this.token)
                fragment.arguments = b
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("AddClient").commit()
            }
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onListFragmentInteraction(item: AllProjectsContent.ProjectItem) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = AllProjectsView()
        val b = Bundle()
        b.putSerializable("item", item)
        fragment.arguments = b
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("Project").commit()
    }

    override fun onListFragmentInteraction(item: MyProjectsContent.ProjectItem) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MyProjectsView()
        val b = Bundle()
        b.putSerializable("item", item)
        fragment.arguments = b
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("Project").commit()
    }
}
