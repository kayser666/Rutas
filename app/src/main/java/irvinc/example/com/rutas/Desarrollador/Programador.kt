package irvinc.example.com.rutas.Desarrollador

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import irvinc.example.com.rutas.R
import android.widget.ImageButton

class Programador : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val View = inflater.inflate(R.layout.fragment_programador, container, false)

        val botonBack = View.findViewById(R.id.btnAtras_desarrollador) as ImageButton
        botonBack.setOnClickListener{
            activity?.onBackPressed()
        }

        return View
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}