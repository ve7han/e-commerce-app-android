package com.example.a3e_commerce

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.GridLayoutManager
import com.example.a3e_commerce.model.Product
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val products = arrayListOf<Product>()
            for ( i in 0..10){
                products.add(Product("hoodie #$i","https://box.black/wp-content/uploads/2019/05/hoodie-with-zipper.jpg",39.99))
            }

            recycler_view.apply {
                layoutManager= GridLayoutManager(this@MainActivity,2)
                adapter= ProductAdapter(products)
            }
        }
    }