package irvinc.example.com.rutas.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import irvinc.example.com.rutas.R

class IniciarSesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        val btnRegistro = findViewById<Button>(R.id.btnRegistro_inicioSesion)
        btnRegistro.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }
    }


}