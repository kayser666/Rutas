package irvinc.example.com.rutas.Login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import irvinc.example.com.rutas.R

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
    }
}