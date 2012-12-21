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
package wlstatistics.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import mBeanControl.connection.ConnectionFactory;
import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IJMSDestination;
import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.IServer;
import mBeanControl.log.DefaultLogger;

public class WLDataManager {
	private static final String jmsDestination = "jmsDestination";
	private static final String domain = "domain";
	private static final String adminServer = "AdminServer";
	private IDomain domianRuntime;
	private String adminHost;
	private String adminPort;
	private String userAdmin;
	private String userPass;
	private HashMap<String, IJMSDestination> jmsDestinations = new HashMap<String, IJMSDestination>();
	private Integer type;

	public WLDataManager(String host, String port, String user, String password,Integer typeServer) {
		adminHost = host;
		adminPort = port;
		userAdmin  = user;
		userPass = password;
		type = typeServer;
		connect();
	}

	private void connect()
	{
		this.domianRuntime = ConnectionFactory.Connect(adminHost, adminPort, userAdmin,
				userPass,type);
	}
	
	public void closeConnection(){
		try {
			ConnectionFactory.closeConnection();
			this.domianRuntime = null;
		} catch (IOException e) {		
			DefaultLogger.NotFoundObjectLog(domain, this.getClass(), e.getMessage());
		}
	}
	
	public ArrayList<String> setMonitoreableJMSDestinations() {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<IServer> servers;
		try {
			servers = this.domianRuntime.getIServers();
			for (IServer iServer : servers) {
				ArrayList<IJMSServer> jmsServes = iServer.getIJMSServers();
				for (IJMSServer ijmsServer : jmsServes) {
					ArrayList<IJMSDestination> jmsDest = ijmsServer
							.getIJMSDestinations();
					for (IJMSDestination jmsDestination : jmsDest) {
						jmsDestinations.put(jmsDestination.getName(),
								jmsDestination);
						result.add(jmsDestination.getName());
					}
				}
			}
		} catch (ObjectNotFoundException e) {			
			DefaultLogger.NotFoundObjectLog(jmsDestination, this.getClass(), e.getMessage());
		}
		return result;
	}

	public String getStatisticForJMSByName(String name) {
		return jmsDestinations.get(name).getMessagesCurrentCount().toString();
	}

	public Boolean isConnected() {
			try {
				if (domianRuntime==null)
					return false;
				else			
				if (domianRuntime.getIServers()==null)
					return false;
				else
					return true;
			} catch (ObjectNotFoundException e) {
				DefaultLogger.NotFoundObjectLog(domain, this.getClass(), e.getMessage());
			}
		return false;	
	}

	public String getAdminServerStatus() {
		try {
			return domianRuntime.getIServers().get(0).getState();
		} catch (ObjectNotFoundException e) {
			DefaultLogger.NotFoundObjectLog(domain, this.getClass(), e.getMessage());
		}
		return "ERROR";
	}

	public String getAdminServerName() {
		try {
			return domianRuntime.getIServers().get(0).getName();
		} catch (ObjectNotFoundException e) {
			DefaultLogger.NotFoundObjectLog(adminServer, this.getClass(), e.getMessage());
		}
		return "ERROR";
	}
	
	public String getDomainName() {
		try {
			return this.domianRuntime.getName();
		} catch (ObjectNotFoundException e) {
			DefaultLogger.NotFoundObjectLog(adminServer, this.getClass(), e.getMessage());
		}
		return "ERROR";
	}
	
	public IDomain getDomain(){
		if(this.domianRuntime == null)
			connect();
		return this.domianRuntime;
	}
}
