package irvinc.example.com.rutas.Desarrollador

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import irvinc.example.com.rutas.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Programador : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View = inflater.inflate(R.layout.fragment_programador, container, false)

        return View
    }


}