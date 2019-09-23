

/*
 * Created by Sachin Rupani on 23/9/19 11:48 PM
 * Copyright (c) 2019
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jodhpurtechies.myplaceslib.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.widget.Toast
import com.jodhpurtechies.myplaceslib.view.PlacesSearchActivity


object MyPlaces {

    const val TAG_ERR = "ErrorMyPlaces"
    private const val API_ERR_MSG = "Please enter a valid API key"
    const val API_ERR_MSG_DETAILS = "Oops! Invalid place_id found"
    const val REQUEST_CODE_PLACE_SEARCH=2909
    const val MY_PLACE_RESULT="PlaceResult"

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
