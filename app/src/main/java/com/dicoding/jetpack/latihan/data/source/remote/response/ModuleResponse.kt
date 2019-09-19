package com.dicoding.jetpack.latihan.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable

data class ModuleResponse(
        val moduleId: String? = null,
        val courseId: String? = null,
        val title: String? = null,
        val position: Int? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(moduleId)
        parcel.writeString(courseId)
        parcel.writeString(title)
        position?.let { parcel.writeInt(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModuleResponse> {
        override fun createFromParcel(parcel: Parcel): ModuleResponse {
            return ModuleResponse(parcel)
        }

        override fun newArray(size: Int): Array<ModuleResponse?> {
            return arrayOfNulls(size)
        }
    }
}