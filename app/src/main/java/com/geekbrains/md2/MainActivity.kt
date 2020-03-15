package com.geekbrains.md2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Press FAB", Snackbar.LENGTH_LONG).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                Log.d("Log", "Press back")
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadingImage(index: Int) {
        GlobalScope.launch {
            runOnUiThread{
                prog_bar_test.visibility = ProgressBar.VISIBLE
            }
            delay(2000)
            runOnUiThread{
                when(index){
                    R.id.nav_fru -> main_image.setImageResource(R.drawable.f2)
                    R.id.nav_veg -> main_image.setImageResource(R.drawable.o1)
                    R.id.nav_nat -> main_image.setImageResource(R.drawable.p1)
                }
                prog_bar_test.visibility = ProgressBar.INVISIBLE
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        loadingImage(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}