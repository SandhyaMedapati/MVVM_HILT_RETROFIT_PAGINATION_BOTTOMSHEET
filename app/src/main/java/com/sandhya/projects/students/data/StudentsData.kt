package com.sandhya.projects.students.data

import android.os.Parcel
import android.os.Parcelable
import com.sandhya.projects.Wand

data class StudentsData(
    val id: String,
    val name: String,
    val alternate_names: List<String>,
    val species: String,
    val gender: String,
    val house: String,
    val dateOfBirth: String,
    val yearOfBirth: Int,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: Wand,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alternate_actors: List<String>,
    val alive: Boolean,
    val image: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(Wand::class.java.classLoader) ?: Wand("", "", 0.0f),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeStringList(alternate_names)
        parcel.writeString(species)
        parcel.writeString(gender)
        parcel.writeString(house)
        parcel.writeString(dateOfBirth)
        parcel.writeInt(yearOfBirth)
        parcel.writeByte(if (wizard) 1 else 0)
        parcel.writeString(ancestry)
        parcel.writeString(eyeColour)
        parcel.writeString(hairColour)
        parcel.writeParcelable(wand, flags)
        parcel.writeString(patronus)
        parcel.writeByte(if (hogwartsStudent) 1 else 0)
        parcel.writeByte(if (hogwartsStaff) 1 else 0)
        parcel.writeString(actor)
        parcel.writeStringList(alternate_actors)
        parcel.writeByte(if (alive) 1 else 0)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentsData> {
        override fun createFromParcel(parcel: Parcel): StudentsData {
            return StudentsData(parcel)
        }

        override fun newArray(size: Int): Array<StudentsData?> {
            return arrayOfNulls(size)
        }
    }
}