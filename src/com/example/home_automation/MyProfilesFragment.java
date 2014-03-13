//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;
import com.example.scheduler.*;
import com.example.home_automation.ModifyFragment.scheduleButton;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MyProfilesFragment extends Fragment implements OnItemSelectedListener {
	
	ClickOnHours onHours;
	
	public interface ClickOnHours{
		public void onClickListener();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

    	View myView = inflater.inflate(R.layout.activity_my_profiles, container, false);

    	setSpinnerContentonHours(myView);
    	
		
		return inflater.inflate(R.layout.activity_my_profiles, container, false);
	}
    

	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception
	        try {
	        	onHours = (ClickOnHours) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement scheduleButton");
	        }
	    }
	    
	    

    private void setSpinnerContentonHours(View view)
    {
    	Spinner spinner = (Spinner) view.findViewById(R.id.spinnerOnHour);
    	// Create an ArrayAdapter using the string array and a default spinner layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
    	        R.array.hours, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
    	spinner.setOnItemSelectedListener(this);

    	//spinner.setOnItemSelectedListener(null);
    	
    	//setSpinnerContentonHours(spinner);
    }




	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		//onHours.onClickListener();
		//Toast.makeText(arg0.getContext(), "Selected : " + arg0.getItemAtPosition(arg2), Toast.LENGTH_SHORT).show();
		System.out.println("something");
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		System.out.println("norhinf");

		
	}

} 
