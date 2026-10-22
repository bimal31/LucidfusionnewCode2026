package com.org.lucidfusion.dtos

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class ReportDTO(
    @JvmField var fn: String = "",
    @JvmField var dataObject: JSONObject? = null,
    @JvmField var isUploaded: String = "0"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        fn = parcel.readString() ?: "",
        dataObject = parcel.readString()?.let {
            try { JSONObject(it) } catch (e: Exception) { null }
        },
        isUploaded = parcel.readString() ?: "0"
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fn)
        parcel.writeString(dataObject?.toString())
        parcel.writeString(isUploaded)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ReportDTO> {
        override fun createFromParcel(parcel: Parcel): ReportDTO = ReportDTO(parcel)
        override fun newArray(size: Int): Array<ReportDTO?> = arrayOfNulls(size)
    }
}
