//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;

public class connectAndRead extends connection{
	//temp string maybe static later 
	String remoteFilename = "result.txt";

	public connectAndRead()
	{
		super();
	}
	public List<String> readFile()
	{
		ArrayList<String> plug_names = new ArrayList();
		
		try
		{
			//creating cat string
			String command = "cat " + remoteFilename;
			
			//opening channel
	        Channel currentChannel = session.openChannel("exec");
	        ((ChannelExec) currentChannel).setCommand(command);

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
			String line = null;
			
			//read each line 
			while((line = reader.readLine()) != null)
			{
				//want to allow it to print screen.
				
				//save connected devices to phone memory
				plug_names.add(line);
			}
			

			
			//disconnect when done
			currentChannel.disconnect();
		    session.disconnect();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return plug_names;
	}

}
