package com.example.cassoviacoders

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation


class FullscreenActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.content_frame)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //prepinac fragmentov
        activityViewModel.actualLocation.observeForever {
            //ak sa zmenila hondota na null, znamena to ze nemam nacitanu ziadnu lokaciu a mam spusit nacitavaci fragment
            if (it == null) {
                navController.navigate(R.id.location_choose)
            } else {
                //inac mam nacitanu lokaciu a prepnem na detailovy fragment
                navController.navigate(R.id.location_detail)
            }
        }
    }
}