package com.businesstime;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((EditText)findViewById(R.id.phone_number)).setOnKeyListener(
		        new OnKeyListener() { // .OnEditorActionListener() {

					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
							InputMethodManager imm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
							imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
							sendSMS();
							return true;
						}
						return false;
					}});
	}
	
	private void sendSMS()
    {        
		String phoneNumber = ((EditText)findViewById(R.id.phone_number)).getText()+"";
		String message = "I'm in the mood!";
        PendingIntent pi = PendingIntent.getActivity(this, 0,
            new Intent(this, SmsManager.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);        
    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
