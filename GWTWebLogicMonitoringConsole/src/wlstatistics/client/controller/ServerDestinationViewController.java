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
package wlstatistics.client.controller;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import wlstatistics.client.domain.DomainManager;
import wlstatistics.client.views.ServerDestinationView;
import wlstatistics.client.views.destinationComponent.DestinationSelect;
import wlstatistics.shared.model.WLDomain;

public class ServerDestinationViewController {
	private WLDomain domain;
	private ServerDestinationView view;
	
	public ServerDestinationViewController(){
		
	}
	
	public ServerDestinationViewController(ServerDestinationView localView,ArrayList<String> items,WLDomain wlDomain){
		view = localView;
		domain = wlDomain;
		for (String itemName : items) {
			view.addDestinationCheck(itemName);
			
		}
		
	view.addButtonAdd();
	view.getButtonAdd().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			DomainManager.getDomainManager().getDomain(domain.getKey()).clearJMSDestinations();
			for (DestinationSelect dest : view.getDestinations()) {
				if (dest.isChecked())
				{
					DomainManager.getDomainManager().getDomain(domain.getKey()).addJMSDestination(dest.getName());
				}
			}
			view.hide();
			//view.removeFromParent();
			
		}
	});
	 //view.centerInPage();
	 view.show();
	 view.setHeight100();
	 view.setAutoSize(true);
	}
	
}
