package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class buscar_nav : AppCompatActivity() {

    var texto1: TextView? = null
    var boton1: Button? = null
    var boton2: Button? = null
    var boton3: Button? = null
    var boton4: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_buscar_nav)
        texto1 = findViewById(R.id.textView)
        boton1 = findViewById(R.id.buttonEliminar)
        boton2 = findViewById(R.id.buttonAgregar)
        boton3 = findViewById(R.id.buttonEditar)
        boton4 = findViewById(R.id.button)


        boton1?.setOnClickListener {
            startActivity(Intent(this,eliminar_data::class.java))
        }

        boton2?.setOnClickListener {
            startActivity(Intent(this,anidar_data::class.java))
        }

        boton3?.setOnClickListener {
            startActivity(Intent(this,editar_data::class.java))
        }

        boton4?.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        }

    }
