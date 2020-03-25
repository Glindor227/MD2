package com.geekbrains.md2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.md2.fragments.FragmentAnimal
import com.geekbrains.md2.fragments.FragmentFlower
import com.geekbrains.md2.fragments.FragmentTree
import com.geekbrains.md2.fragments.FragmentsAdaptor
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_tab.*


class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        fab_tab.setOnClickListener { view ->
            Snackbar.make(view, "Press FAB in Tab activity", Snackbar.LENGTH_LONG).show()
        }

        setSupportActionBar(toolbar_tab)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val fragmentAdapter = FragmentsAdaptor(supportFragmentManager)

        fragmentAdapter.addFragment(FragmentFlower(),"Цветы")
        fragmentAdapter.addFragment(FragmentAnimal(),"Животные")
        fragmentAdapter.addFragment(FragmentTree(),"Деревья")

        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)
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
