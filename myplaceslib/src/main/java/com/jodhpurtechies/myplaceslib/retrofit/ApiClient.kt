

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

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class ApiClient{
    private val mRetrofit:Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(Api.BASE_URL)
        .build()

    private val api:Api

    init {
        api=mRetrofit.create(Api::class.java)
    }

    fun getApi():Api = api

    companion object{
        @Volatile private var instance : ApiClient?=null
        fun getApiClient():ApiClient = instance ?: ApiClient()
    }
}