package com.udacity.shoestore.models

import android.os.Parcelable
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(var name: ObservableField<String> = ObservableField<String>(),
                var size: ObservableField<String> = ObservableField<String>(),
                var company: ObservableField<String> = ObservableField<String>(),
                var description: ObservableField<String> = ObservableField<String>(),
                val images: ObservableField<List<String>> = ObservableField<List<String>>()) : Parcelable