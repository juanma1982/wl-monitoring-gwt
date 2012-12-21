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

import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.resources.ResourcesManager;
import wlstatistics.client.views.TopologyListView;
import wlstatistics.shared.model.WLDatasource;
import wlstatistics.shared.model.Domain;
import wlstatistics.shared.model.WLServer;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

public class TopologyListViewController {
	
	private TopologyListView view;
	private static String dominio = "Dominio";
	private static String puntos = ":";
	private Domain controlledDomain;
	private Domain topologyResult;
	WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();
	
	public TopologyListViewController(TopologyListView view,Domain domain,Domain result){
		this.view = view;
		this.controlledDomain = domain;
		this.topologyResult = result;
		refreshConnection();
		Timer t = new Timer() {
		      public void run() {
		    	  refreshConnection();
		      };};
		      t.scheduleRepeating(30000);
		view.setLeft(10);
		view.setRight(1000);
		view.setTop(40);
		view.show();
		view.setCanDrag(false);
		view.setCanDrop(false);
	}
	
	protected void refreshConnection() {
		serverCall.getTopology(controlledDomain.getKey(),new AsyncCallback<Domain>() {
			
			@Override
			public void onSuccess(Domain result) {
				topologyResult = result;
				setViewData();
				view.setTitle(dominio + view.getTitlePrefix() + puntos + topologyResult.getName());
				view.setAutoSize(true);
				view.setCanDragResize(true);
					}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al obtener topologia");
				
			}
		});
	}

	protected void setViewData() {
		Tree dataLeft=new Tree();
		Tree dataRight=new Tree();
		
		TreeNode rootNodeLeft = new TreeNode();
		TreeNode rootNodeRight = new TreeNode();
		
		TreeNode domainNodeLeft = new TreeNode();
		TreeNode domainNodeRight = new TreeNode();
		
		TreeNode adminNode = new TreeNode(topologyResult.getAdminServerName());		
		adminNode.setAttribute(TopologyListView.name, topologyResult.getAdminServerName());
		adminNode.setAttribute(TopologyListView.health, topologyResult.getAdminServerStatus());
		adminNode.setAttribute(TopologyListView.serverStatus, topologyResult.getAdminServerStatus());
		adminNode.setAttribute(TopologyListView.serverHealth, topologyResult.getAdminHealth());
		adminNode.setIcon(ResourcesManager.getAdminserverimage());
		
		domainNodeLeft.setAttribute(TopologyListView.name, topologyResult.getName());
		domainNodeRight.setAttribute(TopologyListView.name, topologyResult.getName());
		
		dataLeft.setRoot(rootNodeLeft);
		dataLeft.add(domainNodeLeft,rootNodeLeft );
		
		dataRight.setRoot(rootNodeRight);
		dataRight.add(domainNodeRight,rootNodeRight);
		
		dataLeft.add(adminNode, domainNodeLeft);		
		
		view.getTreeGridLeft().setData(dataLeft);
		view.getTreeGridRight().setData(dataRight);
		
		if (topologyResult.getManagedServers().size() > 0){
			int i =0;
			for (WLServer server : topologyResult.getManagedServers()) {
				if(i % 2 == 0)
					addDataToTree(domainNodeLeft,dataLeft,server);
				else
					addDataToTree(domainNodeRight,dataRight,server);	
				i++;
			}
		}
		
		
		dataLeft.openAll();
		dataRight.openAll();
		view.getTreeGridLeft().setData(dataLeft);
		view.getTreeGridRight().setData(dataRight);
		view.redraw();
	}

	private void addDataToTree(TreeNode domainNode,Tree data,WLServer server){
		TreeNode managedNode = new TreeNode();
		managedNode.setAttribute(TopologyListView.name, server.getName());
		managedNode.setAttribute(TopologyListView.health, server.getServerStatus());
		if(server.isOk())		
			managedNode.setAttribute(TopologyListView.serverStatus, server.getServerStatus());
		else
			managedNode.setAttribute(TopologyListView.serverStatus, server.getErrorStatus());
		managedNode.setAttribute(TopologyListView.serverHealth, server.getHealth());
		managedNode.setIcon(ResourcesManager.getServerimage());
		data.add(managedNode, domainNode);
		for (WLDatasource datasource : server.getDatasources()) {
			TreeNode datasourceNode = new TreeNode();
			datasourceNode.setAttribute(TopologyListView.name, datasource.getName());
			datasourceNode.setAttribute(TopologyListView.health, datasource.getStatus());
			datasourceNode.setAttribute(TopologyListView.serverStatus, datasource.getIndicatorResult());
			datasourceNode.setIcon(ResourcesManager.getDbimage());
			data.add(datasourceNode,managedNode);
		}
	}
	
	public void hidePopUp(){
		this.view.hide();
	}
}
