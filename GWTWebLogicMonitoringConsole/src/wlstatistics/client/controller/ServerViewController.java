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

import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.event.EventManager;
import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.views.ServerDestinationView;
import wlstatistics.client.views.ServerView;
import wlstatistics.client.views.StatisticsView;
import wlstatistics.client.views.TopologyListView;
import wlstatistics.client.views.TopologyView;
import wlstatistics.shared.model.WLDomain;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ServerViewController {
	private ServerView view;
	private WLDomain controlledDomain;
	StatisticViewController statisticsViewController;
	ServerDestinationViewController destinationsController;
	WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();
	public ServerViewController(ServerView v,WLDomain domain){
		view = v;
		controlledDomain= domain;
		view.getStatisticButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadStatisticsPage();
			}
		});
		view.getReconnectButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				reconnect();
			}
		});
		view.getSelectorJmsButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popUpJMSselector();
			}

		});
		view.getTopologiaButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popUpTopologia();
			}

		});
		view.getListaTopologiaButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				popUpListaTopologia();
			}

		});
		
	}
	
	public void popUpTopologia() {
		serverCall.getTopology(controlledDomain.getKey(),new AsyncCallback<WLDomain>() {
			
			@Override
			public void onSuccess(WLDomain result) {
				TopologyView view = new TopologyView();				
				TopologyViewController topologyController = new TopologyViewController(view,controlledDomain, result);
				//EventManager.getEventManager().addPortletMainWindow(view);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Érror al obtener topología");
				
			}
		});
	}
	
	public void popUpListaTopologia() {
		serverCall.getTopology(controlledDomain.getKey(),new AsyncCallback<WLDomain>() {
			
			@Override
			public void onSuccess(WLDomain result) {
				TopologyListView viewList = new TopologyListView();				
				TopologyListViewController topologyListController = new TopologyListViewController(viewList,controlledDomain, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Érror al obtener listado de topología");
				
			}
		});
	}

	public void SetStatusOk()
	{
		view.SetServerStatusGreen();
	}
	
	public void SetStatusError()
	{
		view.SetServerStatusRed();
	}
	
	public void SetConnectionOk()
	{
		view.SetConnectionStatusGreen();
	}
	
	public void SetConnectionError()
	{
		view.SetConnectionStatusRed();
	}
	
	public void reconnect()
	{
		Window.alert("Funcionalidad no Implementada");
	}
	public void loadStatisticsPage()
	{
	StatisticsView view = new StatisticsView();
	statisticsViewController = new StatisticViewController(view, controlledDomain.getJmsDestinationSelected(), controlledDomain);	
	}
	
	private void popUpJMSselector() {
			serverCall.getJMSItemsNames(controlledDomain.getKey(),new AsyncCallback<ArrayList<String>>() {
			
			@Override
			public void onSuccess(ArrayList<String> result) {
				// Se crea el controlador de la vista StatisticViews, y se le envían todos los JMS Items a monitorear
				ServerDestinationView view = new ServerDestinationView();
				destinationsController = new ServerDestinationViewController(view, result, controlledDomain);	
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Érror al obtener destinos JMS");
				
			}
		});
	}
	public void refresh() {
		serverCall.isConnected(controlledDomain.getKey(),new AsyncCallback<Boolean>() {
			
			@Override
			public void onSuccess(Boolean result) {
				if (result)
					SetConnectionOk();
				else
					SetConnectionError();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SetConnectionError();				
			}
		});
		
		serverCall.getServerStatus(controlledDomain.getKey(),new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				if (result !=null)		
					if (result.contains("RUNNING"))
						{ 
						serverCall.getServerName(controlledDomain.getKey(),new AsyncCallback<String>() {
							
							@Override
							public void onSuccess(String result) {
								view.setServerName(result);				
							}
							
							@Override
							public void onFailure(Throwable caught) {
								 Window.alert("Error al obtener nombre de server");
								
							}
						});
						SetStatusOk();
						}
					else
						SetStatusError();
				else
					SetStatusError();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SetStatusError();
			}
		});	
		
	}
}
