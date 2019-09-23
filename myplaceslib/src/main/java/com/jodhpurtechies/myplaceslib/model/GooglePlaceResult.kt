

/*
 * Created by Sachin Rupani on 23/9/19 11:47 PM
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

package com.jodhpurtechies.myplaceslib.model

import com.squareup.moshi.Json
import java.io.Serializable


data class PlaceResult(
    var predictions: List<PredictionModel>? = null,
    var status: String? = null //OK
) : Serializable {
    fun isOK(): Boolean = status?.trim()?.equals("OK", true) == true
    fun isRequestDenied(): Boolean = status?.trim()?.equals("REQUEST_DENIED", true) == true
    fun isZeroResult(): Boolean = status?.trim()?.equals("ZERO_RESULTS", true) == true
}


data class PredictionModel(
    @field:Json(name = "description") var placeAddress: String? = null,
    @field:Json(name = "place_id") var placeId: String? = null

) : Serializable

data class PlaceModel(
    var placeId: String? = null,
    var placeName: String? = null,
    var placeAddress: String? = null,
    var placePhoneNo: String? = null,
    var placeWebsite: String? = null,
    var placeRating: Double? = null,
    var placeLat: Double? = 0.0,
    var placeLng: Double? = 0.0
) : Serializable