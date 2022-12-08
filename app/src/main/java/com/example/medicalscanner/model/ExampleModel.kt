package com.example.medicalscanner.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExampleModel (
    @SerializedName("data")
    var data : Map<String, Data>? = null
)

class Data(
    @SerializedName("Data")
    @Expose
    var data: Map<String, Data>? = null,

    @SerializedName("country")
    @Expose
    var country: String? = null,

    @SerializedName("region")
    @Expose
    var region: String? = null
)