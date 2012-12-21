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

public class WLServer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String host;
	private String port;
	private String version;
	private String serverStatus;
	private String connectionStatus;
	private String health;
	private ArrayList<WLDatasource> datasources;
	private static String RUNNING = "RUNNING";
	private static String DOWN = "DOWN";
	
	public WLServer(){
		name ="";
		host ="";
		port="";
		version="";
		serverStatus="";
		connectionStatus="";
		health="";
		datasources= new ArrayList<WLDatasource>();
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}
	public String getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isRunning(){
		if (serverStatus.contains(RUNNING))
			return true;
		else
			return false;
	}
	
	public boolean isOk(){
		if (health.contains("OK"))
			return true;
		else
			return false;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	
	public void addDatasource (WLDatasource datasource){
		datasources.add(datasource);
	}
	public ArrayList<WLDatasource> getDatasources() {
		return datasources;
	}
	public void setDatasources(ArrayList<WLDatasource> datasources) {
		this.datasources = datasources;
	}

	public String getErrorStatus() {
		return DOWN;
	}

	
}