package com.example.front;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front.domain.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;

    MarkerOptions myLocationMarker;
    MarkerOptions destinationMarker;
    Button button=null;
    Button startbutton=null;
    LinearLayout container = null;
    boolean flag = true;
    Double latitude=null;
    Double longitude =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        startbutton = findViewById(R.id.startbutton);
        container = findViewById(R.id.container);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;

            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();

            }
        });


        AndPermission.with(this)
                .runtime()
                .permission(
                        Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //showToast("허용된 권한 갯수 : " + permissions.size());
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //showToast("거부된 권한 갯수 : " + permissions.size());
                    }
                })
                .start();

    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = null;
            if(flag){
                location  = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            else{
                location  = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 3000;
            float minDistance = 0;
//            Log.d("GPSListener2", "GPSListener2");

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                Log.d("Map", message);
            }
//            Log.d("GPSListener", "GPSListener");

            Toast.makeText(getApplicationContext(), "내 위치확인 요청함", Toast.LENGTH_SHORT).show();

            gpsListener.onLocationChanged(location);
        } catch(SecurityException e) {
            Log.d("SecurityException", "SecurityException");

            e.printStackTrace();
        }
    }

        public void mapButton(View view) {
//        skdalksdjlk
        }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
             latitude = location.getLatitude();
             longitude = location.getLongitude();

            String message = "내 위치 -> Latitude : "+ latitude + "\nLongitude:"+ longitude;
            Log.d("Map", message);

            showCurrentLocation(latitude, longitude);
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        Log.d("showCurrentLocation", "showCurrentLocation");
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        Log.d("showCurrentLocation2", "showCurrentLocation2");

        showMyLocationMarker(curPoint);
        showDestinationLocation();
    }
    private void showDestinationLocation(){

        LatLng curPoint = new LatLng(latitude, longitude);

        destinationMarker = new MarkerOptions();
        destinationMarker.position(curPoint);
        destinationMarker.title("● 내 위치\n");
        destinationMarker.snippet("● GPS로 확인한 위치");
        destinationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
        map.addMarker(destinationMarker);
        int x1 = (int)Math.random()*(1861900+0+1)+0;
        int y1 = (int)Math.random()*(112430932+116781668+1)-116781668;
        double tempX1 = latitude+x1;
        double tempY1 = longitude+y1;
        Log.d("tempX1", tempX1+"");
        Log.d("tempY1", tempY1+"");


        int x2 = (int)Math.random()*(902896502+894791498+1)-894791498;
        int y2 = (int)Math.random()*(11002536-3669818+1)+3669818;
        double tempX2 = latitude+x2;
        double tempY2 = longitude+y2;
        Log.d("tempX2", tempX2+"");
        Log.d("tempY2", tempY2+"");


    }

    private void showMyLocationMarker(LatLng curPoint) {
        Log.d("showMyLocationMarker", "showMyLocationMarker");

        if (myLocationMarker == null) {
            Log.d("showMyLocationMarker", "showMyLocationMarker null");

            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else {
            Log.d("showMyLocationMarker2", "showMyLocationMarker2 null");

            myLocationMarker.position(curPoint);
        }
    }

    public void startHandler(View view) {
        button.setVisibility(View.GONE);
        startbutton.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }
    //지도 모서리 gps or wifi로 위치찾기 버튼
    public void locationSetting(View view) {
        if (view != null) {
            flag = !flag;
            view.setSelected(flag);
        }
    }

    //뒤로가기 버튼  리스너
    @Override
    public void onBackPressed() {
        System.out.println(startbutton.getVisibility());
        if(startbutton.getVisibility()!=View.VISIBLE){
            container.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            startbutton.setVisibility(View.VISIBLE);
        }
        else{
            super.onBackPressed();
        }
    }
}




//36.026723,129.3657243

//우 36.025662199567385, 129.37926350394966
//위 36.03469116459556, 129.36803141321369
//왼 36.0258483896084, 129.35695259399662
//가운데 36.025662199567385, 129.36802041067705
//밑 36.016714284584715, 129.36802408049527

//우측(0,0.01124309327261)
//왼쪽(0.000186190041015,-0.01106781668043)
//위(0.009028965028175,0.00001100253664)
//아래(-0.00894791498267,0.00000366981822 )

//1861900 /  -116781668 112430932
//
//9028965028,11002536
//-8947914982, 3669818
