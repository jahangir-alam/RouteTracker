package net.therap;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PeriodicLoggingService extends Service {

	private static final String TAG = "PeriodicLoggingService";

	private Timer timer = new Timer(); 

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "onStart");
		super.onStart(intent, startId);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Log.i(TAG, "Running Timer Tast at " + System.currentTimeMillis());
			}
		}, 100, 5000);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
