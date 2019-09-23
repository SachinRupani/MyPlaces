package com.jodhpurtechies.sampleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jodhpurtechies.myplaceslib.model.PredictionModel
import com.jodhpurtechies.myplaceslib.utils.MyPlaces

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyPlaces.with("AIzaXXXXXXXXXXXXXXXXXXXXXX")
            .startSearch(this)

        MyPlaces.with("AIzaXXXXXXXXXXXXXXXXXXXXXX") //Your Google API key
            //.setTextTypeFace(Typeface.createFromAsset(assets, "Ubuntu-Medium.ttf")) //
            //.setLocationIcon(R.drawable.ic_location) //For changing the default location icon
            //.setNearbyLocation(lat = 28.7041, lng = 77.1025) //For getting results according to a specific lat lng
            //.setCountry("in") //For country specific results, refer to country codes
            //.setBackgroundListItem(android.R.color.transparent) //For changing the default list item background resource
            .animateList(false) //true by default
            .startSearch(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK && requestCode==MyPlaces.REQUEST_CODE_PLACE_SEARCH){
            val predictionResult=data?.extras?.getSerializable(MyPlaces.PREDICTION_RESULT) as PredictionModel?
            Log.d("Prediction",""+predictionResult?.placeId+" - "+predictionResult?.placeAddress)
        }
    }
}
