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

import wlstatistics.client.resources.ResourcesManager;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.Portlet;

public class ServerView extends Portlet {

	private Canvas absolutePanel;
	private Img ServerStatusImage;
	private Img ConnectionStatusImage;
	private IButton StatisticsButton;
	private IButton ReconnectButton;
	private IButton selectorJmsButton;
	private Label serverName ;
	private IButton topologiaButton;
	private IButton listaTopologiaButton;	
	private IButton guardarButton;

	public ServerView() {
		
		super();
		setTitle("Domain");
		absolutePanel = new Canvas();
		absolutePanel.setWidth("369px");
		
		guardarButton = new IButton("Agregar a Tablero");
		absolutePanel.addChild(guardarButton);
		guardarButton.setRect(238, 180, 125, 35);
		this.setSize("389px", "230px");
		this.addChild(absolutePanel);	
		
		StatisticsButton = new IButton("Estad\u00EDsticas");	
		StatisticsButton.setSize("100px", "35px");
		StatisticsButton.moveTo(10, 180);
		absolutePanel.addChild(StatisticsButton);
		
		ReconnectButton = new IButton("Reconectar");
		ReconnectButton.setSize("100px", "35px");
		ReconnectButton.moveTo(125, 180);
		absolutePanel.addChild(ReconnectButton);		
		

		Label lblNewLabel = new Label("Server Name:");
		absolutePanel.addChild(lblNewLabel);
		lblNewLabel.moveTo(10, 10);

		serverName = new Label("sName");
		absolutePanel.addChild(serverName);
		serverName.setRect(96, 51, 100, 18);

		ServerStatusImage = new Img();
		absolutePanel.addChild(ServerStatusImage);
		ServerStatusImage.moveTo(204, 79);
		ServerStatusImage.setSize("18px", "18px");

		ConnectionStatusImage = new Img();
		absolutePanel.addChild(ConnectionStatusImage);
		ConnectionStatusImage.moveTo(204, 115);
		ConnectionStatusImage.setSize("18px", "18px");

		Label lblNewLabel_2 = new Label("Server Status");
		absolutePanel.addChild(lblNewLabel_2);
		lblNewLabel_2.moveTo(10, 40);
		
		Label lblNewLabel_3 = new Label("Connection Status");
		absolutePanel.addChild(lblNewLabel_3);
		lblNewLabel_3.setRect(10, 108, 100, 30);
		
		selectorJmsButton = new IButton("JMS");
		absolutePanel.addChild(selectorJmsButton);
		selectorJmsButton.moveTo(10, 139);
		selectorJmsButton.setSize("100px", "35px");
		
		topologiaButton = new IButton("Topolog\u00EDa");
		absolutePanel.addChild(topologiaButton);
		topologiaButton.moveTo(125, 139);
		topologiaButton.setSize("100px", "35px");
		
		listaTopologiaButton = new IButton("Lista Topolog\u00EDa");
		absolutePanel.addChild(listaTopologiaButton);
		listaTopologiaButton.setRect(238, 139, 125, 35);
		
		SetConnectionStatusRed();
		SetServerStatusRed();
		this.setCanDragResize(true);  
	}

	public void SetConnectionStatusRed() {
		ConnectionStatusImage.setSrc(ResourcesManager.getRedStatusImgUrl());
	}

	public void SetConnectionStatusGreen() {
		ConnectionStatusImage.setSrc(ResourcesManager.getGreenStatusImgUrl());
	}
	
	public void SetServerStatusRed() {
		ServerStatusImage.setSrc(ResourcesManager.getRedStatusImgUrl());
	}

	public void SetServerStatusGreen() {		
		ServerStatusImage.setSrc(ResourcesManager.getGreenStatusImgUrl());
	}
	
	public IButton getStatisticButton(){
		return StatisticsButton;
	}
	
	public IButton getReconnectButton() {
		return ReconnectButton;
	}
	
	public IButton getSelectorJmsButton() {
		return selectorJmsButton;
	}
	
	public IButton getTopologiaButton() {
		return topologiaButton;
	}
	
	public IButton getListaTopologiaButton() {
		return listaTopologiaButton;
	}

	public void setListaTopologiaButton(IButton listaTopologiaButton) {
		this.listaTopologiaButton = listaTopologiaButton;
	}
	
	public void setServerName(String name){		
		this.serverName.setContents(name);
	}

	public void setDomainName(String result) {
		this.setTitle("Domain "+result);
	}

	public IButton getGuardarButton() {
		return guardarButton;
	}

	public void setGuardarButton(IButton guardarButton) {
		this.guardarButton = guardarButton;
	}
	
	
}
