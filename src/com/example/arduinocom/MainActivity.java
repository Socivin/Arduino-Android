package com.example.arduinocom;

import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

	TextView txt;
	private static final int VID = 0x2341;
	private static final int PID = 0x0043;//I believe it is 0x0000 for the Arduino Megas
	private static UsbController sUsbController;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.textView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void ButtonClick(View v) 
    {
    	switch (v.getId()) {
		case R.id.button1_Connect:
		{
			txt.setText("Connect");
			if(sUsbController == null){
		        sUsbController = new UsbController(this, mConnectionHandler, VID, PID);
		        txt.setText("Connect_IF");
			}
			
			break;
			
		}
		case (R.id.button1_a):			
		{
			txt.setText("a");
			if (sUsbController != null) sUsbController.send((byte)'a');
			break;
		}
		case (R.id.button2_b):
		{
			txt.setText("b");
			if (sUsbController != null) sUsbController.send((byte)'b');
			break;
		}
		case (R.id.button3_c):
		{
			txt.setText("c");
			if (sUsbController != null) sUsbController.send((byte)'c');
			break;
		}
		case (R.id.button4_d):
		{
			txt.setText("d");
			if (sUsbController != null) sUsbController.send((byte)'d');
			break;
		}		

		default:
			break;
		}		

	}
    	private final IUsbConnectionHandler mConnectionHandler = new IUsbConnectionHandler() {
    		@Override
    		public void onUsbStopped() {
    			L.e("Usb stopped!");
    		}
    		
    		@Override
    		public void onErrorLooperRunningAlready() {
    			L.e("Looper already running!");
    		}
    		
    		@Override
    		public void onDeviceNotFound() {
    			if(sUsbController != null){
    				sUsbController.stop();
    				sUsbController = null;
    			}
    		}
    	};    	
}
