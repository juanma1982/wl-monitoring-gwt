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
package mBeanControl.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import mBeanControl.interfaces.IDomain;
import mBeanControl.interfacesImpl.weblogic.DomainRuntime;

/**
 * This class let you manage the connections with diferents weblogics
 * 
 * @author jbrasca
 * 
 */
public class ConnectionFactory {
	private static JMXConnector connector;
	private static Map<String, IDomain> connections;

	/**
	 * This method returns an interface of a given domain, it works like a
	 * singleton for each domain.
	 * 
	 * @param hostname
	 * @param portString
	 * @param username
	 * @param password
	 * @return IDomain
	 */
	public static IDomain Connect(String hostname, String portString,
			String username, String password,Integer type) {
		if (connections == null) {
			connections = new HashMap<String, IDomain>();
		}
		String key = hostname + portString + username + password;
		if (connections.containsKey(key)) {
			return connections.get(key);
		} else {
			IDomain dr = null;
			try {
				if(type==0)
					dr = initWeblogicConnection(hostname, portString, username, password);
				if(type==1)
					dr = initJBossConnection(hostname, portString, username, password);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connections.put(key, dr);
			return dr;
		}
	}
	/**
	 *  Initialize connection and returns the Jboss Domain Runtime MBean Server
	 *  
	 * @param hostname
	 * @param portString
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException 
	 */	
	private static IDomain initJBossConnection(String hostname,
			String portString, String username, String password) throws IOException {

		String urlString = "service:jmx:rmi://" + hostname + "/jndi/rmi://"+hostname+":" + portString+"/jmxconnector";

		String webClusterObjectName="jboss.web:type=Server";   
		JMXServiceURL serviceURL = new JMXServiceURL(urlString);

		Hashtable<String, String[]> h = new Hashtable<String, String[]>();
		String[] credentials = new String[] { username, password };
		h.put("jmx.remote.credentials", credentials);

		connector = JMXConnectorFactory.connect(serviceURL,h);
		MBeanServerConnection connection = connector.getMBeanServerConnection();
		ObjectName domainRuntimeMBean;
		try {
			domainRuntimeMBean = new ObjectName(webClusterObjectName);
		} catch (MalformedObjectNameException e) {
			throw new AssertionError(e.getMessage());
		}

		return new mBeanControl.interfacesImpl.jboss.DomainRuntime(domainRuntimeMBean,connection);
	}

	/**
	 * Initialize connection and returns the weblogic Domain Runtime MBean Server
	 * 
	 * @param hostname
	 * @param portString
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private static IDomain initWeblogicConnection(String hostname,
			String portString, String username, String password)
			throws IOException, MalformedURLException {
		MBeanServerConnection connection;
		ObjectName domainRuntimeMBean;
		try {
			domainRuntimeMBean = new ObjectName(
					"com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
		} catch (MalformedObjectNameException e) {
			throw new AssertionError(e.getMessage());
		}

		String protocol = "t3";
		Integer portInteger = Integer.valueOf(portString);
		int port = portInteger.intValue();
		String jndiroot = "/jndi/";
		String mserver = "weblogic.management.mbeanservers.domainruntime";
		JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port,
				jndiroot + mserver);
		Hashtable<String, String> h = new Hashtable<String, String>();
		h.put(Context.SECURITY_PRINCIPAL, username);
		h.put(Context.SECURITY_CREDENTIALS, password);
		h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
				"weblogic.management.remote");
		connector = JMXConnectorFactory.connect(serviceURL, h);
		connection = connector.getMBeanServerConnection();

		return new DomainRuntime(connection, domainRuntimeMBean);
	}

	public static void closeConnection() throws IOException {
		connector.close();
	}
}
