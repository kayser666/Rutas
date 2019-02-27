package irvinc.example.com.rutas.Inicio;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.PolylineOptions;
import irvinc.example.com.rutas.R;

public class Mapa extends Fragment implements OnMapReadyCallback {

    private View vista;
    private GoogleMap mapa;
    private Button btnRutas;

    private String[] rutas = new String[] {"Ruta 9", "Eje"};
    private static final int GRUESOLINEA = 4;

    public Mapa() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         vista = inflater.inflate(R.layout.fragment_mapa, container, false);

         btnRutas = getActivity().findViewById(R.id.btnRutas_appBarHome);

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapView mapView = vista.findViewById(R.id.mapita);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarRuta();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.setMinZoomPreference(11.0f);
        mapa.setMaxZoomPreference(17.0f);
//        mapa.setOnMarkerClickListener(this);

        LatLng mexicali = new LatLng(32.6278100, -115.4544600);
        mapa.moveCamera(CameraUpdateFactory.newLatLng(mexicali));

        permisoUbicacion();
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

        ventanita.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String seleccionRuta = sp.getSelectedItem().toString();

                if(seleccionRuta.equals("Ruta 9")) {
                    btnRutas.setText(seleccionRuta);
                    ruta9();
                } else if(seleccionRuta.equals("Eje")){
                    btnRutas.setText("Eje");
                    eje();
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

    private void ruta9() {
        mapa.clear();

        //// PUNTO DE SALIDA A PRIMER VUELTA OFICINAS OXXO ////
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.583385, -115.355832), new LatLng(32.586228, -115.354673)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.586228, -115.354673), new LatLng(32.588253, -115.352881)).width(GRUESOLINEA).color(Color.RED));

        //// HASTA COCA-COLA ////
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.588253, -115.352881), new LatLng(32.62342, -115.406202)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.62342, -115.406202), new LatLng(32.624574, -115.408185)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.624574, -115.408185), new LatLng(32.6251, -115.410249)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()
                .add(new LatLng(32.6251, -115.410249), new LatLng(32.624658, -115.443268)).width(GRUESOLINEA).color(Color.RED));

        //// HASTA GLORIETA LAZARO CARDENAS Y BENITO JUARES ////
        mapa.addPolyline(new PolylineOptions()// De lazaro a benito //
                .add(new LatLng(32.624658, -115.443268), new LatLng(32.625394, -115.443934)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//desde glorieta a calle indepencia //
                .add(new LatLng(32.625394, -115.443934), new LatLng(32.637229, -115.448417)).width(GRUESOLINEA).color(Color.RED));

        ///////// TODA LA CALLE INDEPENDENCIA ////
        mapa.addPolyline(new PolylineOptions()// de benito J a L montejano sobre indepencia //
                .add(new LatLng(32.637229, -115.448417), new LatLng(32.636214, -115.452275)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// deL montejano a  konicentro //
                .add(new LatLng(32.636214, -115.452275), new LatLng(32.636039, -115.454549)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// desde konicentro a punte ferrocarril//
                .add(new LatLng(32.636039, -115.454549), new LatLng(32.636943, -115.468071)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// curva del puente ferrocarril //
                .add(new LatLng(32.636943, -115.468071), new LatLng(32.637796, -115.470106)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// del puente hasta calle del hospital//

                //////  CALLE DEL HOSPITAL ////
                .add(new LatLng(32.637796, -115.470106), new LatLng(32.643325, -115.477009)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// desde calle independencia a curva del hospital //
                .add(new LatLng(32.643325, -115.477009), new LatLng(32.644426, -115.476651)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// del hospital a dhl //
                .add(new LatLng(32.644426, -115.476651), new LatLng(32.647392, -115.473365)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// dhl a first cash //
                .add(new LatLng(32.647392, -115.473365), new LatLng(32.649066, -115.472107)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// first cash a lopez mateos //
                .add(new LatLng(32.649066, -115.472107), new LatLng(32.649291, -115.471689)).width(GRUESOLINEA).color(Color.RED));

        ///////////// LOPEZ MATEOS ///////////////
        mapa.addPolyline(new PolylineOptions()// esquina de la calle del hospital a la glorieta //
                .add(new LatLng(32.649291, -115.471689), new LatLng(32.650918, -115.472462)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// curva glorieta //
                .add(new LatLng(32.650918, -115.472462), new LatLng(32.651416, -115.472549)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()// curva glorieta a sol del nino //
                .add(new LatLng(32.651416, -115.472549), new LatLng(32.65213, -115.473333)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.65213, -115.473333), new LatLng(32.652866, -115.473831)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.652866, -115.473831), new LatLng(32.653361, -115.474315)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.653361, -115.474315), new LatLng(32.654178, -115.475031)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.654178, -115.475031), new LatLng(32.657153, -115.479102)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.657153, -115.479102), new LatLng(32.662711, -115.491335)).width(GRUESOLINEA).color(Color.RED));
        //////// CALLE ALTAMIRANO //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.662711, -115.491335), new LatLng(32.658287, -115.491262)).width(GRUESOLINEA).color(Color.RED));

        //////// CALLE PEDRO MORENO //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.658287, -115.491262), new LatLng(32.657877, -115.49275)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.657877, -115.49275), new LatLng(32.657261, -115.494122)).width(GRUESOLINEA).color(Color.RED));

        //////// CALLE MERIDA //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.657261, -115.494122), new LatLng(32.65615, -115.494118)).width(GRUESOLINEA).color(Color.RED));

        //////// CALLE MICHOACAN //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.65615, -115.494118), new LatLng(32.656145, -115.510611)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.656145, -115.510611), new LatLng(32.6561, -115.51322)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.6561, -115.51322), new LatLng(32.656124, -115.522637)).width(GRUESOLINEA).color(Color.RED));

        //////// CALLE YUGUSLAVIA //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.656124, -115.522637), new LatLng(32.628365, -115.522779)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.628365, -115.522779), new LatLng(32.627506, -115.522998)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.627506, -115.522998), new LatLng(32.626435, -115.523699)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.626435, -115.523699), new LatLng(32.625439, -115.523881)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.625439, -115.523881), new LatLng(32.624838, -115.523921)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.624838, -115.523921), new LatLng(32.624217,  -115.524086)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.624217, -115.524086), new LatLng(32.621708,  -115.524098)).width(GRUESOLINEA).color(Color.RED));
        /////////   LAZARO CARDENAS /////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.621708, -115.524098), new LatLng(32.621747,  -115.512025)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.621747, -115.512025), new LatLng(32.623799,  -115.506449)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.623799, -115.506449), new LatLng(32.622929,  -115.505407)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.622929, -115.505407), new LatLng(32.613271,  -115.505375)).width(GRUESOLINEA).color(Color.RED));
        /////// TERAN TERAN /////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.613271, -115.505375), new LatLng(32.613256,  -115.487261)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.613256, -115.487261), new LatLng(32.61134,  -115.479113)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.61134, -115.479113), new LatLng(32.606749,  -115.466667)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.606749, -115.466667), new LatLng(32.605387,  -115.442309)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.605387, -115.442309), new LatLng(32.603439,  -115.438972)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.603439, -115.438972), new LatLng(32.604319,  -115.434408)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.604319, -115.434408), new LatLng(32.606551, -115.431498)).width(GRUESOLINEA).color(Color.RED));
        ////// CARRETERA SAN LUIS /////////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.606551, -115.431498), new LatLng(32.60152, -115.425413)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.60152, -115.425413), new LatLng(32.598441, -115.420182)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.598441, -115.420182), new LatLng(32.597155 , -115.416867)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.597155, -115.416867), new LatLng(32.577341  , -115.359466)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.577341, -115.359466), new LatLng(  32.580486 , -115.357887)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.580486, -115.357887), new LatLng( 32.581974 , -115.356451)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.581974, -115.356451), new LatLng(32.583385, -115.355832)).width(GRUESOLINEA).color(Color.RED));
    }

    private void eje()
    {
        mapa.clear();

        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.593593, -115.577116), new LatLng(32.598352,  -115.573913)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.598352, -115.573913), new LatLng(32.599302, -115.57369)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.599302, -115.57369), new LatLng(32.613239, -115.573723)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.613239, -115.573723), new LatLng(32.614541, -115.573896)).width(GRUESOLINEA).color(Color.RED));
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.614541, -115.573896), new LatLng(32.622006, -115.573907)).width(GRUESOLINEA).color(Color.RED));
        //////   LAZARO //////
        mapa.addPolyline(new PolylineOptions()//  //
                .add(new LatLng(32.622006, -115.573907), new LatLng(32.622042, -115.536233)).width(GRUESOLINEA).color(Color.RED));
    }

}