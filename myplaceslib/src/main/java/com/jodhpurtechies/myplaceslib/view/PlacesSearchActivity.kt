package com.jodhpurtechies.myplaceslib.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jodhpurtechies.myplaceslib.R
import com.jodhpurtechies.myplaceslib.model.PredictionModel
import com.jodhpurtechies.myplaceslib.utils.MyPlaces
import com.jodhpurtechies.myplaceslib.utils.MyPlaces.TAG_ERR
import com.jodhpurtechies.myplaceslib.viewmodels.PlaceSearchViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_places_search.*
import kotlinx.android.synthetic.main.content_layout_no_record_found.*
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SetTextI18n")
class PlacesSearchActivity : AppCompatActivity() {


    private lateinit var viewModel: PlaceSearchViewModel

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_search)
        init()
        initConfig()
        handleClicks()
        handleTextChanges()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun initConfig() {
        if (MyPlaces.typeface != null) {
            etSearch.typeface = MyPlaces.typeface
            txtNoRecFound.typeface = MyPlaces.typeface
        }
        if (MyPlaces.iconSearch != null)
            imgSearch.setImageResource(MyPlaces.iconSearch!!)
        if (MyPlaces.iconClear != null)
            imgClearSearch.setImageResource(MyPlaces.iconClear!!)
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(PlaceSearchViewModel::class.java)
        rvSearchResults.layoutManager = LinearLayoutManager(this)
    }

    private fun handleClicks() {
        compositeDisposable.add(
            RxView.clicks(imgClearSearch)
                .throttleFirst(800, TimeUnit.MILLISECONDS)
                .subscribe {
                    etSearch.setText("")
                    imgClearSearch.isVisible = false
                })
    }

    private fun handleTextChanges() {
        compositeDisposable.add(RxTextView.textChangeEvents(etSearch)
            .skip(1)
            .debounce(700, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { err ->
                Log.d(TAG_ERR, "" + err)
            }
            .subscribe {
                val strInput = it.text().toString().trim()
                if (strInput.trim().isNotEmpty()) {
                    beginSearch(strInput)
                    setColorSearch(R.color.colorMainFont)
                    imgClearSearch.isVisible = true
                } else {
                    imgClearSearch.isVisible = false
                    rvSearchResults.adapter = null
                    setColorSearch(R.color.colorHint)
                    linNoRecordFound.isVisible = false
                }
            })
    }


    private fun beginSearch(strInput: String) {
        progressBar.isVisible = true
        viewModel.getResults(
            strInput = strInput,
            strCountry = if (MyPlaces.country != null) MyPlaces.country!!.trim().toLowerCase(Locale.ENGLISH) else "",
            strNearbyLocation = if (MyPlaces.nearbyLocation != null) MyPlaces.nearbyLocation!!.trim() else ""
        ).observe(this, Observer { placeResult ->
            if (placeResult != null) {
                if (placeResult.isOK()) {
                    val arrayPredictions = placeResult.predictions as ArrayList<PredictionModel>?
                    if (arrayPredictions != null && arrayPredictions.isNotEmpty()) {
                        linNoRecordFound.isVisible = false
                        val placeAdapter =
                            PlaceSearchAdapter(this, arrayPredictions, this::onPlaceClicked)
                        with(rvSearchResults) {
                            adapter = placeAdapter

                            if (MyPlaces.animate)
                                scheduleLayoutAnimation()
                        }
                    } else {
                        showNoRecordFound(strInput)
                    }
                } else {
                    if (placeResult.isRequestDenied())
                        showNoRecordFound(isError = true)
                }
            }

            progressBar.isVisible = false

        })
    }

    private fun onPlaceClicked(pos: Int, arrayPredictions: ArrayList<PredictionModel>) {
        val intent = Intent().also {
            it.putExtra(MyPlaces.PREDICTION_RESULT, arrayPredictions[pos])
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setColorSearch(colorRes: Int) {
        imgSearch.setColorFilter(ContextCompat.getColor(applicationContext, colorRes))
    }

    private fun showNoRecordFound(strInput: String? = null, isError: Boolean? = null) {
        rvSearchResults.adapter = null
        linNoRecordFound.isVisible = true

        if (strInput != null) {
            val suffix = " for \"$strInput\""
            txtNoRecFound.text = getString(R.string.no_search_found) + suffix
        } else if (isError == true) {
            txtNoRecFound.text = getString(R.string.enter_valid_api_key)
        }
    }
}
