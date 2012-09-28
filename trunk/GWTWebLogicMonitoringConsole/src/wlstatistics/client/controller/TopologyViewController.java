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
import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.views.TopologyView;
import wlstatistics.shared.model.WLDomain;
import wlstatistics.shared.model.WLServer;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TopologyViewController {
	private TopologyView view;
	private WLDomain controlledDomain;
	private WLDomain topologyResult;
	WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();
	
	public TopologyViewController(TopologyView view,WLDomain domain,WLDomain result){
		this.view = view;
		this.controlledDomain = domain;
		this.topologyResult = result;
		setViewData();
		Timer t = new Timer() {
		      public void run() {
		    	  refreshConnection();
		      };};
		      t.scheduleRepeating(5000);
		this.view.getOkButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hidePopUp();
			}
		});
		
		view.getRefresh().addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
            		refreshConnection();
            }
		});	
		view.setAutoSize(true);
		view.setCanDragResize(true); 
		view.show();
	}
	
	protected void refreshConnection() {
		serverCall.getTopology(controlledDomain.getKey(),new AsyncCallback<WLDomain>() {
			
			@Override
			public void onSuccess(WLDomain result) {
				topologyResult = result;
				view.setClusterStatusRed();
				view.setFinalSize();
				setViewData();
					}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al obtener topologia");
				
			}
		});
	}

	protected void setViewData() {
		view.getAdminServercomp().setStatusGreen();
		view.getAdminServercomp().setServerName(topologyResult.getName());
		view.getAdminServercomp().setServerUrl(topologyResult.getAdminHost()+":"+topologyResult.getAdminPort());
		view.removerServersComponents();	
		if (topologyResult.getManagedServers().size() > 0)
			for (WLServer server : topologyResult.getManagedServers()) {
				this.view.addServerComponent(server);
			}
		else
			if (topologyResult.getAdminServerStatus().contains("HEALTH_OK")) //TODO: ver de dejar la logica unificada.
				view.setClusterStatusGreen();
			else
				view.setClusterStatusRed();
			
		view.redraw();
	}

	public void hidePopUp(){
		this.view.hide();
	}
}
