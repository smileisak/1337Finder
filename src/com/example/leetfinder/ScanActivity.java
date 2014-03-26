package com.example.leetfinder;


import android.app.Activity;
import android.content.*;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;



public class ScanActivity extends Activity {
	
	private WifiManager wifiManager;
	private Button btn;
	private WifiInfo info=null; 
	private String bssid ;
	private String essid ;
	private String ip ;
	
	
 public String getIpAddr() {
		   WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		   WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		   int ip = wifiInfo.getIpAddress();

		   String ipString = String.format(
		   "%d.%d.%d.%d",
		   (ip & 0xff),
		   (ip >> 8 & 0xff),
		   (ip >> 16 & 0xff),
		   (ip >> 24 & 0xff));

		   return ipString;
		}	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		
		wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
		info = wifiManager.getConnectionInfo();
		
		bssid = info.getBSSID();
		TextView bssidTextView = (TextView) findViewById(R.id.BSSID_Value);
        bssidTextView.setText(bssid);
        
        essid = info.getSSID();
		TextView essidTextView = (TextView) findViewById(R.id.ESSID_Value);
        essidTextView.setText(essid);
        
        ip = getIpAddr();
		TextView ipTextView = (TextView) findViewById(R.id.IP_Value);
        ipTextView.setText(ip);

        
        btn = (Button) findViewById(R.id.connect_btn);
        if (bssid == null)
        	Toast.makeText(ScanActivity.this, "Press connect Button...", Toast.LENGTH_LONG).show();
        
        btn.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
            // TODO Auto-generated method stub
            	 if (bssid != null)
            		 Toast.makeText(ScanActivity.this, "You have allready connected to " + essid, Toast.LENGTH_LONG).show();
            	 else
            		 {Toast.makeText(ScanActivity.this, "Need Wifi Connection", Toast.LENGTH_LONG).show();
            	     startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));}
            	 	 
            }

        });

    /*    if (info.getBSSID() != null) {
        	 //  int force = WifiManager.calculateSignalLevel(info.getRssi(), 5);
        	   Toast.makeText(this, "Connecté à " + info.getSSID() +
        	   " à " + info.getLinkSpeed() + WifiInfo.LINK_SPEED_UNITS +
        	   " avec une force " + "/5   " +getIpAddr() , 1000000000).show();
        	}       
        
        
	}*/

	}

}
