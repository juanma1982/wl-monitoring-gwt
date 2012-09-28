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
import wlstatistics.shared.model.WLDomain;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WeblogicMonitorServiceImpl extends RemoteServiceServlet implements WeblogicMonitorService {
	private HashMap<String,WLDataManager> dataManager = new HashMap<String,WLDataManager>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void ConnectToServer(String host, String port, String user,
			String password) throws Exception {
		dataManager.put(host+port, new WLDataManager(host, port, user, password));	
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
	public WLDomain getTopology(String wl) {
		
		WLDomain wLDomain = WLDomainTrans.transform( dataManager.get(wl).getDomain());
		
		return wLDomain;
//		WLDomain result = new WLDomain();
//		result.setAdminConnectionStatus("RUNNING");
//		result.setAdminHost("localhost");
//		result.setAdminPort("7001");
//		result.setName("AdminServer");
//		for (int i=0 ; i<5; i++)
//		{
//			WLServer server = new WLServer();
//			server.setServerStatus("RUNNING");
//			//server.setServerStatus("STOP");
//			
//			server.setHost("localhost");
//			server.setName("Man_"+i);
//			server.setPort("720"+i);
//			server.setVersion("10.3");
//			result.addManagedServers(server);
//		}
//		WLServer server = new WLServer();
//		server.setServerStatus("STOP");
//		server.setHost("localhost");
//		server.setName("Man_"+6);
//		server.setPort("720"+6);
//		server.setVersion("10.3");
//		result.addManagedServers(server);
//		
//		return result;
	}
	
	
	
}
