package Model

class Sapi(nama:String, usia: String) : Animal(nama, usia) {
    override fun suara():String {
//        super.suara()
        var suaraSapi = "Moo...."
//        var toast = Toast.makeText(this, "Bock bock bock...", Toast.LENGTH_SHORT).show()
//        toast.show()
        return suaraSapi
    }
}