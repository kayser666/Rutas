package irvinc.example.com.rutas.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import irvinc.example.com.rutas.R

class IniciarSesion : AppCompatActivity() {

    private var campo_usuario: EditText? = null
    private var campo_contra: EditText? = null
    private var mantenerSesion: CheckBox? = null
    private var botonEntrar: Button? = null
    private var btnRegistro: Button? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        supportActionBar?.title = "Iniciar Sesion"

        campo_usuario = findViewById(R.id.etUsuario_inicioSesion)
        campo_contra = findViewById(R.id.etContra_inicioSesion)
        mantenerSesion = findViewById(R.id.cbMantenerSesion_InicioSesion)
        botonEntrar = findViewById(R.id.btnLogin_inicioSesion)
        btnRegistro = findViewById(R.id.btnRegistro_inicioSesion)


    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        botonEntrar?.setOnClickListener {
            iniciarSesion()
        }

        btnRegistro?.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }

    }

    private fun iniciarSesion()
    {

    }

}