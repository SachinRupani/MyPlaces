package com.jodhpurtechies.sampleapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.jodhpurtechies.myplaceslib.model.PlaceModel
import com.jodhpurtechies.myplaceslib.utils.MyPlaces
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AIzaXXXXXXXXXXXXXXXXXX
        MyPlaces.with("AIzaXXXXXXXXXXXXXX") //Your Google API key
            //.setTextTypeFace(Typeface.createFromAsset(assets, "Ubuntu-Medium.ttf")) //
            //.setLocationIcon(R.drawable.ic_location) //For changing the default location icon
            //.setNearbyLocation(lat = 28.7041, lng = 77.1025) //For getting results according to a specific lat lng
            //.setCountry("in") //For country specific results, refer to country codes
            //.setBackgroundListItem(android.R.color.transparent) //For changing the default list item background resource
            .animateList(false) //true by default
            .startSearch(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == MyPlaces.REQUEST_CODE_PLACE_SEARCH) {
            val placeResult = data?.extras?.getSerializable(MyPlaces.MY_PLACE_RESULT) as PlaceModel?

            //Now with this placeResult, you can access its placeId, name, address, lat, lng etc

            //placeResult?.placeName
            //placeResult?.placeAddress

            txtPlace.text=HtmlCompat.fromHtml("PlaceId: <b>${placeResult?.placeId}</b>" + "<br/>PlaceName: <b>${placeResult?.placeName}</b>" + "<br/>PlaceAddress: <b>${placeResult?.placeAddress}</b>" + "<br />PlaceLatLng: <b>${placeResult?.placeLat},${placeResult?.placeLng}</b>" +"<br />PlaceWebsite: <b>${placeResult?.placeWebsite}</b>",HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}
