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
import wlstatistics.client.controller.connectionComponent.ConnectionPoupController;
import wlstatistics.client.views.connectionComponent.ConnectionPopUp;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.PortalLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

public class ServersView extends VLayout {
	private ConnectionPopUp connectionPopUp = new ConnectionPopUp();
	public HashMap<String,ServerViewController> serversViewControllers = new HashMap<String, ServerViewController>();
	public PortalLayout serversPanel;
	public ServersView() {
		
		ConnectionPoupController popController = new ConnectionPoupController(connectionPopUp);
		
		serversPanel = new PortalLayout(1);
		serversPanel.setWidth100();  
		serversPanel.setHeight100();  
		serversPanel.setShowColumnMenus(false);
		this.setWidth100();
		this.setHeight100();
		ToolStrip menuBar = new ToolStrip();
		this.addMember(menuBar);
		menuBar.setSize("100%", "32px");
		Menu menuBar_1 = new Menu();
			
		MenuItem mntmNueva = new MenuItem("Nueva");
		mntmNueva.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				if (!connectionPopUp.isDrawn())
					connectionPopUp.draw();
				else
					connectionPopUp.show();
			}
		});
		menuBar_1.addItem(mntmNueva);
		
		MenuItem mntmEliminar = new MenuItem("Eliminar");
		mntmEliminar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(MenuItemClickEvent event) {
				Window.alert("Funcionalidad No Implementada");
				
			}
		});
		
		menuBar_1.addItem(mntmEliminar);
		ToolStripMenuButton menuConexiones = new ToolStripMenuButton("Conexiones",menuBar_1);
		menuBar.addMenuButton(menuConexiones);
		
		menuBar.addSeparator();
		this.addMember(serversPanel);
//		
//		MenuItem mntmSalir = new MenuItem("Salir", false, new Command() {
//			public void execute() {
//				Window.alert("Funcionalidad No Implementada");
//			}
//		});
//		menuBar.addItem(mntmSalir);
		
	}

	
}
