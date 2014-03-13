//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
        	
        	System.out.println("Reciever working");
        }

		
	}

}
