package com.jodhpurtechies.myplaceslib.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jodhpurtechies.myplaceslib.model.PlaceResult
import com.jodhpurtechies.myplaceslib.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaceSearchRepository(private val strKey: String) {

    private val tag=this.javaClass.simpleName

    fun getPlaceSearchResults(strInput:String,strCountry:String="",nearbyLocation:String=""): LiveData<PlaceResult>{
        val liveDataPlaceResult=MutableLiveData<PlaceResult>()
        ApiClient.getApiClient()
            .getApi()
            .getSearchResults(strKey,strInput,
                component= if(strCountry.trim().isNotEmpty()) "country:$strCountry" else "",
                nearby = if(nearbyLocation.trim().isNotEmpty()) nearbyLocation else ""
            )
            .enqueue(object : Callback<PlaceResult> {
                override fun onFailure(call: Call<PlaceResult>, t: Throwable) {
                    Log.e("$tag-call",""+call)
                    Log.e("$tag-err",""+t.message)
                }

                override fun onResponse(call: Call<PlaceResult>, response: Response<PlaceResult>) {
                    val placeResult=response.body()
                    Log.v("$tag-Response",""+placeResult)
                    liveDataPlaceResult.value=placeResult
                }

            })
        return liveDataPlaceResult
    }

    companion object {
        @Volatile
        private var instance: PlaceSearchRepository? = null

        fun getInstance(strKey:String): PlaceSearchRepository =
            instance ?: synchronized(this) {
                instance ?: PlaceSearchRepository(strKey)
            }
    }
}