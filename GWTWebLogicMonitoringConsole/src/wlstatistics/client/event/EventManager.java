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
package wlstatistics.client.event;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.Portlet;

import wlstatistics.client.controller.ServersViewController;
import wlstatistics.shared.model.WLDomain;

public class EventManager {
	private static EventManager instance;
	private ServersViewController serversViewC;
	private EventManager() {

	}

	public static EventManager getEventManager() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}

	public void addedDomainEvent(WLDomain domain) {
		serversViewC.addServerView(domain);
	}
	
	public void registerDrageableWindow(Window window){
		window.setDragTarget(serversViewC.getDragDestination());
	}
	
	public ServersViewController getServersViewC() {
		return serversViewC;
	}

	public void setServersViewC(ServersViewController serversViewC) {
		this.serversViewC = serversViewC;
	}
	
	public void addPortletMainWindow(Portlet portlet){
		this.serversViewC.addPortlet(portlet);
	}
	
	public void removedDomainEvent(String key) {
		serversViewC.removeServerView(key);
	}
	
}
