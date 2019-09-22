package com.jodhpurtechies.myplaceslib.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.widget.Toast
import com.jodhpurtechies.myplaceslib.view.PlacesSearchActivity


object MyPlaces {

    const val TAG_ERR = "ErrorMyPlaces"
    private const val API_ERR_MSG = "Please call MyPlaces.initialize(\"YOUR_API_KEY\") first"
    const val REQUEST_CODE_PLACE_SEARCH=2909
    const val PREDICTION_RESULT="PredictionResult"

    //config values for customization
    var typeface: Typeface? = null

    var iconSearch: Int? = null
    var iconLocation: Int? = null
    var iconClear: Int? = null
    var backgroundListItem: Int? = null
    var country:String?=null
    var nearbyLocation:String?=null
    var animate:Boolean=true

    var API_KEY = ""

    fun with(strApiKey: String): MyPlaces {
        return this.apply { this.API_KEY = strApiKey }
    }

    fun setTextTypeFace(typeface: Typeface): MyPlaces {
        return this.apply { this.typeface = typeface }
    }

    fun setSearchIcon(iconSearchResource: Int): MyPlaces {
        return this.apply { this.iconSearch = iconSearchResource }
    }

    fun setClearIcon(iconClearResource: Int): MyPlaces {
        return this.apply { this.iconClear=iconClearResource }
    }

    fun setLocationIcon(iconLocationResource:Int):MyPlaces{
        return this.apply { this.iconLocation = iconLocationResource }
    }

    fun setBackgroundListItem(backgroundListItemResource:Int):MyPlaces{
        return this.apply { this.backgroundListItem= backgroundListItemResource }
    }

    fun setCountry(country:String):MyPlaces{
        return this.apply { this.country=country }
    }

    fun setNearbyLocation(lat:Double,lng:Double):MyPlaces{
        return this.apply { this.nearbyLocation="$lat,$lng" }
    }

    fun animateList(animateListItems:Boolean):MyPlaces{
        return this.apply { this.animate=animateListItems }
    }


    fun startSearch(act: Activity) {
        if (API_KEY.trim().isEmpty()) {
            Toast.makeText(
                act.applicationContext,
                API_ERR_MSG,
                Toast.LENGTH_SHORT
            ).show()
            Log.e(TAG_ERR, API_ERR_MSG)
            return
        }
        act.startActivityForResult(Intent(act.applicationContext, PlacesSearchActivity::class.java),REQUEST_CODE_PLACE_SEARCH)
    }

}
