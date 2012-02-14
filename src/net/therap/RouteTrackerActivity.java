package net.therap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RouteTrackerActivity extends Activity implements LocationListener {

	private static final String TAG = "RouteTrackerActivity";

	TextView txtInfo;
	TextView txtSatelliteInfo;
	LocationManager lm;
	StringBuilder sb;
	int noOfFixes = 0;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "onCreate");

		super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        txtInfo = (TextView) findViewById(R.id.textInfo);
        txtSatelliteInfo = (TextView) findViewById(R.id.txtSatelliteInfo);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        /*Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
        });*/

        startService(new Intent(this, PeriodicLoggingService.class));
    }
    
    @Override
	protected void onResume() {
    	Log.i(TAG, "onResume");
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 *
		 * add location listener and request updates every 1000ms or 10m
		 */
    	lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this);

		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		/* GPS, as it turns out, consumes battery like crazy */
		lm.removeUpdates(this);
		super.onResume();
	}

	public void onLocationChanged(Location location) {
		Log.v(TAG, "Location Changed");

		sb = new StringBuilder(512);

		noOfFixes++;

		/* display some of the data in the TextView */

		sb.append("No. of Fixes: ");
		sb.append(noOfFixes);
		sb.append('\n');
		sb.append('\n');

		sb.append("Londitude: ");
		sb.append(location.getLongitude());
		sb.append('\n');

		sb.append("Latitude: ");
		sb.append(location.getLatitude());
		sb.append('\n');

		sb.append("Altitiude: ");
		sb.append(location.getAltitude());
		sb.append('\n');

		sb.append("Accuracy: ");
		sb.append(location.getAccuracy());
		sb.append('\n');

		sb.append("Timestamp: ");
		sb.append(location.getTime());
		sb.append('\n');

		txtInfo.setText(sb.toString());
	}

	public void onProviderDisabled(String provider) {
		/* this is called if/when the GPS is disabled in settings */
		Log.v(TAG, "Disabled");

		/* bring up the GPS settings */
		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
	}

	public void onProviderEnabled(String provider) {
		Log.v(TAG, "Enabled");
		Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.i(TAG, "onStatusChanged");
		/* This is called when the GPS status alters */
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			Log.v(TAG, "Status Changed: Out of Service");
			Toast.makeText(this, "Status Changed: Out of Service",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v(TAG, "Status Changed: Temporarily Unavailable");
			Toast.makeText(this, "Status Changed: Temporarily Unavailable",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.AVAILABLE:
			Log.v(TAG, "Status Changed: Available");
			Toast.makeText(this, "Status Changed: Available",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		/* may as well just finish since saving the state is not important for this toy app */
		finish();
		super.onStop();
	}
}
