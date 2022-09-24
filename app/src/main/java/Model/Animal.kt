package Model

import android.os.Parcel
import android.os.Parcelable

open class Animal (
    var nama:String?,
    var usia:String?,


    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    var imageUri: String = ""

    open fun suara() : String{
        return suara()
    }
    open fun makan(sapiKambing: String): String{
        return "Kamu memberi makan hewan dengan rerumputan"
    }
    open fun makan(Ayam: Int): String{
        return "Kamu memberi makan hewan dengan biji -  bijian"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(usia)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}