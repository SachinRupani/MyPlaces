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