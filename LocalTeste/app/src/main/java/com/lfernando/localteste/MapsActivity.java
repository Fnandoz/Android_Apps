package com.lfernando.localteste;



import android.app.DownloadManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView aboutText;
    LatLng myCurrentLocation;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        aboutText = (TextView) findViewById(R.id.textView);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        RequestQueue queue = Volley.newRequestQueue(this);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(1,1)));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //mMap.addMarker(new MarkerOptions().position(latLng).title("Marcador"));
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //marker.remove();
                aboutText.setText(marker.getTitle()+marker.getId());
                return false;
            }
        });

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://geoapi.getsandbox.com/getgeo", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mJSONArray = response.getJSONArray("locais");
                            for(int i = 0; i < mJSONArray.length(); i++){
                                mJSONArray.getJSONObject(i);
                                mMap.addMarker(new MarkerOptions()
                                        .position(
                                        new LatLng(mJSONArray.getJSONObject(i).getDouble("lat"),
                                                mJSONArray.getJSONObject(i).getDouble("lat")))
                                        .title(mJSONArray.getJSONObject(i).getString("titulo")));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        queue.add(jsObjRequest);
    }




}
