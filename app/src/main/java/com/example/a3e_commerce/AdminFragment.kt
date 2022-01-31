package com.example.a3e_commerce

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.a3e_commerce.database.AppDatabase
import com.example.a3e_commerce.database.ProductDatabase
import kotlinx.android.synthetic.main.admin_fragment.*
import kotlinx.android.synthetic.main.fragment_jeans.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.admin_fragment, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                submitButton.setOnClickListener {
                    val title = Producttitle.text
            d("daniel", "button pressed :) with text of $title")

            doAsync {

                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "database-name"
                ).build()

                db.productDao().insertAll(ProductDatabase(null, title.toString() , 30.00))

                uiThread {
                    d("daniel", "redirecting to home screen")


                }
            }
        }
    }
}