package hu.zsoltborza.example.mapdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.map);
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        start();

        MarkerOptions markerOptions = new MarkerOptions();
        Bitmap icon = BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.marker_red);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromBitmap(icon);
        markerOptions.icon(descriptor);
        mMap.addMarker(markerOptions
                .position(new LatLng(47.548, 19.0719793))
                .title("Marker A")
        );
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    protected GoogleMap getMap() {
        return mMap;
    }

    private void start() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.548, 19.0719793), 13));
        String overlayString = "http://tile.stamen.com/watercolor/{z}/{x}/{y}.jpg";

        CustomUrlTileProvider mTileProvider = new CustomUrlTileProvider(
                256,
                256, overlayString);
        getMap().addTileOverlay(
                new TileOverlayOptions().tileProvider(mTileProvider)
                        .zIndex(-1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);


    }
}
