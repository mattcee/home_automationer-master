//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

public class readGPIO extends connection{

	public readGPIO()
	{
		super();
	}
	public String read()
	{
		super.establishConnection();
		String onORoff = null;
		 try
		    {
			 Channel currentChannel = session.openChannel("exec");
		        ((ChannelExec) currentChannel).setCommand("gpio read 4");
			      

			        //preparing inputstream
			        currentChannel.setInputStream(null);

			        //setting channel error
			        ((ChannelExec) currentChannel).setErrStream(System.err);
			        
			        //establishing channel
			        currentChannel.connect();
					
			        //getting the input from the cat command
			        InputStream in = currentChannel.getInputStream();
			        
			        InputStreamReader isr = new InputStreamReader(in);
			        BufferedReader reader = new BufferedReader(isr);
					
					//read  line 
					onORoff = reader.readLine();

					currentChannel.connect(3*1000);
					currentChannel.disconnect();
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	      session.disconnect();

	      return onORoff;
		 
	}
	
}
