package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class eliminar_data : AppCompatActivity() {


    var nombre: EditText?=null;
    var txt: TextView?=null;
    var txt2: TextView?=null;
    var txt3: TextView?=null;
    var txt4: TextView?=null;
    var btnDel: Button?=null;
    var btnCon: Button?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        val instanciaFirebase= FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_data)

        nombre=findViewById(R.id.etPetName)
        btnDel=findViewById(R.id.btnDel)
        btnCon=findViewById(R.id.btnCons)
        txt=findViewById(R.id.txtCon)
        txt2=findViewById(R.id.txtCon2)
        txt3=findViewById(R.id.txtCon3)
        txt4=findViewById(R.id.txtCon4)
        var nombreMan:String= ""
        var data=""
        var data1=""
        var data2=""
        var data3=""

        btnCon?.setOnClickListener {
            nombreMan = nombre?.text.toString()
            instanciaFirebase.collection("prueba2").whereEqualTo("nombre",nombreMan).get().addOnSuccessListener {
                it.documents.forEach {
                    Log.i("items", it.data.toString());
                    data = it?.data?.get("idDoc").toString()
                    data1 = it?.data?.get("placa").toString()
                    data2 = it?.data?.get("modelo").toString()
                    data3 = it?.data?.get("nombre").toString()
                    txt?.setText("Encontrada.")
                    txt2?.setText("encontrada")
                    txt3?.setText("Encontrada.")
                    txt4?.setText("Encontrada.")
                }
            }.addOnFailureListener {
                txt?.setText("No Encontrada.")
            }
        }

        btnDel?.setOnClickListener {
            instanciaFirebase.collection("prueba2").document(data).delete().addOnSuccessListener {
                startActivity(Intent(this,buscar_nav::class.java))
            }.addOnFailureListener {
                Log.d("delete", it.localizedMessage!!)
                txt?.setText("Error al borrar.")
            }
        }

    }
}