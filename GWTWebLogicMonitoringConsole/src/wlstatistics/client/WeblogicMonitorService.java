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
package wlstatistics.client;

import java.util.ArrayList;

import wlstatistics.shared.model.WLDomain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("WeblogicMonitorService")
public interface WeblogicMonitorService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class UtilGWT {
		private static WeblogicMonitorServiceAsync instance;
		public static WeblogicMonitorServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(WeblogicMonitorService.class);
			}
			return instance;
		}
	}

	public void ConnectToServer(String host,String port, String user, String password) throws Exception;
	public String getItemValue(String wl,String propertyToWatch);
	public ArrayList<String> getJMSItemsNames(String wl);
	public Boolean isConnected(String wl);
	public String getServerStatus(String wl);
	public String getServerName(String wl);
	public WLDomain getTopology(String key);
}
