package com.geekbrains.md2

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        loadingImage(R.id.nav_fru)
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
                }
                prog_bar_test.visibility = ProgressBar.INVISIBLE
            }
        }

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, TabActivity::class.java))
//        loadingImage(item.itemId)
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