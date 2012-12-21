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

import java.util.ArrayList;
import java.util.HashMap;

import wlstatistics.client.WeblogicMonitorService;
import wlstatistics.shared.model.Domain;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WeblogicMonitorServiceImpl extends RemoteServiceServlet implements WeblogicMonitorService {
	private HashMap<String,WLDataManager> dataManager = new HashMap<String,WLDataManager>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void ConnectToServer(String host, String port, String user,
			String password,Integer serverType) throws Exception {
		dataManager.put(host+port, new WLDataManager(host, port, user, password,serverType));	
	}

	@Override
	public String getItemValue(String wl,String propertyToWatch) {
		return dataManager.get(wl).getStatisticForJMSByName(propertyToWatch);
	}

	@Override
	public ArrayList<String> getJMSItemsNames(String wl) {
		return dataManager.get(wl).setMonitoreableJMSDestinations();
	}

	@Override
	public Boolean isConnected(String wl) {
		return dataManager.get(wl).isConnected();
	}

	@Override
	public String getServerStatus(String wl) {
		return dataManager.get(wl).getAdminServerStatus();
	}

	@Override
	public String getServerName(String wl) {
		return dataManager.get(wl).getAdminServerName();
	}

	@Override
	public String getDomainName(String wl) {
		return dataManager.get(wl).getDomainName();
	}
	
	public ArrayList<Domain> getMonitoreableDomains(){
		return PropertiesReader.getDomains();
	}
	
	@Override
	public Domain getTopology(String wl) {
		
		Domain wLDomain = WLDomainTrans.transform( dataManager.get(wl).getDomain());	
		return wLDomain;
	}

	@Override
	public Domain saveDomain(Domain controlledDomain) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
