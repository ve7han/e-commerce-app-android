package com.example.a3e_commerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a3e_commerce.model.Product
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val products = arrayListOf<Product>()
        for ( i in 0..10){
            products.add(Product("hoodie #$i","https://box.black/wp-content/uploads/2019/05/hoodie-with-zipper.jpg",39.99))
        }

        root.recycler_view.apply {
            layoutManager= GridLayoutManager(activity,2)
            adapter= ProductAdapter(products)
        }
        return root
    }
}