package com.example.a3e_commerce

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.a3e_commerce.database.AppDatabase
import com.example.a3e_commerce.database.ProductDatabase
import com.example.a3e_commerce.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {

            val db = Room.databaseBuilder(
             applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductDatabase(null, "Sriwil ", 20.00))
            val products = db.productDao().getAll()
            uiThread {
                d("yasalam", "products size? ${products.size} ${products[0].title}")
            }
        }


            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment())
                .commit()

            navigationView.setNavigationItemSelectedListener {
                it.isChecked = true
                drawerLayout.closeDrawers()
                when (it.itemId) {
                    R.id.action_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, MainFragment()).commit()
                    }

                    R.id.action_jeans -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, JeansFragment()).commit()

                    }
                    R.id.action_hoodie -> d("yasalam", "Buy Hoodie")
                    R.id.action_shoes -> d("yasalam", "Buy shoes")
                    R.id.action_admin -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, AdminFragment()).commit()
                    }
                }
                true
            }
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
            }


        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
            // return super.onOptionsItemSelected(item)
        }
    }
