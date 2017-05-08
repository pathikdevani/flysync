package com.pathik.service;

import java.io.IOException;

import com.pathik.flysync.DataBase;
import com.pathik.flysync.MainActivity;
import com.pathik.server.NANOServer;
import com.pathik.server.UDPserver;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import pathik.network.WIFINetwork;

public class MyService extends Service{

	
	public static Context context;
	private final int PORT = 8080;
	private UDPserver udp;
	private NANOServer nano;
	public static boolean isOn;
	
	public  static DataBase database;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		context = getBaseContext();
		isOn = true;
		database = new DataBase(context);
		
		udp = new UDPserver(getBaseContext(),PORT);
		nano = new NANOServer(getApplicationContext(), PORT);
	
		start();
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	
    	udp.stop();
    	nano.stop();
    	stopAsForeground();
    	isOn = false;			
    }
    
    private void start(){
    	udp.start();
    	try {
			nano.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	runAsForeground();
    }
    
   
    public void stopAsForeground(){
        stopForeground(true);
    }
    
    private void runAsForeground(){

    	WIFINetwork wifi = new WIFINetwork(getBaseContext());
    	wifi.updateConnectivity();
    	String ip = wifi.getIp();

        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification=new NotificationCompat.Builder(this)
                .setSmallIcon(com.pathik.flysync.R.drawable.ic_launcher)
                .setContentText(ip)
                .setContentTitle("Flysync runing...")
                .setTicker("flysync runing...")
                .setContentIntent(pendingIntent).build();

        startForeground(1, notification);
    }
}
