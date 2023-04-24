package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    var usuarioTxt: TextView? = null
    var passTxt: EditText? = null
    var boton1: Button? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton1=findViewById(R.id.button3)
        usuarioTxt=findViewById(R.id.idTVCourse2)
        passTxt=findViewById(R.id.idTVCourse1)

        boton1?.setOnClickListener{
            Log.i("mensaje","Hola mundo");
            var usuario:String=usuarioTxt?.text.toString();
            var password:String=passTxt?.text.toString();
            mAuth= FirebaseAuth.getInstance();
            mAuth!!.signInWithEmailAndPassword("1@gmail.com","123456").addOnCompleteListener (this){
                    task->if(task.isSuccessful){
                val user=mAuth!!.currentUser;
                startActivity(Intent(this,buscar_nav::class.java).putExtra("uid",user?.uid));
            }else{
                Log.w("TextoCualquiera","Error autenticación");
                Toast.makeText(this,"Autenticación Fallida",Toast.LENGTH_SHORT).show()
            }
            }
        };

    }
}