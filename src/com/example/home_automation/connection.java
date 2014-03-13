//  Created by Matthew cai on 3/8/14.
//  Copyright (c) 2014 Matthew cai. All rights reserved.
//
package com.example.home_automation;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class connection {

	int status;//0 for off 1 for on
	Session session;
	Channel channel;
	
	public connection(int status)
	{
		this.status = status;
	}
	public connection()
	{
		
	}
	
	//establish the connection to the rpi
	protected void establishConnection()
	{
		try
		{
			JSch jsch = new JSch();
			String host = List_of_rpi.ip_address;
			String user = List_of_rpi.host_name;
			//System.out.println(host);
			String password = List_of_rpi.password;
			//setup the session
			 session = jsch.getSession(user, host, 22);
			session.setPassword(password);
		     session.setConfig("StrictHostKeyChecking", "no");
			session.connect(30000);
			
			if(session.isConnected())
			{
				System.out.println("we connected bro");
			}
			//setting the channel to output
			channel = session.openChannel("exec");
	        ((ChannelExec)channel).setCommand("gpio mode 4 out");
		      channel.connect(3*1000);
		      channel.disconnect();
		      
		      
		/*      if(status == 0)
		      {
		    	  turnOff();
		      }
		      if(status == 1)
		      {
		    	  turnOn();
		    	  
		      }
		     */


		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//turning on the device
	public void turnOn()
	{
		   //for turn on
		try{
			
			channel = session.openChannel("exec");
		      ((ChannelExec)channel).setCommand("gpio write 4 1");
		      channel.connect(3*1000);
		      channel.disconnect();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	//turning off the device
	public void turnOff()
	{
		//useful for turn off
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
	}
	
	private void disconnect()
	{
		session.disconnect();
	}
	
	
}
