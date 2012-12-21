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
package mBeanControl.interfacesImpl.weblogic;

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.interfaces.IJDBCRuntimeService;
import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.IJVMRuntime;
import mBeanControl.interfaces.ISAF;
import mBeanControl.interfaces.IServer;
import mBeanControl.interfaces.IServerConfiguration;
import mBeanControl.interfaces.IThreads;
import mBeanControl.log.DefaultLogger;
import weblogic.health.HealthState;
import weblogic.management.runtime.ServerRuntimeMBean;

public class ServerRuntime implements IServer {
	private static final String listenPort = "ListenPort";
	private static final String NA = "N/A";
    private static final String nameString = "Name";
    private static final String stateString = "State";
    private static final String jmsRuntime ="JMSRuntime";
    private static final String jmsServers = "JMSServers";
    private static final String openSocketsCurrentCount="OpenSocketsCurrentCount";
    private static final String jvmRuntime="JVMRuntime";
    private static final String threadPoolRuntimeString="ThreadPoolRuntime";
    private static final String serverConfiguration="ServerConfiguration";
    private static final String adminServer="AdminServer";
    private static final String listenAddress="ListenAddress";
	private static final String weblogicVersion = "WeblogicVersion";
	private static final String healthState = "HealthState";
	private static final String safRuntimeString = "SAFRuntime";
	private static final String jdbcServiceRuntime = "JDBCServiceRuntime";
    
    
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
		name =NA;
		host = NA;
		port =0;
		status=NA;
		health=new HealthState(3);
		version=NA;
	}

	public String getName() {
		try {
			name = (String) connection.getAttribute(serverRuntime, nameString);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(nameString,this.getClass(),e.getMessage());	
		}
		return name;
	}

	public String getState() {
		try {
			status = (String) connection.getAttribute(serverRuntime, stateString);

		} catch (Exception e) {
			status = "DOWN";
			DefaultLogger.NotFoundObjectAttributeLog(stateString,this.getClass(),e.getMessage());	
		}
		return status;
	}

	public ArrayList<IJMSServer> getIJMSServers() {
		ObjectName jmsRT;
		ObjectName[] jmsServRTs;
		ArrayList<IJMSServer> iJMSServers = new ArrayList<IJMSServer>();
		try {
			jmsRT = (ObjectName) connection.getAttribute(serverRuntime,
					jmsRuntime);
			jmsServRTs = (ObjectName[]) connection.getAttribute(jmsRT,
					jmsServers);

			for (ObjectName jmsServRTObject : jmsServRTs) {
				JMSServerRuntime jmsServer = new JMSServerRuntime(connection,
						jmsServRTObject);
				iJMSServers.add(jmsServer);
			}
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(jmsRuntime,this.getClass(),e.getMessage());	
		}
		return iJMSServers;
	}

	public Integer getOpenSocketsCurrentCount() {
		try {
			return (Integer) connection.getAttribute(serverRuntime,
					openSocketsCurrentCount);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(openSocketsCurrentCount,this.getClass(),e.getMessage());
		}
		return 0;
	}

	public IJVMRuntime getIJVMRuntime() {
		try {
			ObjectName jVMRuntimeMBean = (ObjectName) connection.getAttribute(
					serverRuntime, jvmRuntime);
			JVMRuntime jVM = new JVMRuntime(connection, jVMRuntimeMBean);
			return jVM;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(openSocketsCurrentCount,this.getClass(),e.getMessage());
		}

		return null;
	}

	public IThreads getIThreads() {
		try {
			ObjectName threadPoolRuntime = (ObjectName) connection
					.getAttribute(serverRuntime, threadPoolRuntimeString);
			ThreadsRuntime threadsRuntime = new ThreadsRuntime(connection,
					threadPoolRuntime);
			return threadsRuntime;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(threadPoolRuntimeString,this.getClass(),e.getMessage());
		}

		return null;
	}

	public IServerConfiguration getIServerConfiguration() {
		try {
			ObjectName serverMbean = (ObjectName) connection.getAttribute(
					serverRuntime, serverConfiguration);
			ServerConfiguration serverConfiguration = new ServerConfiguration(
					connection, serverMbean);
			return serverConfiguration;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(serverConfiguration,this.getClass(),e.getMessage());
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
			return(Boolean) connection.getAttribute(serverRuntime, adminServer);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(adminServer,this.getClass(),e.getMessage());
		}

		return false;
	}

	public String getListenAddress() {
		
		try {
			host = (String) connection.getAttribute(serverRuntime, listenAddress);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(listenAddress,this.getClass(),e.getMessage());
		}

		return host;
	}

	public Integer getListenPort() {
		try {
			port = (Integer) connection.getAttribute(serverRuntime, listenPort);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(listenPort,this.getClass(),e.getMessage());
		}

		return port;
	}

	public String getVersion() {
		try {
			version = (String) connection.getAttribute(serverRuntime, weblogicVersion);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(weblogicVersion,this.getClass(),e.getMessage());
		}

		return version;
	}

	public String getHealthState() {
		try {
			health = (HealthState) connection.getAttribute(serverRuntime, healthState);
			System.out.println(((ServerRuntimeMBean)serverRuntime).getHealthState().getState());
		} catch (Exception e) {
			health=new HealthState(3);
			DefaultLogger.NotFoundObjectAttributeLog(healthState,this.getClass(),e.getMessage());	
		}
		String healthString = null;
		switch (health.getState()) {
			case HealthState.HEALTH_OK:
				healthString = "OK";
				break;
			case HealthState.HEALTH_FAILED:
				healthString = "FAILED";
				break;
			case HealthState.HEALTH_CRITICAL:
				healthString = "CRITICAL";
				break;
			case HealthState.HEALTH_OVERLOADED:
				healthString = "OVERLOADED";
				break;
			case HealthState.HEALTH_WARN:
				healthString = "WARNING";
				break;
			}
		return healthString;		
	}
		

	public ISAF getSAF() {
		try {
			ObjectName sAFRuntime = (ObjectName) connection.getAttribute(
					serverRuntime, safRuntimeString);
			SAFRuntime sAF = new SAFRuntime(
					connection, sAFRuntime);
			return sAF;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(safRuntimeString,this.getClass(),e.getMessage());	
		}

		return null;
	}

	@Override
	public IJDBCRuntimeService getIJDBCRuntimeService() {
		try {
			ObjectName jdbcRuntime = (ObjectName) connection.getAttribute(
					serverRuntime, jdbcServiceRuntime);
			JDBCRuntimeService jdbcRuntimeWL = new JDBCRuntimeService(
					connection, jdbcRuntime);
			return jdbcRuntimeWL;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(jdbcServiceRuntime,this.getClass(),e.getMessage());	
		}

		return null;
	}

}
