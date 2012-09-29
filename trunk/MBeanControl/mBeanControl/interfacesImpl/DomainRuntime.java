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
import java.util.HashMap;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IServer;

/**
 * 
 * @author jbrasca
 * @date Aug 2, 2011
 */
public class DomainRuntime implements IDomain  {
	private  MBeanServerConnection connection;
	private  final ObjectName domainRuntimeMBean;
	private HashMap<String,ServerRuntime> serverRuntimes;
	private IServer adminServer;
	private HashMap<String,IServer> managedServers;
		
	
	/**
	 * This constructor expects the connection and a domainRuntimeMBean
	 * 
	 * @param connection
	 * @param domainRuntimeMBean
	 */
	public DomainRuntime(MBeanServerConnection connection,ObjectName domainRuntimeMBean){
		this.connection = connection;
		this.domainRuntimeMBean = domainRuntimeMBean;
		this.managedServers = new HashMap<String,IServer>();
		this.serverRuntimes = new HashMap<String,ServerRuntime>();
		
	}
	

	/**
	 * This method query for the attribute "ServerName" of the domainRuntimeMBean
	 * 
	 * @return The name of the server
	 * @throws ObjectNotFoundException 
	 */
	public String getAdminServerName() throws ObjectNotFoundException {
		try {
			return (String) connection.getAttribute(domainRuntimeMBean, "ServerName");
		} catch (Exception e) {
			throw new ObjectNotFoundException(e);
		} 
	}
	
	/**
	 * This method query for the attribute "Name" of the domainRuntimeMBean
	 * 
	 * @return The name of the server
	 * @throws ObjectNotFoundException 
	 */
	public String getName() throws ObjectNotFoundException {
		try {
			
			ObjectName domainMBean = (ObjectName) connection.getAttribute(domainRuntimeMBean, "DomainConfiguration");
			return (String) connection.getAttribute(domainMBean, "Name");
			//return (String) connection.getAttribute(domainRuntimeMBean, "Name");
		} catch (Exception e) {
			throw new ObjectNotFoundException(e);
		} 
	}
	
	/**
	 * This method query for the attribute "ServerRuntimes" of the domainRuntimeMBean
	 * 
	 * @return an ArrayList of IServer interface
	 * @throws ObjectNotFoundException 
	 */
	public ArrayList<IServer> getIServers() throws ObjectNotFoundException {
		
		ArrayList<IServer> iServers =  new ArrayList<IServer>();		
		for(String key: getServers().keySet()){
			IServer server = this.serverRuntimes.get(key);
			iServers.add(server);			
		}
		return iServers;
	}

	
	/**
	 * This method query for the attribute "ServerRuntimes" of the domainRuntimeMBean
	 * and search for the name
	 * 
	 * @param name 
	 * @return the expected IServer
	 * @throws ObjectNotFoundException 
	 */
	public IServer getIServer(String name) throws ObjectNotFoundException {
		
		for(String key: getServers().keySet()){
			IServer server = this.serverRuntimes.get(key);
			if(server.getName().equals(name)){
				return server;
			}
		}
		
		return null;
	}
	
	//Private methods
	private ObjectName[] getServerRuntimes() throws Exception {
		return (ObjectName[]) connection.getAttribute(domainRuntimeMBean,
		"ServerRuntimes");
	}
	
	private HashMap<String,ServerRuntime> getServers() throws ObjectNotFoundException{
		//if(serverRuntimes == null){
			try {
				for(ObjectName o :getServerRuntimes()){
					ServerRuntime serverRuntime = new ServerRuntime(connection, o);
					serverRuntimes.put(serverRuntime.getName(),serverRuntime);
				}
			} catch (Exception e) {
				throw new ObjectNotFoundException(e);
			}
		//}
		return serverRuntimes;
	}


	public IServer getAdminServer() throws ObjectNotFoundException {
		if(this.adminServer == null){
			for(String key: getServers().keySet()){
				IServer server = this.serverRuntimes.get(key);
				if(server.isAdminServer()){
					this.adminServer = server;
					return server;
				}
			}
		}
		return this.adminServer;
	}


	public ArrayList<IServer> getManagedServers() throws ObjectNotFoundException {		
		ArrayList<IServer> result = new ArrayList<IServer>();
		for(String key: getServers().keySet()){
			IServer server = this.serverRuntimes.get(key);
			if(!server.isAdminServer()){
				this.managedServers.put(key,server);
				result.add(server);
			}
		}
		
		return result;
	}
}
