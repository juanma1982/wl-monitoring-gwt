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
import wlstatistics.client.views.topologyComponent.ServerComponent;
import wlstatistics.shared.model.WLServer;

import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HeaderControl;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Portlet;
import com.smartgwt.client.widgets.layout.VLayout;

public class TopologyView extends Portlet {
	private ServerComponent adminServercomp;
	private VLayout managedServersPanel;
	private HLayout horizontalPanel_1;
	private IButton okButton;
	private Label statusClusterLabel;
	private HLayout horizontalPanel_2;
	private Img clusterStatusImage;
	private Img refreshImage;
	private HeaderControl refresh; 
	
	public Img getRefreshImage() {
		return refreshImage;
	}


	public IButton getOkButton() {
		return okButton;
	}


	public void setOkButton(IButton okButton) {
		this.okButton = okButton;
	}


	public HeaderControl getRefresh() {
		return refresh;
	}


	public ServerComponent getAdminServercomp() {
		return adminServercomp;
	}


	public void setAdminServercomp(ServerComponent adminServercomp) {
		this.adminServercomp = adminServercomp;
	}


	public VLayout getManagedServersPanel() {
		return managedServersPanel;
	}


	public void setManagedServersPanel(VLayout managedServersPanel) {
		this.managedServersPanel = managedServersPanel;
	}


	public TopologyView() {
		setTitle("Topologia");
		
		horizontalPanel_2 = new HLayout();		
		
		statusClusterLabel = new Label("Cluster Status:");
		horizontalPanel_2.addMember(statusClusterLabel);
		statusClusterLabel.setSize("97px", "28px");
		
		clusterStatusImage = new Img(ResourcesManager.getRedStatusImgUrl());
		horizontalPanel_2.addMember(clusterStatusImage);
		clusterStatusImage.setSize("30px", "28px");
		addItem(horizontalPanel_2);
		horizontalPanel_2.setWidth100();
		horizontalPanel_2.setHeight("36px");
		
		horizontalPanel_1 = new HLayout();
		addItem(horizontalPanel_1);
		
		adminServercomp = new ServerComponent();
		horizontalPanel_1.addMember(adminServercomp);
		//		horizontalPanel_1.setCellVerticalAlignment(adminServercomp, HasVerticalAlignment.ALIGN_MIDDLE);
		//		horizontalPanel_1.setCellHorizontalAlignment(adminServercomp, HasHorizontalAlignment.ALIGN_CENTER);
		adminServercomp.setHeight("104px");
		
		managedServersPanel = new VLayout();
		horizontalPanel_1.addMember(managedServersPanel);
		
		 refresh = new HeaderControl(HeaderControl.REFRESH);               
         
         setHeaderControls(HeaderControls.MINIMIZE_BUTTON, HeaderControls.HEADER_LABEL, refresh,HeaderControls.CLOSE_BUTTON);

		
		okButton = new IButton("Close");
		addItem(okButton);
		okButton.moveTo(30, 177);
	}
	
	public void addServerComponent(WLServer server){
		ServerComponent serverCom = new ServerComponent();
		serverCom.setServerName(server.getName());
		serverCom.setServerUrl(server.getHost()+":"+server.getPort());
		if (server.isRunning())
			{
				serverCom.setStatusGreen();
				setClusterStatusGreen();
			}
		else
			serverCom.setStatusRed();
		managedServersPanel.addMember(serverCom);
	}
	
	public void setFinalSize(){
		this.setHeight(managedServersPanel.getChildren().length*304);
		this.setWidth(400);		
	}
	public void removerServersComponents(){
		for (Canvas child : this.managedServersPanel.getChildren()) {
			this.managedServersPanel.removeChild(child);//child.removeFromParent();
		}
		//this.managedServersPanel.clear();
	}
	public void setClusterStatusRed() {
		clusterStatusImage.setSrc(ResourcesManager.getRedStatusImgUrl());
	}

	public void setClusterStatusGreen() {
		clusterStatusImage.setSrc(ResourcesManager.getGreenStatusImgUrl());
	}
	
}
