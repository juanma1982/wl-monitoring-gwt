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
import mBeanControl.interfacesImpl.DomainRuntime;


/**
 * This class let you manage the connections with diferents weblogics
 * 
 * @author jbrasca
 *
 */
public class ConnectionFactory {
	   private static JMXConnector connector;
	   private static Map<String,DomainRuntime> connections;
	  
	   /**
	    * This method returns an interface of a given domain, it works like a singleton 
	    * for each domain.
	    * 
	    * @param hostname  
	    * @param portString
	    * @param username
	    * @param password
	    * @return IDomain 
	    */
	   public static IDomain Connect(String hostname, String portString, String username, String password){
		   if(connections == null){
			   connections = new HashMap<String, DomainRuntime>();
		   }
		   String key = hostname+portString+username+password;
		   if(connections.containsKey(key)){
			  return connections.get(key);
		   }
		   else{
			   DomainRuntime dr = null;
			try {
				dr = initConnection(hostname, portString, username, password);
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
	    * Initialize connection and returns the Domain Runtime MBean Server
	    * 
	    * @param hostname
	    * @param portString
	    * @param username
	    * @param password
	    * @return
	    * @throws IOException
	    * @throws MalformedURLException
	    */
	   private static DomainRuntime initConnection(String hostname, String portString, String username, String password) throws IOException, MalformedURLException {
		   MBeanServerConnection connection;
		   ObjectName domainRuntimeMBean;
		   try {
			   domainRuntimeMBean = new ObjectName("com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
	       }catch (MalformedObjectNameException e) {
	         throw new AssertionError(e.getMessage());
	       }
		   
	      String protocol = "t3";
	      Integer portInteger = Integer.valueOf(portString);
	      int port = portInteger.intValue();
	      String jndiroot = "/jndi/";
	      String mserver = "weblogic.management.mbeanservers.domainruntime";
	      JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
	      Hashtable<String, String> h = new Hashtable<String, String>();
	      h.put(Context.SECURITY_PRINCIPAL, username);
	      h.put(Context.SECURITY_CREDENTIALS, password);
	      h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
	      connector = JMXConnectorFactory.connect(serviceURL, h);
	      connection = connector.getMBeanServerConnection();
	      
	      return new DomainRuntime(connection,domainRuntimeMBean);
	   }
}
