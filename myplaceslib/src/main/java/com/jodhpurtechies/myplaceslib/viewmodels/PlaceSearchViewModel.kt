package com.jodhpurtechies.myplaceslib.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jodhpurtechies.myplaceslib.data.PlaceSearchRepository
import com.jodhpurtechies.myplaceslib.model.PlaceModel
import com.jodhpurtechies.myplaceslib.model.PlaceResult
import com.jodhpurtechies.myplaceslib.utils.MyPlaces


class PlaceSearchViewModel : ViewModel() {

    private val repository = PlaceSearchRepository.getInstance(MyPlaces.API_KEY)

    fun getResults(strInput: String,strCountry:String="",strNearbyLocation:String=""): LiveData<PlaceResult> =
        repository.getPlaceSearchResults(strInput,strCountry,strNearbyLocation)

    fun getPlaceDetails(strPlaceId:String):LiveData<PlaceModel> =
        repository.getPlaceDetails(strPlaceId)

}