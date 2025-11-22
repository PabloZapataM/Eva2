package com.example.zapata_pablo_androideval1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class CrearCuentaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        auth = FirebaseAuth.getInstance()

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etDireccion = findViewById<EditText>(R.id.etDireccion)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCrear = findViewById<Button>(R.id.btnCrear)
        val btnVolver = findViewById<Button>(R.id.btnVolverLogin)

        btnCrear.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🔥 Crear cuenta en Firebase Auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()

                    // Puedes guardar otros datos en Firestore si quieres
                    /*
                    val uid = it.user!!.uid
                    val db = FirebaseFirestore.getInstance()
                    val userData = mapOf(
                        "nombre" to nombre,
                        "telefono" to telefono,
                        "direccion" to direccion
                    )
                    db.collection("usuarios").document(uid).set(userData)
                    */

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .addOnFailureListener { error ->
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
        }

        btnVolver.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
