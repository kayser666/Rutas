package irvinc.example.com.rutas.Inicio;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import irvinc.example.com.rutas.R;

public class Mapa extends Fragment implements OnMapReadyCallback {

    private View vista;
    private MapView mapView;
    private GoogleMap mapa;
    private Button boton;

    private String[] rutas = new String[] {"Ruta 9", "Eje", "Naranjos"};

    public Mapa() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         vista = inflater.inflate(R.layout.fragment_mapa, container, false);

         boton = getActivity().findViewById(R.id.btnRutas_appBarHome);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = vista.findViewById(R.id.mapita);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRuta();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        MapsInitializer.initialize(getContext());

        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setMinZoomPreference(11.0f);
//        mapa.setOnMarkerClickListener(this);

        LatLng mexicali = new LatLng(32.6278100, -115.4544600);
        mapa.moveCamera(CameraUpdateFactory.newLatLng(mexicali));

        MarkerOptions marker = new MarkerOptions().position(new LatLng(32.6278100, -115.4544600));
        mapa.addMarker(marker);
    }

    @SuppressLint("MissingPermission")
    private void permisoUbicacion()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            boolean permisoActivado;
            int resultado = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
            if(resultado == PackageManager.PERMISSION_GRANTED)
            {
                permisoActivado = true;
            }
            else
            {
                permisoActivado = false;
            }

            if(permisoActivado == false)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION))
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                else
                {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
            else
            {
                mapa.setMyLocationEnabled(true);
            }
        }
        else
        {
            mapa.setMyLocationEnabled(true);
        }
    }

    private void seleccionarRuta()
    {
        AlertDialog.Builder ventanita = new AlertDialog.Builder(getActivity());
        ventanita.setTitle("Seleccionar ruta");
        //// MOSTRAR SPINNER /////
        final Spinner sp = new Spinner(getActivity());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, rutas);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adapter);

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(sp);
        ventanita.setView(linearLayout);

        ventanita.setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String seleccionRuta = sp.getSelectedItem().toString();

                if(seleccionRuta.equals("Ruta 9")) {
                    ruta9();
                }
            }
        });
        ventanita.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }});

        ventanita.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) { }});

        ventanita.show();
    }

    public void ruta9()
    {
        mapa.clear();
    }


}