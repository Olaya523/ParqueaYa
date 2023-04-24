package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class editar_data : AppCompatActivity() {

    var texto1: TextView?=null
    var boton1: Button?=null
    var boton2: Button?=null
    var editTexto1: EditText?=null
    var editTexto2: EditText?=null
    var editTexto3: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        var nombre:String= ""
        var marca:String= ""
        var placa:String=""

        var data: String? = getIntent().getStringExtra("data")
        val instanciaFirebase = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_data)

        texto1=findViewById(R.id.textFinal)
        boton1=findViewById(R.id.button)
        boton2=findViewById(R.id.button2)
        editTexto1=findViewById(R.id.editText1) //placa
        editTexto2=findViewById(R.id.editText2) //marca
        editTexto3=findViewById(R.id.editText3) //NOMBRE
        editTexto3?.setText(nombre)
        editTexto2?.setText(marca)
        editTexto1?.setText(placa)

        boton2?.setOnClickListener {
            placa = editTexto1?.text.toString()
            marca = editTexto2?.text.toString()
            nombre = editTexto3?.text.toString()
            instanciaFirebase.collection("prueba2").document(data.toString()).update(mapOf("placa" to placa, "marca" to marca, "nombre" to nombre))
                .addOnSuccessListener {
                    instanciaFirebase.collection("prueba2").document(data.toString()).update(mapOf("idDoc" to data))
                    startActivity(Intent(this,buscar_nav::class.java))
                }.addOnFailureListener{
                    Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                }
        }

        boton1?.setOnClickListener {
            startActivity(Intent(this,buscar_nav::class.java))
        }


    }
}