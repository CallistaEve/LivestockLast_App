package com.example.week2_0706012110036

import Database.GlobalVar
import Model.Animal
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.week2_0706012110036.InputAnimalScreen
import com.example.week2_0706012110036.databinding.ActivityInputAnimalScreenBinding


class InputAnimalScreen : AppCompatActivity() {

    private lateinit var viewBind: ActivityInputAnimalScreenBinding
    private lateinit var animal : Animal
    var position = -1
    var image: String =""
    var jenis: String=""
    var nama: String=""
    var usia: String=""


    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}
            viewBind.addpictureImageView.setImageURI(uri)
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityInputAnimalScreenBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        GetIntent()
        Listener()
    }

    private fun GetIntent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val animal = GlobalVar.ListDataAnimal[position]
            viewBind.toolbar2.title = "Edit Hewan"
            viewBind.addButton.text = "Edit"
            viewBind.addpictureImageView.setImageURI(Uri.parse(GlobalVar.ListDataAnimal[position].imageUri))
            viewBind.usiaTextInputLayout.editText?.setText(animal.usia.toString())
            viewBind.namaTextInputLayout.editText?.setText(animal.nama)
            if(animal is Ayam){
                viewBind.ayamRadioButton.isChecked=true
            }
            if(animal is Sapi){
                viewBind.sapiRadioButton.isChecked=true
            }
            if(animal is Kambing){
                viewBind.kambingRadioButton.isChecked=true
            }
//            viewBind.radioGroup.id
//            viewBind.jenisTextInputLayout.editText?.setText(animal.jenis)
        }
    }

    private fun Listener() {
        viewBind.toolbar2.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("position", position)
            }
            startActivity(myIntent)
        }

        viewBind.addpictureImageView.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.ayam_radioButton) {
                jenis = "ayam"
            } else if (checkedId == R.id.sapi_radioButton) {
                jenis = "sapi"
            } else if (checkedId == R.id.kambing_radioButton) {
                jenis = "kambing"
            }
        }


        viewBind.addButton.setOnClickListener {
            var nama = viewBind.namaTextInputLayout.editText?.text.toString().trim()
            var usia = viewBind.usiaTextInputLayout.editText?.text.toString().trim()


            if (jenis == "sapi") {
                animal = Sapi(nama, usia)
            } else if (jenis == "ayam") {
                animal = Ayam(nama, usia)
            } else if (jenis == "kambing") {
                animal = Kambing(nama, usia)
            }
            checker()
        }
    }


    private fun checker(){
        var isCompleted:Boolean = true

        //Nama
        if(animal.nama!!.isEmpty()){
            viewBind.namaTextInputLayout.error = "Kolom Nama Harus di Isi"
            isCompleted = false
        }else{
            viewBind.namaTextInputLayout.error = ""
        }

        //Usia
        if(animal.usia!!.isEmpty()){
            viewBind.usiaTextInputLayout.error = "Kolom Usia Harus di Isi"
            isCompleted = false
        }else{
            viewBind.usiaTextInputLayout.error = ""
        }

//        //Jenis
        if(jenis!!.isEmpty()){
            Toast.makeText(this, "Jenis hewan harus dipilih!", Toast.LENGTH_SHORT).show()
            isCompleted = false
        }else{
//            viewBind.jenisTextInputLayout.error = ""
        }

        animal.imageUri = image.toString()

        if (isCompleted){
            if (position == -1){
                GlobalVar.ListDataAnimal.add(animal)
            }
            else{
                GlobalVar.ListDataAnimal[position] = (animal)
            }
            Toast.makeText(this, "New Animal Added!", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}