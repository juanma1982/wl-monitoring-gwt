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

import java.util.ArrayList;

//import com.rsa.jsafe.ag;

import mBeanControl.connection.ConnectionFactory;
import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IJMSDestination;
import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.ISAF;
import mBeanControl.interfaces.ISAFAgent;
import mBeanControl.interfaces.IServer;

public class Test {

	/**
	 * Example of usage
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String hostname = "localhost";
		String portString = "7001";
		String username = "weblogic";
		String password = "weblogic";
		
		IDomain dr = ConnectionFactory.Connect(hostname, portString, username, password,0);
		
		try {
			printServersInfo(dr.getIServers());
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void printServersInfo(ArrayList<IServer> iServers){
		for(IServer server :iServers){
			printServerInfo(server);
		}
		
	}

	public static void printServerInfo(IServer server){			
		System.out.println("Server Name: " + server.getName());
		System.out.println("Server State:" + server.getState());
		System.out.println("Is Admin Server?: " + server.isAdminServer());
		System.out.println("Listen Address: " + server.getListenAddress());
		System.out.println("Listen Port: " + server.getListenPort());
		System.out.println("Health State: " + server.getHealthState());
		System.out.println("Weblogic Version: " + server.getVersion());
		System.out.println("Open Sockets: " + 
						   server.getOpenSocketsCurrentCount());
		System.out.println("Current Heap Size: " + 
				           server.getIJVMRuntime().getHeapSizeCurrent());
		System.out.println("Thread Idle Count: " + 
				           server.getIThreads().getExecuteThreadIdleCount());
		System.out.println("Pending User Request: " + 
				           server.getIThreads().getPendingUserRequestCount());
		
		System.out.println("----------------------------------------");
		
		printSAFInfo(server.getSAF());
		printJMSServersInfo(server.getIJMSServers());
		
	}

	/**
	 * @param saf
	 */
	public static void printSAFInfo(ISAF saf) {
		System.out.println("SAF Info");
		System.out.println("Name: "+ saf.getName());
		System.out.println("Health State: " + saf.getHealthState());
		for(ISAFAgent agent : saf.getAgents()){
			printSAFAgentInfo(agent);
		}
		System.out.println("****************************");
	}

	/**
	 * @param agent
	 */
	public static void printSAFAgentInfo(ISAFAgent agent) {
		System.out.println("++++++++++");
		System.out.println("Agent Name: " + agent.getName());
		System.out.println("Health State: " + agent.getHealthState());
		System.out.println("Failed Message Total: " +agent.getFailedMessagesTotal());
		System.out.println("Message Recived Count: " + agent.getMessagesReceivedCount());
		System.out.println("Message Current Count: " + agent.getMessagesCurrentCount());
		System.out.println("++++++++++");		
	}

	public static void printJMSServersInfo(ArrayList<IJMSServer> ijmsServers) {
		for(IJMSServer jMSServer : ijmsServers){
			System.out.println("JMSServer name: " + jMSServer.getName());
			printJMSDestinationsInfo(jMSServer.getIJMSDestinations());
			
		}
		
	}

	public static void printJMSDestinationsInfo(
			ArrayList<IJMSDestination> ijmsDestinations) {
		for(IJMSDestination dest: ijmsDestinations){

			System.out.println("Destination: " + dest.getName());
			System.out.println("Messages High Count: " + dest.getMessagesHighCount());
			System.out.println("Messages Current Count: "+ dest.getMessagesCurrentCount());
			System.out.println("Consumers Total Count: "+dest.getConsumersTotalCount() );
			System.out.println("Messages Pending Count:"+ dest.getMessagesPendingCount());
		}
		System.out.println("***********************************");		
	}

}
