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

import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.event.EventManager;
import wlstatistics.client.views.DomainMonitoringView;
import wlstatistics.client.views.ServerView;
import wlstatistics.client.views.ServersView;

import com.google.gwt.user.client.ui.RootPanel;


public class MainServerViewController {
	WeblogicMonitorServiceAsync serverCall;
	ServerViewController serverViewController;
	ServersViewController serversViewController;
	//DomainMonitoringViewController domainMonitoringViewController;
	
	public MainServerViewController(WeblogicMonitorServiceAsync serverCall) {
		super();
		this.serverCall = serverCall;
	}

	public void showServerView(){
		ServerView view = new ServerView();
		RootPanel.get().add(view);		
	}
	
	public void showServersView(){
		RootPanel rootPanel = RootPanel.get();
		//rootPanel.clear();
		ServersView view = new ServersView();
		serversViewController = new ServersViewController(view);
		EventManager.getEventManager().setServersViewC(serversViewController);
		rootPanel.add(view);		
	}
	
	public void showCVDomainServersView(){
		RootPanel rootPanel = RootPanel.get();
		//rootPanel.clsear();
		//DomainMonitoringView view = new DomainMonitoringView();
		//domainMonitoringViewController = new DomainMonitoringViewController(view);
		//rootPanel.add(view);		
	}
	
	
}
