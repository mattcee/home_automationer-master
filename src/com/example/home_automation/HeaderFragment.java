//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.*;

public class HeaderFragment extends ListFragment {

	ListClickListener mCallBack;
	writing wCallBack;
	List<String> plug_names = new ArrayList();
	List<String> plugsFromMemory = new ArrayList();
	String[] plugs;
	Context file = getActivity(); // for writing and reading
	 String FILENAME = "saved_devices";

	
    public static ArrayAdapter<String> adapter;

	public interface ListClickListener{
		public void onPlugSelected(int position);
	}
	public interface writing{
		
		public void writeOutSavedDevices(List<String> plug_names);
	}
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	     	//new SSHconnect_read().execute(null,null,null);
	     	keepChecking();

			//System.out.print("size of array" + listOfPlugs.plug_namez.size());

	        // We need to use a different list item layout for devices older than Honeycomb
	        
        	//int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
	          //      android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
	        // Create an array adapter for the list view, using the Ipsum headlines array
	        //listOfPlugs plugs = new listOfPlugs();
	        //adapter = new ArrayAdapter<String>(getActivity(), layout, plug_names);
	       // writeOutSavedDevices();
	       // setListAdapter(adapter);
	        //System.out.println("size of arraylist" + plug_names.size());
	        //readSavedDevices();
	        
	    }

	 
	 private void keepChecking()
	 {

		 try{
			 new Thread(new Runnable()
			 {
				public void run()
				{
					while(true)
					{
				     	
						try {
							new SSHconnect_read().execute(null,null,null);
				            Log.i("Thread", "Running parallely");
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			 }).start();
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 private void splitString()
	 {
		 plugs = listOfPlugs.saved_memory_of_plugs.split("/");
	 }

	private class SSHconnect_read extends AsyncTask<URL, Integer, Long>
		{

			@Override
			protected Long doInBackground(URL... arg0) {
				// TODO Auto-generated method stub
				
				try{
					
					connectAndRead c = new connectAndRead();
					c.establishConnection();
					//listOfPlugs.plug_namez = c.readFile();
					plug_names = c.readFile();
					listOfPlugs.plug_namez  = plug_names;

				}catch(Exception e)
				{
					e.printStackTrace();
				}

				return null;
			}
			
			
			  protected void onPostExecute(Long result) {
				  int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
			                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
				  boolean test = false;
				  splitString();
				  for(String t : plugs)
				  {
					  test = plug_names.contains(t);
					  if(test == true)
					  {
						  plugsFromMemory.add(t);
						  //System.out.println(t);
					  }
				  }
			      adapter = new ArrayAdapter<String>(getActivity(), layout, plug_names);
				  adapter.notifyDataSetChanged();
			      setListAdapter(adapter);
				
			      //writeOutSavedDevices();

			     }
		}
	 


	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception
	        try {
	        	mCallBack = (ListClickListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement ListClickListener");
	        }
	        
	        try{
	        	wCallBack = (writing) activity;
	        }catch(ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement writing");
	        }
	    }


	 @Override
	 public void onStart()
	 {
		 super.onStart();
		 getActivity().setTitle("List of Plugs");


	 }
	 
	 @Override
	 public void onStop()
	 {
		 super.onStop();
		 wCallBack.writeOutSavedDevices(plug_names);

	 }

	 @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        // Notify the parent activity of selected item
		 	mCallBack.onPlugSelected(position);
	        
	    }
}
