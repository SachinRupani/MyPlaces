package com.jodhpurtechies.myplaceslib.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jodhpurtechies.myplaceslib.model.PlaceModel
import com.jodhpurtechies.myplaceslib.model.PlaceResult
import com.jodhpurtechies.myplaceslib.retrofit.ApiClient
import com.jodhpurtechies.myplaceslib.utils.MyPlaces
import org.json.JSONObject
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
                    liveDataPlaceResult.value= PlaceResult(status = "ZERO_RESULTS")
                }

                override fun onResponse(call: Call<PlaceResult>, response: Response<PlaceResult>) {
                    val placeResult=response.body()
                    //Log.v("$tag-Response",""+placeResult)
                    liveDataPlaceResult.value=placeResult
                }

            })
        return liveDataPlaceResult
    }

    fun getPlaceDetails(strPlaceId:String=""): LiveData<PlaceModel>{
        val liveDataPlaceModel=MutableLiveData<PlaceModel>()
        ApiClient.getApiClient()
            .getApi()
            .getPlaceDetails(key = MyPlaces.API_KEY,placeId = strPlaceId)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("$tag-call",""+call)
                    Log.e("$tag-err",""+t.message)
                    liveDataPlaceModel.value =PlaceModel()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    try {
                        val strResponse = response.body()
                        //Log.v("$tag-Response", "" + strResponse)
                        val json=JSONObject(strResponse)
                        val jsonResult=json.getJSONObject("result")
                        jsonResult.apply {
                            val placeModel=PlaceModel(
                                placeId = optString("place_id",""),
                                placeName = optString("name",""),
                                placeAddress = optString("formatted_address",""),
                                placeRating = optDouble("rating",0.0),
                                placeWebsite = optString("website",""),
                                placePhoneNo = optString("formatted_phone_number",""),
                                placeLat = optJSONObject("geometry").optJSONObject("location").optDouble("lat"),
                                placeLng = optJSONObject("geometry").optJSONObject("location").optDouble("lng")
                            )
                            liveDataPlaceModel.value=placeModel
                        }

                    }catch (e:Exception){
                        Log.d("$tag-Exc", "Error: $e")
                    }
                }

            })
        return liveDataPlaceModel
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