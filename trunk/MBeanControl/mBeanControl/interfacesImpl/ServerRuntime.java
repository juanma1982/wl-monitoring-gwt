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
package mBeanControl.interfacesImpl;

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.IJVMRuntime;
import mBeanControl.interfaces.ISAF;
import mBeanControl.interfaces.IServer;
import mBeanControl.interfaces.IServerConfiguration;
import mBeanControl.interfaces.IThreads;
import weblogic.health.HealthState;

public class ServerRuntime implements IServer {
	private ObjectName serverRuntime;
    private String name;
    private String host;
    private Integer port;
    private String status;
    private String version;
	private HealthState health;
    
	private MBeanServerConnection connection;

	public ServerRuntime(MBeanServerConnection connection, ObjectName o) {
		this.serverRuntime = o;
		this.connection = connection;
		name ="N/A";
		host = "N/A";
		port =0;
		status="N/A";
		health=new HealthState(3);
		version="N/A";
	}

	public String getName() {
		try {
			name = (String) connection.getAttribute(serverRuntime, "Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getState() {
		try {
			status = (String) connection.getAttribute(serverRuntime, "State");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public ArrayList<IJMSServer> getIJMSServers() {
		ObjectName jmsRT;
		ObjectName[] jmsServRTs;
		ArrayList<IJMSServer> iJMSServers = new ArrayList<IJMSServer>();
		try {
			jmsRT = (ObjectName) connection.getAttribute(serverRuntime,
					"JMSRuntime");
			jmsServRTs = (ObjectName[]) connection.getAttribute(jmsRT,
					"JMSServers");

			for (ObjectName jmsServRTObject : jmsServRTs) {
				JMSServerRuntime jmsServer = new JMSServerRuntime(connection,
						jmsServRTObject);
				iJMSServers.add(jmsServer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iJMSServers;
	}

	public Integer getOpenSocketsCurrentCount() {
		try {
			return (Integer) connection.getAttribute(serverRuntime,
					"OpenSocketsCurrentCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public IJVMRuntime getIJVMRuntime() {
		try {
			ObjectName jVMRuntimeMBean = (ObjectName) connection.getAttribute(
					serverRuntime, "JVMRuntime");
			JVMRuntime jVM = new JVMRuntime(connection, jVMRuntimeMBean);
			return jVM;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public IThreads getIThreads() {
		try {
			ObjectName threadPoolRuntime = (ObjectName) connection
					.getAttribute(serverRuntime, "ThreadPoolRuntime");
			ThreadsRuntime threadsRuntime = new ThreadsRuntime(connection,
					threadPoolRuntime);
			return threadsRuntime;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public IServerConfiguration getIServerConfiguration() {
		try {
			ObjectName serverMbean = (ObjectName) connection.getAttribute(
					serverRuntime, "ServerConfiguration");
			ServerConfiguration serverConfiguration = new ServerConfiguration(
					connection, serverMbean);
			return serverConfiguration;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public IJMSServer getIJMServer(String name) {
		for(IJMSServer jMSServer: getIJMSServers()){
			if(jMSServer.getName().equals(name)){
				return jMSServer;
			}
		}
		return null;
	}

	public Boolean isAdminServer() {
		try {
			return(Boolean) connection.getAttribute(serverRuntime, "AdminServer");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getListenAddress() {
		
		try {
			host = (String) connection.getAttribute(serverRuntime, "ListenAddress");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return host;
	}

	public Integer getListenPort() {
		try {
			port = (Integer) connection.getAttribute(serverRuntime, "ListenPort");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return port;
	}

	public String getWeblogicVersion() {
		try {
			version = (String) connection.getAttribute(serverRuntime, "WeblogicVersion");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return version;
	}

	public HealthState getHealthState() {
		try {
			health = (HealthState) connection.getAttribute(serverRuntime, "HealthState");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return health;
	}


	public ISAF getSAF() {
		try {
			ObjectName sAFRuntime = (ObjectName) connection.getAttribute(
					serverRuntime, "SAFRuntime");
			SAFRuntime sAF = new SAFRuntime(
					connection, sAFRuntime);
			return sAF;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
