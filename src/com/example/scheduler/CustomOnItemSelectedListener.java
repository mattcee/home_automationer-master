package com.example.scheduler;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomOnItemSelectedListener extends Activity implements OnItemSelectedListener {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		System.out.println("test2");
		// TODO Auto-generated method stub
		Toast.makeText(arg0.getContext(), "Selected : " + arg0.getItemAtPosition(arg2), Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
