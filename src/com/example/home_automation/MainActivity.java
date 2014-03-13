//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.io.FileOutputStream;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		//setting font type
		 TextView tv = (TextView) findViewById(R.id.textView1);
		    tv.setTypeface(Typeface.createFromAsset(getAssets(), "font/manteka.ttf"));
		    
		    
		ImageView myAnimation = (ImageView)findViewById(R.id.myanimation);
		final AnimationDrawable myAnimationDrawable
		= (AnimationDrawable)myAnimation.getDrawable();
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				  myAnimationDrawable.start();
		        new SSHconnections_run().execute(null,null,null);

				start_program(); // transitioning to the list view of the plug loads
				finish();
				 myAnimationDrawable.stop();
			}
			
		}, 3000);
	}


	
	private class SSHconnections_run extends AsyncTask<URL, Integer, Long>
	{
	

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			//connection c = new connection(1);
			//c.establishConnection();
			turnon z = new turnon();
			z.open_turon();
			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			startuprun t = new startuprun();
//			t.running();
			
			return null;
		}
		

	
	}
	
	//http://stackoverflow.com/questions/11455455/splash-screen-alpha-animation-in-android/11456132#11456132
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void start_program()
	{

		Intent intent = new Intent(this, ListActivity.class);

		startActivity(intent);
	}

}
