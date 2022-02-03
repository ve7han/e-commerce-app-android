package com.example.a3e_commerce

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.a3e_commerce.database.AppDatabase
import com.example.a3e_commerce.database.ProductDatabase
import com.example.a3e_commerce.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment: Fragment() {
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        doAsync {
            val json = URL("https://api.jsonbin.io/b/61fc560af77b236211eaa191").readText()
            uiThread {
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()
                root.recycler_view.apply {
                    layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                    adapter = ProductAdapter(products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }

    @SuppressLint("UseRequireInsteadOfGet")
        val categories = listOf("Hoodies","Jeans","Shoes","Socks","Shirts","Socks","Hats")
        root.categoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
            adapter = CategoriesAdapter(categories)
        }


        return root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener {
            doAsync {

                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()
                val productsDatabase = db.productDao().searchFor("%${searchTerm.text}%")
                val products = productsDatabase.map{
                    Product(
                        it.title,
                        "https://image1.superdry.com/static/images/optimised/zoom/upload9223368955665535834.jpg",
                        it.price,
                        true
                    )
                }

                uiThread {
                    recycler_view.apply {
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = ProductAdapter(products)
                    }
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}
