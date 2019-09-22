package com.jodhpurtechies.myplaceslib.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jodhpurtechies.myplaceslib.databinding.RowPlaceListBinding
import com.jodhpurtechies.myplaceslib.model.PredictionModel
import com.jodhpurtechies.myplaceslib.utils.MyPlaces


class PlaceSearchAdapter(
    private val act: Activity,
    private val arrayPredictions: ArrayList<PredictionModel>,
    private val onPlaceClicked:(pos:Int,arrayPredictions: ArrayList<PredictionModel>) -> Unit
) : RecyclerView.Adapter<PlaceSearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RowPlaceListBinding.inflate(LayoutInflater.from(act), parent, false))

    override fun getItemCount(): Int = arrayPredictions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayPredictions[position])
    }

    inner class ViewHolder(private val binding: RowPlaceListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            if (MyPlaces.backgroundListItem != null)
                binding.linPlace.setBackgroundResource(MyPlaces.backgroundListItem!!)
            if (MyPlaces.iconLocation != null)
                binding.imgLocation.setImageResource(MyPlaces.iconLocation!!)
            if (MyPlaces.typeface != null)
                binding.txtPlaceName.typeface = MyPlaces.typeface

            binding.linPlace.setOnClickListener {
               onPlaceClicked(adapterPosition,arrayPredictions)
            }
        }

        fun bind(predictionModel: PredictionModel) {
            binding.model = predictionModel
            binding.executePendingBindings()
        }
    }
}