package com.geekbrains.md2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.geekbrains.md2.data.MainListItem
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var color: SharedPreferences
    companion object {
        const val APP_PREFERENCES = "MD2_Pref"
        const val APP_PREFERENCES_COUNTER = "theme_numer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSharedProp()
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
        loadingImage(R.id.nav_fru)
    }

    private fun initSharedProp() {
        color = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        if (color.contains(APP_PREFERENCES_COUNTER)) {
            when (color.getInt(APP_PREFERENCES_COUNTER, 0)) {
                1 -> setTheme(R.style.AppTheme)
                3 -> setTheme(R.style.AppTheme3)
                2 -> setTheme(R.style.AppTheme2)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.color,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                Log.d("Log", "Press back")
                onBackPressed()
            }
            else -> setColor(item.itemId)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setColor(itemId: Int) {
        val editor = color.edit()
        when(itemId) {
            R.id.base_color -> editor.putInt(APP_PREFERENCES_COUNTER, 1)
            R.id.green_color -> editor.putInt(APP_PREFERENCES_COUNTER, 3)
            R.id.purple_color -> editor.putInt(APP_PREFERENCES_COUNTER, 2)
        }
        editor.apply()
        recreate()
    }

    private fun loadingImage(index: Int) {
        GlobalScope.launch {
            runOnUiThread{
                prog_bar_test.visibility = ProgressBar.VISIBLE
            }
            delay(2000)
            runOnUiThread{

                when(index){
                    R.id.nav_fru -> {main_image.setImageResource(R.drawable.f2)
                        toolbar_layout.title = "Фрукты"
                        toolbar.title = "Фрукты"
                        val items = listOf(
                            MainListItem("Яблоко","Вкустно", main_image),
                            MainListItem("Груша","Так себе", main_image),
                            MainListItem("Арбуз","Вообщето ягода", main_image),
                            MainListItem("Виноград","Вино лучге", main_image),
                            MainListItem("Апельсин","Большой", main_image),
                            MainListItem("Мандарин","С новым годом!", main_image),
                            MainListItem("ФруктX","Ну и фрукт...", main_image),
                            MainListItem("ФруктX","Ну и фрукт...", main_image),
                            MainListItem("ФруктX","Ну и фрукт...", main_image),
                            MainListItem("ФруктX", "Ну и фрукт...",main_image),
                            MainListItem("ФруктX","Ну и фрукт...", main_image),
                            MainListItem("ФруктX", "Ну и фрукт...",main_image),
                            MainListItem("ФруктX", "Ну и фрукт...",main_image),
                            MainListItem("ФруктX", "Ну и фрукт...",main_image),
                            MainListItem("ФруктX", "Ну и фрукт...",main_image)
                        )
                        myRecycler.adapter = MainListAdapter(items, object : MainListAdapter.Callback {
                            override fun onItemClicked(item: MainListItem) {
                                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
                            }
                        })
                    }
                    R.id.nav_veg -> {main_image.setImageResource(R.drawable.o1)
                        toolbar_layout.title = "Овощи"
                        val items = listOf(
                            MainListItem("Огурец", "Полезно",main_image),
                            MainListItem("Картофан","Спасибо Колумбу", main_image)
                        )
                        myRecycler.adapter = MainListAdapter(items, object : MainListAdapter.Callback {
                            override fun onItemClicked(item: MainListItem) {
                                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
                            }
                        })
                    }
/*
                    R.id.nav_nat -> {

                        main_image.setImageResource(R.drawable.p1)
                        toolbar_layout.title = "Природа"
                        val items = listOf(
                            MainListItem("Береза","бревно", main_image),
                            MainListItem("Дуб", "бревно",main_image),
                            MainListItem("Ясень","бревно", main_image),
                            MainListItem("Дерево", "бревно",main_image)
                        )
                        myRecycler.adapter = MainListAdapter(items, object : MainListAdapter.Callback {
                            override fun onItemClicked(item: MainListItem) {
                                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
                            }
                        })
                    }
                    */
                }
                prog_bar_test.visibility = ProgressBar.INVISIBLE
            }
        }

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.nav_nat)
            startActivity(Intent(this, TabActivity::class.java))
        else {
            loadingImage(item.itemId)
        }
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