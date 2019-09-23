

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