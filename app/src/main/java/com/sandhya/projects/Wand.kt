package com.sandhya.projects

import android.os.Parcel
import android.os.Parcelable

data class Wand(
    val wood: String, val core: String, val length: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "", parcel.readString() ?: "", parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(wood)
        parcel.writeString(core)
        parcel.writeFloat(length)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wand> {
        override fun createFromParcel(parcel: Parcel): Wand {
            return Wand(parcel)
        }

        override fun newArray(size: Int): Array<Wand?> {
            return arrayOfNulls(size)
        }
    }
}