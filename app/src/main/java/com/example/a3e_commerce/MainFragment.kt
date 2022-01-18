package com.example.a3e_commerce

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3e_commerce.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        doAsync {
                val json = URL("https://api.jsonbin.io/b/61e6ff99dbe5d13083280bc7").readText()
            uiThread {
                val products = Gson().fromJson(json,Array<Product>::class.java).toList()
                root.recycler_view.apply {
                    layoutManager= GridLayoutManager(activity,2)
                    adapter= ProductAdapter(products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }
        val categories = listOf("Hoodies","Jeans","Shoes","Socks","Big Mick","Sandawitchi Jambo","Btata 40 X2")
        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
            adapter = CategoriesAdapter(categories)
        }


        return root
    }
}