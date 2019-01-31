package irvinc.example.com.rutas.Desarrollador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import irvinc.example.com.rutas.R

class Programador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.programador)

        supportActionBar?.hide()

        val botonBack = findViewById(R.id.btnAtras_desarrollador) as ImageButton
        botonBack.setOnClickListener{
            onBackPressed()
        }
    }
}