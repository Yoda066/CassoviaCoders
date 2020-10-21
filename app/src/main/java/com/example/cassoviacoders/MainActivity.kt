package com.example.cassoviacoders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cassoviacoders.db.WeatherDatabase
import com.example.cassoviacoders.ui.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.content_frame)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //na pracu s db
        WeatherDatabase.context = (applicationContext)

        setContentView(R.layout.activity_main)

        val activityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //prepinac fragmentov
        activityViewModel.actualFragmentId.observeForever {
            it?.let {  navController.navigate(it) }
        }
    }
}