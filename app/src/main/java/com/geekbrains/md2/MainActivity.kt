package com.geekbrains.md2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main)

        setSupportActionBar(toolbar)
        //       val fab: FloatingActionButton = findViewById(R.id.fab)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Press FAB", Snackbar.LENGTH_LONG).show()
        }
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
}
