package com.example.navigation

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mDrawerToggle : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: NavigationView = findViewById(R.id.nav_view)

        mDrawerToggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        mDrawerToggle!!.syncState()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val inflater = menuInflater
        inflater.inflate(R.menu.main,menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity,"Looking for $query",Toast.LENGTH_LONG).show()
                return true
            }
        })

        return true
    }

    override fun onNavigationItemSelected(item:MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_first -> {
                loadFirst(frag1 = FirstFragment())
            }
            R.id.nav_second ->{
                loadSecond(frag2 = SecondFragment())
            }
            R.id.nav_share ->{

            }
            R.id.nav_send ->{

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun loadFirst(frag1: FirstFragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.framelayout,frag1)
        fm.commit()
    }
    private fun loadSecond(frag2: SecondFragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.framelayout,frag2)
        fm.commit()
    }

}
