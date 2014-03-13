//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.net.URL;

import com.example.home_automation.HeaderFragment.ListClickListener;
 
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ModifyFragment extends Fragment implements OnClickListener{

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    Button myButton;
    scheduleButton mCallBack;
    Switch toggle;
    ImageView img;
    public interface scheduleButton
    {
    	public void schedule_botton_click();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

    	
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
    	View myView = inflater.inflate(R.layout.modify_view, container, false);
    	myButton = (Button) myView.findViewById(R.id.button1);
    	myButton.setOnClickListener(this);
    	
    	//switch
    	toggle = (Switch) myView.findViewById(R.id.switch1);
    	img = (ImageView) myView.findViewById(R.id.imageViewChange);

    	
    	new SSHconnection_readGPIO().execute(null,null,null);

    	//Power textView
    	
    	//power = (TextView) myView.findViewById(R.id.powerTextView);
    	
    	
    	
    	//use only when we get the saved preference correct
    	//toggle.setChecked(true);
		return myView;
	}


	@Override 
	public void onStart(){
		super.onStart();
        Bundle args = getArguments();
        if (args != null) {
        	updateTitle(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
        	updateTitle(mCurrentPosition);
        }
	}
	
	
	//setting the text for the text view
	 
	
	
	//need to change
	public void updateTitle(int position)
	{
		
		//listOfPlugs plugs = new listOfPlugs();
		//String name = plugs.plug_names[position];
		String name = listOfPlugs.plug_namez.get(position);
		if(name.equals("Lamp"))
		{
			img.setImageResource(R.drawable.lamp);
		}
		if(name.equals("Speakers"))
		{
			img.setImageResource(R.drawable.speakers);
		}
		getActivity().setTitle(name);

		mCurrentPosition = position;
	}


	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception
	        try {
	        	mCallBack = (scheduleButton) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement scheduleButton");
	        }
	    }
	 	String checks = "0";
		private class SSHconnection_readGPIO extends AsyncTask<URL, Integer, Long>
		{

			@Override
			protected Long doInBackground(URL... arg0) {
				// TODO Auto-generated method stub
				readGPIO t = new readGPIO();
				checks = t.read();
				return null;
			}
			
			@Override
			  protected void onPostExecute(Long result) {
				 
				if(checks.equals("0"))
				{
					toggle.setChecked(false);
				}
				else
				{
					toggle.setChecked(true);
				}
				



			     }
			
		}
	  
	@Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);

	        // Save the current article selection in case we need to recreate the fragment
	        outState.putInt(ARG_POSITION, mCurrentPosition);
	    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallBack.schedule_botton_click();
	}

}
