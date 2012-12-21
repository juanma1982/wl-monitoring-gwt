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

import wlstatistics.shared.model.Domain;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface WeblogicMonitorServiceAsync {

	void ConnectToServer(String host, String port, String user,
			String password, Integer type, AsyncCallback<Void> callback);

	void getItemValue(String wl,String propertyToWatch,AsyncCallback<String> callback);

	void getJMSItemsNames(String wl,AsyncCallback<ArrayList<String>> callback);

	void isConnected(String wl,AsyncCallback<Boolean> callback);

	void getServerStatus(String wl,AsyncCallback<String> callback);

	void getServerName(String wl,AsyncCallback<String> callback);

	void getTopology(String key, AsyncCallback<Domain> asyncCallback);

	void getDomainName(String wl, AsyncCallback<String> callback);

	void saveDomain(Domain controlledDomain,
			AsyncCallback<Domain> asyncCallback);
}
