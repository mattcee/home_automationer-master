//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class turnoff extends connection{
	
	public turnoff()
	{
		super();
	}
	public void open_turnoff()
	{
		super.establishConnection();

		 try
		    {
		    	channel = session.openChannel("exec");
			      ((ChannelExec)channel).setCommand("gpio write 4 0");
			      channel.connect(3*1000);
			      channel.disconnect();
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	      session.disconnect();

		 
	}

}
