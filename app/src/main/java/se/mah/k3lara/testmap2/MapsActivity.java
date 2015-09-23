package se.mah.k3lara.testmap2;

//import android.app.FragmentTransaction;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMarkerClickListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener
    {

        private static final String TAG = "MapsActivity";
        private GoogleMap mMap; // Might be null if Google Play services APK is not available.
        private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings mUISetting  = mMap.getUiSettings();
        mUISetting.setZoomControlsEnabled(true);
        mUISetting.setMyLocationButtonEnabled(true);

        LatLng home = new LatLng(55.61,13.01);
        LatLng home2 = new LatLng(55.612629, 13.004221);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(home2));
        //mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        Location myLocation = mMap.getMyLocation();

        mMap.addMarker(new MarkerOptions()
                .position(home2)
                .title("Malmö")
                .snippet("This is my home")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.florist_black)));

        mMap.addMarker(new MarkerOptions()
                .position(home)
                .title("Malmö")
                .snippet("This is my home 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.florist_black)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));


        mMap.setOnMarkerClickListener(this);





    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MarkerClickDialog mDialog = new MarkerClickDialog();
        mDialog .show(ft, "dialog");
        return true;
    }

        @Override
        public void onConnected(Bundle bundle) {
            Log.i(TAG,"Connected");
            LocationRequest mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(10000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onLocationChanged(Location location) {
            Log.i(TAG, "Location: "+location.getLongitude()+"time"+location.getTime() );
        }
    }
