package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class anidar_data : AppCompatActivity() {

    var texto1: TextView?=null
    var boton1: Button?=null
    var boton2: Button?=null
    var editTexto1: EditText?=null
    var editTexto2: EditText?=null
    var editTexto3: EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val instaciaFirebase = FirebaseFirestore.getInstance()
        setContentView(R.layout.activity_anidar_data)

        var nombre:String= ""
        var marca:String= ""
        var placa:String=""

        texto1=findViewById(R.id.textFinal)
        boton1=findViewById(R.id.button)
        boton2=findViewById(R.id.button2)
        editTexto1=findViewById(R.id.editText1)
        editTexto2=findViewById(R.id.editText2)
        editTexto3=findViewById(R.id.editText3)


        boton1?.setOnClickListener {
            startActivity(Intent(this, buscar_nav::class.java))
        }

        boton2?.setOnClickListener {
            nombre= editTexto3?.text.toString()
            marca= editTexto2?.text.toString()
            placa= editTexto1?.text.toString()

            instaciaFirebase.collection("prueba2")
              .add(mapOf("placa" to editTexto1?.text.toString(), "marca" to editTexto2?.text.toString(), "nombre" to editTexto3?.text.toString()))
            .addOnSuccessListener {
              instaciaFirebase.collection("prueba2").document(it.id)
                .update(mapOf("idDoc" to it.id))
            }.addOnFailureListener {
              Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }


    }
}