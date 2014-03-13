//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class shaketurnon extends connection {

	public shaketurnon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void open_turon()
	{
		super.establishConnection();
		
try{
			
			channel = session.openChannel("exec");
		      ((ChannelExec)channel).setCommand("gpio write 5 0");
		      channel.connect(3*1000);
		      
		    
		      channel.disconnect();
		      Thread.sleep(500);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	try{
			
			channel = session.openChannel("exec");
		      ((ChannelExec)channel).setCommand("gpio write 5 0");
		      channel.connect(3*1000);
		      
		    
		      channel.disconnect();
		      Thread.sleep(500);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
	try{
		
		channel = session.openChannel("exec");
	      ((ChannelExec)channel).setCommand("gpio write 5 1");
	      channel.connect(3*1000);
	      
	    
	      channel.disconnect();
	      Thread.sleep(500);

	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	try{
		
		channel = session.openChannel("exec");
	      ((ChannelExec)channel).setCommand("gpio write 5 0");
	      channel.connect(3*1000);
	      
	    
	      channel.disconnect();
	      Thread.sleep(1000);

	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	try{
		
		channel = session.openChannel("exec");
	      ((ChannelExec)channel).setCommand("gpio write 5 1");
	      channel.connect(3*1000);
	      
	    
	      channel.disconnect();
	      Thread.sleep(500);

	}catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	try{
		
		channel = session.openChannel("exec");
	      ((ChannelExec)channel).setCommand("gpio write 5 0");
	      channel.connect(3*1000);
	      
	    
	      channel.disconnect();
	      Thread.sleep(500);

	}catch(Exception e)
	{
		e.printStackTrace();
	}
	

		
	      session.disconnect();

	}
}
