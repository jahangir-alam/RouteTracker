package net.therap;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class GpsLocationUpdateListener implements LocationListener {

	private static final String TAG = "GpsLocationUpdateListener";

	public void onLocationChanged(Location location) {
		Log.i(TAG, "LocationChanged " + location);
	}

	public void onProviderDisabled(String provider) {
		Log.i(TAG, "onProviderDisabled " + provider);
		
	}

	public void onProviderEnabled(String provider) {
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.i(TAG, "onStatusChanged provider: " + provider + " status: " + status);		
	}
}
