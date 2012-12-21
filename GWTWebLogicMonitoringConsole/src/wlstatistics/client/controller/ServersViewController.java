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

import wlstatistics.client.domain.DomainManager;
import wlstatistics.client.views.ServerView;
import wlstatistics.client.views.ServersView;
import wlstatistics.shared.model.Domain;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.Portlet;

public class ServersViewController {
	private ServersView view;
	
	public ServersViewController(ServersView viewR) {
		view = viewR;
		Timer t = new Timer() {
		      public void run() {
		    	  refreshServersViews();
		      };};
		t.scheduleRepeating(5000);
		view.draw();
	}
	
	public void refreshServersViews(){
		for (String key : view.serversViewControllers.keySet()) {
			view.serversViewControllers.get(key).refresh();
		}
	}
	
	public void addServerView(Domain domain){
		ServerView auxView = new ServerView();		
		ServerViewController auxController = new ServerViewController(auxView,domain);
		view.serversViewControllers.put(domain.getKey(),auxController);
		view.serversPanel.addPortlet(auxView,0,DomainManager.getDomainManager().getDomainCount());	
	}

	public void addPortlet(Portlet portlet){
		view.serversPanel.addPortlet(portlet,0,0);
	}
	public void removeServerView(String key){
		view.serversViewControllers.remove(key);
	}
	
	public Canvas getDragDestination(){
		return view.serversPanel;
	}
}
