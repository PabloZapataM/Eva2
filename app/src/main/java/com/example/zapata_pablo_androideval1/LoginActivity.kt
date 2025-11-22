package com.example.zapata_pablo_androideval1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnIrCrear = findViewById<Button>(R.id.btnIrCrearCuenta)
        val btnIrRecuperar = findViewById<Button>(R.id.btnIrRecuperar)

        btnLogin.setOnClickListener {
            if (etUsuario.text.isEmpty() || etPassword.text.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí iría Firebase Auth si luego lo conectas
                Toast.makeText(this, "Login correcto (ejemplo)", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        btnIrCrear.setOnClickListener {
            startActivity(Intent(this, CrearCuentaActivity::class.java))
        }

        btnIrRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarPasswordActivity::class.java))
        }
    }
}

