package net.therap;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GpsTrackingService extends Service  {

	private static final String TAG = "GpsTrackingService";

	@Override
	public IBinder onBind(Intent intent) {
		LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GpsLocationUpdateListener());
		return null;
	}

	

}
