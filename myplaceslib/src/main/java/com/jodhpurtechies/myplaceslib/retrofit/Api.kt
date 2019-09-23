

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

package com.jodhpurtechies.myplaceslib.retrofit

import com.jodhpurtechies.myplaceslib.model.PlaceResult
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

    @GET(value = "place/details/json?")
    fun getPlaceDetails(@Query("key") key:String,
                         @Query("place_id") placeId:String,
                         @Query("sessiontoken") sessionToken:String= uniqueId
    ):Call<String>

}