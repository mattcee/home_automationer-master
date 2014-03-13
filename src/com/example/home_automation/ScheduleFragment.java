//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import com.example.home_automation.HeaderFragment.ListClickListener;
import com.example.home_automation.ModifyFragment.scheduleButton;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class ScheduleFragment extends Fragment implements OnClickListener{
	
	Button myButton;
	
	currentSchedule mCallBack;
	
	public interface currentSchedule
	{
		public void currentSchedule_button();
	}
	

	   @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) {
		   
		   View myView = inflater.inflate(R.layout.activity_schedule, container, false);
		   myButton = (Button) myView.findViewById(R.id.button2);
		   myButton.setOnClickListener(this);
		   
			return myView;
		}


		 @Override
		    public void onAttach(Activity activity) {
		        super.onAttach(activity);
		        
		        // This makes sure that the container activity has implemented
		        // the callback interface. If not, it throws an exception
		        try {
		        	mCallBack = (currentSchedule) activity;
		        } catch (ClassCastException e) {
		            throw new ClassCastException(activity.toString()
		                    + " must implement currentSchedule");
		        }
		    }

	   
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCallBack.currentSchedule_button();
		
	}



	
}
