package tian.mike.fragment;


import android.location.Location;
import android.os.Bundle;
import android.content.*;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackLocation extends Fragment implements OnMapReadyCallback {


    GoogleMap mMap;
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;



    public TrackLocation() {
        // Required empty public constructor
    }

        /*// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);
        Log.d("TrackerLocation", "onCreateView");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapFragment1);
        mapFragment.getMapAsync(this);

        Log.d("TrackerLocation", "onViewCreate");
    }

    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        gpsTracker = new GPSTracker(getContext());
        mLocation = gpsTracker.getLocation();
        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();
        LatLng Location = new LatLng(latitude,longitude);
        MarkerOptions options = new MarkerOptions();
        options.position(Location).title("Your are here...");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Location,15));
        Log.d("TrackerLocation", "onMapReady");

    }


/*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (menu.getItem(0).getItemId() == R.menu.menu_maptype){
        }else{
            menu.clear();
            getActivity().getMenuInflater().inflate(R.menu.menu_maptype, menu);


        }
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mapTypeNone:
                return true;
            case R.id.mapTypeNormal:
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
