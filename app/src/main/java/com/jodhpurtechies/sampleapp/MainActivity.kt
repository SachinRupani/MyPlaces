package com.jodhpurtechies.sampleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jodhpurtechies.myplaceslib.model.PlaceModel
import com.jodhpurtechies.myplaceslib.utils.MyPlaces

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AIzaSyBVXtCqKn-E7c6U8RSKQi0yfIBk_AEEb7w
        MyPlaces.with("AIzaSyBVXtCqKn-E7c6U8RSKQi0yfIBk_AEEb7w") //Your Google API key
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
        if (resultCode == Activity.RESULT_OK && requestCode == MyPlaces.REQUEST_CODE_PLACE_SEARCH) {
            val placeResult = data?.extras?.getSerializable(MyPlaces.MY_PLACE_RESULT) as PlaceModel?

            //Now with this placeResult, you can access its placeId, name, address, lat, lng etc

            //placeResult?.placeName
            //placeResult?.placeAddress

            /*Log.d("Place", "PlaceId: ${placeResult?.placeId}"
                        + "\nPlaceName: ${placeResult?.placeName}"
                        + "\nPlaceAddress: ${placeResult?.placeAddress}"
                        + "\nPlaceLatLng: ${placeResult?.placeLat},${placeResult?.placeLng}"
                        + "\nPlacePhone: ${placeResult?.placePhoneNo}"
                        + "\nPlaceWebsite: ${placeResult?.placeWebsite}"
                        + "\nPlaceRating: ${placeResult?.placeRating}"
            )*/
        }
    }
}
