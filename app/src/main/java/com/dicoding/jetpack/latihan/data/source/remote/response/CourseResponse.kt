package com.dicoding.jetpack.latihan.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable

data class CourseResponse(
        val id: String? = null,
        val title: String? = null,
        val description: String? = null,
        val date: String? = null,
        val imagePath: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(date)
        parcel.writeString(imagePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseResponse> {
        override fun createFromParcel(parcel: Parcel): CourseResponse {
            return CourseResponse(parcel)
        }

        override fun newArray(size: Int): Array<CourseResponse?> {
            return arrayOfNulls(size)
        }
    }
}