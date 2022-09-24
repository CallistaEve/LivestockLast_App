package Model

import android.os.Parcel

class Ayam(nama:String, usia: String) : Animal(nama, usia) {
    override fun suara(): String {
//        super.suara()
        var suaraAyam = "Bock bock bock"
//        var toast = Toast.makeText(this, "Bock bock bock...", Toast.LENGTH_SHORT).show()
//        toast.show()
        return suaraAyam
    }
}