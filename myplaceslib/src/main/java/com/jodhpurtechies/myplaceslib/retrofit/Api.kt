package com.jodhpurtechies.myplaceslib.retrofit

import com.jodhpurtechies.myplaceslib.model.PlaceResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface Api{
    companion object{
        val BASE_URL="https://maps.googleapis.com/maps/api/"
        val uniqueId= UUID.randomUUID().toString()
    }

    @GET("place/autocomplete/json?")
    fun getSearchResults(@Query("key") key:String,
                         @Query("input") input:String,
                         @Query("sessiontoken") sessionToken:String= uniqueId,
                         @Query("components") component:String="",
                         @Query("radius") radius:String="",
                         @Query("location") nearby:String=""
    ):Call<PlaceResult>
}