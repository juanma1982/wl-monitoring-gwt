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
package wlstatistics.client.views;

import java.util.HashMap;

import wlstatistics.client.controller.ServerViewController;
import wlstatistics.client.resources.ResourcesManager;

import com.smartgwt.client.widgets.layout.PortalLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class DomainMonitoringView extends VLayout {
	private static final String datasource = "Datasource";
	private static final String manejado = "Servidor Manejado";
	private static final String admin = "Servidor Administrador";
	private static final String acerca = "Acerca de..";
	private static final String conexiones = "Conexiones";
	private static final String soa = "SOA Suite";
	private static final String osbasync = "OSB Asincronico";
	private static final String osbsync = "OSB Sincronico";
		
	public HashMap<String,ServerViewController> serversViewControllers = new HashMap<String, ServerViewController>();
	public PortalLayout serversPanel;
	
	private MenuItem mntmOSBSinc;
	private MenuItem mntmOSBAsync;
	private MenuItem mntmSOA;
	private MenuItem mntmHelp;
	private MenuItem mntmManaged;
	private MenuItem mntmAdminServer;
	private MenuItem mntmDatasource;
	
	public DomainMonitoringView() {
		
		serversPanel = new PortalLayout(1);
		serversPanel.setWidth100();  
		serversPanel.setHeight100();  
		serversPanel.setShowColumnMenus(false);
		serversPanel.setCanAcceptDrop(false);
		this.setWidth100();
		this.setHeight100();
		ToolStrip menuBar = new ToolStrip();
		this.addMember(menuBar);
		menuBar.setSize("100%", "32px");
		Menu menuBarConnections = new Menu();
			
		mntmOSBSinc = new MenuItem(osbsync);
		menuBarConnections.addItem(mntmOSBSinc);
		
		mntmOSBAsync = new MenuItem(osbasync);
		menuBarConnections.addItem(mntmOSBAsync);
		
		mntmSOA = new MenuItem(soa);
		menuBarConnections.addItem(mntmSOA);
		
		ToolStripMenuButton menuConexiones = new ToolStripMenuButton(conexiones,menuBarConnections);
		menuBar.addMenuButton(menuConexiones);
		
		menuBar.addSeparator();
		
		Menu menuBarHelp = new Menu();
		
		mntmHelp = new MenuItem(acerca);
		menuBarHelp.addItem(mntmHelp);
		
		mntmAdminServer = new MenuItem(admin);
		mntmAdminServer.setIcon(ResourcesManager.getAdminserverimage());
		menuBarHelp.addItem(mntmAdminServer);
		
		mntmManaged = new MenuItem(manejado);
		mntmManaged.setIcon(ResourcesManager.getServerimage());
		menuBarHelp.addItem(mntmManaged);
		
		mntmDatasource = new MenuItem(datasource);
		mntmDatasource.setIcon(ResourcesManager.getDbimage());
		menuBarHelp.addItem(mntmDatasource);
		
		ToolStripMenuButton menuAcerca = new ToolStripMenuButton("Ayuda",menuBarHelp);
		menuBar.addMenuButton(menuAcerca);
		this.addMember(serversPanel);
		
	}

	public MenuItem getMntmOSBSinc() {
		return mntmOSBSinc;
	}

	public void setMntmOSBSinc(MenuItem mntmOSBSinc) {
		this.mntmOSBSinc = mntmOSBSinc;
	}

	public MenuItem getMntmOSBAsync() {
		return mntmOSBAsync;
	}

	public void setMntmOSBAsync(MenuItem mntmOSBAsync) {
		this.mntmOSBAsync = mntmOSBAsync;
	}

	public MenuItem getMntmSOA() {
		return mntmSOA;
	}

	public void setMntmSOA(MenuItem mntmSOA) {
		this.mntmSOA = mntmSOA;
	}

	public MenuItem getMntmHelp() {
		return mntmHelp;
	}

	public void setMntmHelp(MenuItem mntmHelp) {
		this.mntmHelp = mntmHelp;
	}

	public MenuItem getMntmManaged() {
		return mntmManaged;
	}

	public MenuItem getMntmAdminServer() {
		return mntmAdminServer;
	}

	public MenuItem getMntmDatasource() {
		return mntmDatasource;
	}
	
	
	
}
