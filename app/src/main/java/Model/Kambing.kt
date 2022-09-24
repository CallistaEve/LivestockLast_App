package Model

class Kambing(nama:String, usia: String) : Animal(nama, usia) {
    override fun suara():String {
//        super.suara()
        var suaraKambing = "Bleh..."
//        var toast = Toast.makeText(this, "Bock bock bock...", Toast.LENGTH_SHORT).show()
//        toast.show()
        return suaraKambing
    }
}