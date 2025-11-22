package com.example.zapata_pablo_androideval1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RecuperarPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_password)

        val etEmail = findViewById<EditText>(R.id.etEmailRecuperar)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        btnRecuperar.setOnClickListener {
            if (etEmail.text.isEmpty()) {
                Toast.makeText(this, "Ingrese su email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Correo de recuperación enviado (ejemplo)", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolver.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
