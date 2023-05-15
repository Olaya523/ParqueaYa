package com.example.proyecto

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa FirebaseAuth
        auth = FirebaseAuth.getInstance()

        usernameEditText = findViewById(R.id.editTextUsername)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        registerButton = findViewById(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Aquí llamada a Firebase para registrar el usuario
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso, actualiza UI con la información del usuario
                        val user = auth.currentUser
                        Toast.makeText(baseContext, "Registro exitoso.",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        // Si el registro falla, muestra un mensaje al usuario.
                        Toast.makeText(baseContext, "Error de autenticación.",
                            Toast.LENGTH_SHORT).show()
                        Log.w("RegisterActivity", "createUserWithEmail:failure", task.exception)
                    }
                }
        }
    }
}
