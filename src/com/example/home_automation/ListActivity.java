//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.io.BufferedReader;
import com.example.scheduler.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class ListActivity extends FragmentActivity implements MyProfilesFragment.ClickOnHours, HeaderFragment.writing, HeaderFragment.ListClickListener, ModifyFragment.scheduleButton, ScheduleFragment.currentSchedule {

	//private String clicked_plugName;
	private SensorManager mSensorManager;

	  private ShakeEventListener mSensorListener;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		   Context context = this;

		  
		  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		    mSensorListener = new ShakeEventListener();   

		    mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

		      public void onShake() {
		    	  System.out.println("shake");
		        	//new SSHconnections_shake().execute(null,null,null);
		          new AsyncClass(ListActivity.this).execute();




		        //Toast.makeText(context, "Shake!", Toast.LENGTH_SHORT).show();
		      }
		    });
		if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HeaderFragment firstFragment = new HeaderFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
		

	}
	
	@Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(mSensorListener,
	        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	        SensorManager.SENSOR_DELAY_UI);
	  }

	  @Override
	  protected void onPause() {
	    mSensorManager.unregisterListener(mSensorListener);
	    super.onPause();
	  }
	  
	//handles the plug that is selected from the main page
	public void onPlugSelected(int position)
	{
		// Create fragment and give it an argument specifying the article it should show
		ModifyFragment newFrag = new ModifyFragment();
		Bundle args = new Bundle();
		args.putInt(ModifyFragment.ARG_POSITION, position);
		newFrag.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}
	
	//happens on the schedule fragment, handles schedule botton click
	public void schedule_botton_click()
	{
		ScheduleFragment newFrag = new ScheduleFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}

	//handles current schedule button click on the schedule fragment page
	public void currentSchedule_button()
	{
		MyProfilesFragment newFrag = new MyProfilesFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}
	

	//turning on the lights
	private class SSHconnections_ON extends AsyncTask<URL, Integer, Long>
	{
	

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			//connection c = new connection(1);
			//c.establishConnection();
			turnon t = new turnon();
			t.open_turon();
			

			return null;
		}
		
		//need to add onPreExecute() to prepare the load screen for the program
		
		//need to add onProgressUpdate() to add a spinner that shows that is connecting to turn on/off
		//on postexecute maybe let the user know that the device is on with a green light button or a red button
	
	}
	
	/*
	private class SSHconnections_shake extends AsyncTask<URL, Integer, Long>
	{

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			//connection c = new connection(1);
			//c.establishConnection();

			shaketurnon t = new shaketurnon();
			t.open_turon();

			return null;
		}
		
		
		
		
		//need to add onPreExecute() to prepare the load screen for the program
		
		//need to add onProgressUpdate() to add a spinner that shows that is connecting to turn on/off
		//on postexecute maybe let the user know that the device is on with a green light button or a red button
	
	}
	*/
	
	
	private class AsyncClass extends AsyncTask<Void, Void, Void> {
	    private Context context;
	    ProgressDialog dialog;

	        public AsyncClass(Context cxt) {
	            context = cxt;
	            dialog = new ProgressDialog(context);
	        }

	        @Override
	        protected void onPreExecute() {
	            dialog.setTitle("Updating device list....");
	            dialog.show();
	        }

	        @Override
	        protected Void doInBackground(Void... unused) {
				shaketurnon t = new shaketurnon();
				t.open_turon();
				System.out.println("test");
	            return (null);
	        }

	        @Override
	        protected void onPostExecute(Void unused) {
	            dialog.dismiss();
	        }
	    }
	
	
	//turning off the lights
	private class SSHconnections_OFF extends AsyncTask<URL, Integer, Long>
	{

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			
			//might be able to get rid of the connections at this part
			// connection c = new connection();
			//c.establishConnection();
			
			//connection c = new connection(0);
			//c.establishConnection();
			turnoff t = new turnoff();
			t.open_turnoff();
	

			return null;
		}
		
	
	}
	
	private class SSHconnection_overall extends AsyncTask<URL, Integer, Long>
	{

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			connection c = new connection();
			c.establishConnection();
			
			return null;
		}
		
	}


    //only use when on uci wifi
    public void onToggleClicked(View view)
    {
    	
    	boolean on = ((Switch)view).isChecked();
    	if(on)
    	{
    		System.out.println("on");
        	new SSHconnections_ON().execute(null,null,null);


    	}
    	else
    	{
    		System.out.println("off");
        	new SSHconnections_OFF().execute(null,null,null);

    	}
    }
    
	 String FILENAME = "saved_devices";


	 public void writeOutSavedDevices(List<String> plug_name)
	 {
		 //String test = "hello world test!";
		 FileOutputStream fos;

		 try{
			 
			 fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			 for(String str : plug_name)
			 {
				 String parse = str + "/";
				 fos.write(parse.getBytes());
				 
			 }
			 //fos.write(test.getBytes());
			 fos.close();
			 
			 System.out.println("finished writing");
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 
		@Override 
		protected void onDestroy()
		{
			super.onDestroy();
			//write to memory
			// writeOutSavedDevices();
		}
		
		
		@Override
		protected void onStop()
		{
			super.onStop();
			//writeOutSavedDevices();
		}
		
		@Override
		protected void onStart()
		{
			
			super.onStart();
			readSavedDevices();
		}
	
		String line;

		 private void readSavedDevices()
		 {
			 
			 //need to add what is read in this file to listOfPlugs.plug_namez
			 //listOfPlugs.plug_namez
			 FileInputStream fis;

			try {
				fis = openFileInput(FILENAME);
				   InputStreamReader isr = new InputStreamReader(fis);
				   BufferedReader bufferedReader = new BufferedReader(isr);
				   StringBuilder sb = new StringBuilder();

					try {
						while ((line = bufferedReader.readLine()) != null) {
						       sb.append(line);
						   }
						System.out.println(sb);
						listOfPlugs.saved_memory_of_plugs = sb.toString();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.print("error in input output");
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("file not found");

			}
			


		 }
		
		 
		 //handles clicks on scheduler
		 
		 public void onClickListener()
		 {
				System.out.println("testeststsetsetestsetest");

		 }
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
