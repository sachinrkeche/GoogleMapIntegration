package keche.sachin.com.googlemapintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class map extends AppCompatActivity {
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(map.this, "Location changed" + location.getLatitude() + location.getLongitude(), Toast.LENGTH_LONG).show();

                String Longitude = "longtude is" + location.getLongitude();
                Log.v("Longitude", Longitude);


                String Latitude = "longtude is" + location.getLatitude();
                Log.v("Longitude", Latitude);

                String Cityname = null;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses;

                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                    if (addresses.size() > 0) {
                        System.out.print(addresses.get(0).getLocality());
                        Cityname = addresses.get(0).getLocality();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                }

                String s = Longitude + Latitude + "My current Location city is " + Cityname;
                Toast.makeText(map.this, " My details" + s, Toast.LENGTH_SHORT).show();

                // makeUseOfMyNewLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }
}
