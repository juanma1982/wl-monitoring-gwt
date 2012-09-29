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
package wlstatistics.shared.model;

import java.io.Serializable;
import java.util.ArrayList;

public class WLDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String adminServerName;
	private String adminHost;
	private String adminPort;
	private String user;
	private String password;
	private String adminServerStatus;
	private String adminConnectionStatus;
	private String adminHealth;
	private ArrayList<String> jmsDestinationSelected = new ArrayList<String>();
	private ArrayList<WLServer> managedServers = new ArrayList<WLServer>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getJmsDestinationSelected() {
		return jmsDestinationSelected;
	}
	public String getAdminHost() {
		return adminHost;
	}
	public void setAdminHost(String host) {
		this.adminHost = host;
	}
	public String getAdminPort() {
		return adminPort;
	}
	public void setAdminPort(String port) {
		this.adminPort = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminServerStatus() {
		return adminServerStatus;
	}
	public void setAdminServerStatus(String serverStatus) {
		this.adminServerStatus = serverStatus;
	}
	public String getAdminConnectionStatus() {
		return adminConnectionStatus;
	}
	public void setAdminConnectionStatus(String connectionStatus) {
		this.adminConnectionStatus = connectionStatus;
	}
	public String getKey() {
		return this.adminHost+this.adminPort;
	}
	
	public void addManagedServers(WLServer server){
		this.managedServers.add(server);
	}
	public void removeManagedServers(WLServer server){
		this.managedServers.remove(server);
	}
	public void clearManagedServers() {
		this.jmsDestinationSelected.clear();	
	}
	
	public void addJMSDestination(String name){
		this.jmsDestinationSelected.add(name);
	}
	public void removeJMSDestination(String name){
		this.jmsDestinationSelected.remove(name);
	}
	public void clearJMSDestinations() {
		this.jmsDestinationSelected.clear();	
	}
	public ArrayList<WLServer> getManagedServers() {
		return this.managedServers;
	}
	public void setAdminHealth(String health) {
		adminHealth = health;
	}
	
	public String getAdminHealth() {
		return adminHealth;
	}
	public String getAdminServerName() {
		return adminServerName;
	}
	public void setAdminServerName(String adminServerName) {
		this.adminServerName = adminServerName;
	}
	
}
