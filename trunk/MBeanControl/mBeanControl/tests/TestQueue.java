/*
 * Copyright 2011 Juan Cruz Basso
 * juancruzbasso@gmail.com
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package mBeanControl.tests;

import mBeanControl.connection.ConnectionFactory;
import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IJMSDestination;
import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.IServer;

public class TestQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hostname = "localhost";
		String portString = "7001";
		String username = "weblogic";
		String password = "weblogic";
		
		IDomain dr = ConnectionFactory.Connect(hostname, portString, username, password,0);
		
		IServer server;
		try {
			server = dr.getIServer("AdminServer");
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println(server.getName());
		IJMSServer jMSServer = server.getIJMServer("LocalJMSServer");
		System.out.println(jMSServer.getName());
		IJMSDestination dest = jMSServer.getIJDestination("SystemModule-0!LocalQueue");
		System.out.println(dest.getMessagesHighCount());		
	}

}
